package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CacheWarmupServiceTest {

    private static final String BASE_RAW_URL = "https://my-bitbucket-server.com";
    private static final String BASE_REST_URL = "https://my-bitbucket-server.com/rest/api/latest";

    private CatalogsCollectionService catalogsCollectionService;
    private CatalogEntitiesService catalogEntitiesService;
    private BitbucketService bitbucketService;
    private CacheWarmupService service;

    @BeforeEach
    void setUp() {
        catalogsCollectionService = mock(CatalogsCollectionService.class);
        catalogEntitiesService = mock(CatalogEntitiesService.class);
        bitbucketService = mock(BitbucketService.class);
        service = new CacheWarmupService(catalogsCollectionService, catalogEntitiesService, bitbucketService);

        // Default: pathAtBuilder() returns a real builder backed by our test base URLs
        when(bitbucketService.pathAtBuilder()).thenReturn(
                BitbucketPathAt.builder()
                        .baseRawUrl(BASE_RAW_URL)
                        .baseRestUrl(BASE_REST_URL)
        );
    }

    // -------------------------------------------------------------------------
    // run() — ApplicationRunner entry point
    // -------------------------------------------------------------------------

    @Test
    void whenRun_thenWarmupIsCalled() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.empty());

        service.run(new DefaultApplicationArguments());

        verify(catalogsCollectionService).getCatalogsCollection();
    }

    // -------------------------------------------------------------------------
    // warmup() — catalog-of-catalogs not found
    // -------------------------------------------------------------------------

    @Test
    void givenNoCatalogOfCatalogs_whenWarmup_thenSkipsWithWarning() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.empty());

        service.warmup();

        verify(catalogsCollectionService).getCatalogsCollection();
        verifyNoInteractions(catalogEntitiesService);
    }

    // -------------------------------------------------------------------------
    // warmup() — happy path: two catalogs loaded successfully
    // -------------------------------------------------------------------------

    @Test
    void givenTwoCatalogs_whenWarmup_thenBothCatalogsLoaded() throws Exception {
        var entity = catalogsCollectionsEntityWith(bitbucketTarget("catalog-a"), bitbucketTarget("catalog-b"));
        var items = List.of(CatalogItemEntityContextMother.of());

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(items);

        service.warmup();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — one catalog fails, the other still loads
    // -------------------------------------------------------------------------

    @Test
    void givenOneCatalogFails_whenWarmup_thenOtherCatalogsStillLoaded() throws Exception {
        var entity = catalogsCollectionsEntityWith(bitbucketTarget("catalog-ok"), bitbucketTarget("catalog-bad"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any()))
                .thenReturn(List.of(CatalogItemEntityContextMother.of()))
                .thenThrow(new InvalidIdException("catalog-bad"));

        // Must not throw — errors are swallowed and logged as WARN
        service.warmup();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — RuntimeException on one catalog is also swallowed
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogThrowsRuntimeException_whenWarmup_thenContinuesWithOtherCatalogs() throws Exception {
        var entity = catalogsCollectionsEntityWith(bitbucketTarget("catalog-err"), bitbucketTarget("catalog-fine"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any()))
                .thenThrow(new RuntimeException("Unexpected error"))
                .thenReturn(List.of());

        service.warmup();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — getCatalogsCollection throws unexpectedly
    // -------------------------------------------------------------------------

    @Test
    void givenGetCatalogsCollectionThrows_whenWarmup_thenNoExceptionPropagated() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection())
                .thenThrow(new RuntimeException("Connection refused"));

        // Must not propagate — warmup failures must never crash the app or the scheduler
        service.warmup();

        verifyNoInteractions(catalogEntitiesService);
    }

    // -------------------------------------------------------------------------
    // warmup() — catalog with zero items
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogWithNoItems_whenWarmup_thenCompletesNormally() throws Exception {
        var entity = catalogsCollectionsEntityWith(bitbucketTarget("empty-catalog"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(List.of());

        service.warmup();

        verify(catalogEntitiesService, times(1)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // helpers
    // -------------------------------------------------------------------------

    /**
     * Creates a target whose URL is a valid full Bitbucket raw URL (same format the real data uses),
     * so that BitbucketService.pathAtBuilder() can parse it correctly in the warmup logic.
     */
    private CatalogsCollectionsEntityTarget bitbucketTarget(String slug) {
        var target = new CatalogsCollectionsEntityTarget();
        target.setSlug(slug);
        target.setUrl(BASE_RAW_URL + "/projects/MYPROJECT/repos/" + slug + "/raw/Catalog.yaml?at=refs%2Fheads%2Fmaster");
        return target;
    }

    private static CatalogsCollectionsEntity catalogsCollectionsEntityWith(CatalogsCollectionsEntityTarget... targets) {
        var spec = new CatalogsCollectionsEntitySpec();
        spec.setTargets(targets);

        var metadata = new CatalogsCollectionsEntityMetadata();
        metadata.setName("catalog-of-catalogs");
        metadata.setSpec(spec);

        var entity = new CatalogsCollectionsEntity();
        entity.setMetadata(metadata);
        return entity;
    }
}
