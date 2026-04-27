package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private CatalogsCollectionService catalogsCollectionService;
    private CatalogEntitiesService catalogEntitiesService;
    private CacheWarmupService service;

    @BeforeEach
    void setUp() {
        catalogsCollectionService = mock(CatalogsCollectionService.class);
        catalogEntitiesService = mock(CatalogEntitiesService.class);
        service = new CacheWarmupService(catalogsCollectionService, catalogEntitiesService);
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
        var entity = catalogsCollectionsEntityWith(targetWithUrl("catalog-a"), targetWithUrl("catalog-b"));
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
        var entity = catalogsCollectionsEntityWith(targetWithUrl("catalog-ok"), targetWithUrl("catalog-bad"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any()))
                .thenReturn(List.of(CatalogItemEntityContextMother.of()))
                .thenThrow(new InvalidIdException("catalog-bad"));

        service.warmup();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — RuntimeException on one catalog is also swallowed
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogThrowsRuntimeException_whenWarmup_thenContinuesWithOtherCatalogs() throws Exception {
        var entity = catalogsCollectionsEntityWith(targetWithUrl("catalog-err"), targetWithUrl("catalog-fine"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any()))
                .thenThrow(new RuntimeException("Unexpected error"))
                .thenReturn(List.of());

        service.warmup();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — target with null URL is skipped gracefully
    // -------------------------------------------------------------------------

    @Test
    void givenTargetWithNullUrl_whenWarmup_thenSkippedGracefully() throws Exception {
        var nullUrlTarget = new CatalogsCollectionsEntityTarget();
        nullUrlTarget.setSlug("bad-target");
        nullUrlTarget.setUrl(null);

        var entity = catalogsCollectionsEntityWith(nullUrlTarget, targetWithUrl("valid-catalog"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(List.of());

        service.warmup();

        // Only the valid target should reach getCatalogItemsEntities
        verify(catalogEntitiesService, times(1)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — getCatalogsCollection throws unexpectedly
    // -------------------------------------------------------------------------

    @Test
    void givenGetCatalogsCollectionThrows_whenWarmup_thenNoExceptionPropagated() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection())
                .thenThrow(new RuntimeException("Connection refused"));

        service.warmup();

        verifyNoInteractions(catalogEntitiesService);
    }

    // -------------------------------------------------------------------------
    // warmup() — catalog with zero items
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogWithNoItems_whenWarmup_thenCompletesNormally() throws Exception {
        var entity = catalogsCollectionsEntityWith(targetWithUrl("empty-catalog"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(List.of());

        service.warmup();

        verify(catalogEntitiesService, times(1)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // helpers
    // -------------------------------------------------------------------------

    /**
     * Creates a target with a real Bitbucket-like URL — same format used in production YAML files.
     * The ID sent to getCatalogItemsEntities will be idEncode(url), exactly as EntitiesMapper does.
     */
    private CatalogsCollectionsEntityTarget targetWithUrl(String slug) {
        var target = new CatalogsCollectionsEntityTarget();
        target.setSlug(slug);
        target.setUrl("https://bitbucket.example.com/projects/MYPRJ/repos/" + slug + "/raw/Catalog.yaml?at=refs%2Fheads%2Fmaster");
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
