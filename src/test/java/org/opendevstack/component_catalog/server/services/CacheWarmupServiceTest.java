package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.mother.CatalogsCollectionsEntityTargetMother;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Optional;

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
        var target1 = CatalogsCollectionsEntityTargetMother.of("catalog-a");
        var target2 = CatalogsCollectionsEntityTargetMother.of("catalog-b");
        var entity = catalogsCollectionsEntityWith(target1, target2);

        var items = List.of(CatalogItemEntityContextMother.of());

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities("catalog-a")).thenReturn(items);
        when(catalogEntitiesService.getCatalogItemsEntities("catalog-b")).thenReturn(items);

        service.warmup();

        verify(catalogEntitiesService).getCatalogItemsEntities("catalog-a");
        verify(catalogEntitiesService).getCatalogItemsEntities("catalog-b");
    }

    // -------------------------------------------------------------------------
    // warmup() — one catalog fails, the other still loads
    // -------------------------------------------------------------------------

    @Test
    void givenOneCatalogFails_whenWarmup_thenOtherCatalogsStillLoaded() throws Exception {
        var target1 = CatalogsCollectionsEntityTargetMother.of("catalog-ok");
        var target2 = CatalogsCollectionsEntityTargetMother.of("catalog-bad");
        var entity = catalogsCollectionsEntityWith(target1, target2);

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities("catalog-ok"))
                .thenReturn(List.of(CatalogItemEntityContextMother.of()));
        when(catalogEntitiesService.getCatalogItemsEntities("catalog-bad"))
                .thenThrow(new InvalidIdException("catalog-bad"));

        // Must not throw — errors are swallowed and logged as WARN
        service.warmup();

        verify(catalogEntitiesService).getCatalogItemsEntities("catalog-ok");
        verify(catalogEntitiesService).getCatalogItemsEntities("catalog-bad");
    }

    // -------------------------------------------------------------------------
    // warmup() — RuntimeException on one catalog is also swallowed
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogThrowsRuntimeException_whenWarmup_thenContinuesWithOtherCatalogs() throws Exception {
        var target1 = CatalogsCollectionsEntityTargetMother.of("catalog-runtime-error");
        var target2 = CatalogsCollectionsEntityTargetMother.of("catalog-fine");
        var entity = catalogsCollectionsEntityWith(target1, target2);

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities("catalog-runtime-error"))
                .thenThrow(new RuntimeException("Unexpected error"));
        when(catalogEntitiesService.getCatalogItemsEntities("catalog-fine"))
                .thenReturn(List.of());

        service.warmup();

        verify(catalogEntitiesService).getCatalogItemsEntities("catalog-runtime-error");
        verify(catalogEntitiesService).getCatalogItemsEntities("catalog-fine");
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
        var target = CatalogsCollectionsEntityTargetMother.of("empty-catalog");
        var entity = catalogsCollectionsEntityWith(target);

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities("empty-catalog")).thenReturn(List.of());

        service.warmup();

        verify(catalogEntitiesService).getCatalogItemsEntities("empty-catalog");
    }

    // -------------------------------------------------------------------------
    // helpers
    // -------------------------------------------------------------------------

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



