package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CacheWarmupServiceTest {

    private CatalogsCollectionService catalogsCollectionService;
    private CatalogEntitiesService catalogEntitiesService;
    private ProvisionerActionsService provisionerActionsService;
    private CacheWarmupService service;

    @BeforeEach
    void setUp() {
        catalogsCollectionService = mock(CatalogsCollectionService.class);
        catalogEntitiesService = mock(CatalogEntitiesService.class);
        provisionerActionsService = mock(ProvisionerActionsService.class);

        service = new CacheWarmupService(catalogsCollectionService, catalogEntitiesService, provisionerActionsService);
    }

    // -------------------------------------------------------------------------
    // run() — ApplicationRunner entry point
    // -------------------------------------------------------------------------

    @Test
    void whenRun_thenWarmupCatalogsCacheIsCalled() throws Exception {
        // @Async is ignored in unit tests (no Spring proxy); run() delegates to warmup() synchronously here.
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.empty());

        service.run(new DefaultApplicationArguments());

        verify(catalogsCollectionService).getCatalogsCollection();
    }

    // -------------------------------------------------------------------------
    // warmup() — catalog-of-catalogs not found
    // -------------------------------------------------------------------------

    @Test
    void givenNoCatalogOfCatalogs_whenWarmup_CatalogsCache_thenSkipsWithWarning() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.empty());

        service.warmupCatalogsBitbucketServiceCache();

        verify(catalogsCollectionService).getCatalogsCollection();
        verifyNoInteractions(catalogEntitiesService);
    }

    // -------------------------------------------------------------------------
    // warmup() — happy path: two catalogs loaded successfully
    // -------------------------------------------------------------------------

    @Test
    void givenTwoCatalogs_whenWarmup_CatalogsCache_thenBothCatalogsLoaded() throws Exception {
        var entity = catalogsCollectionsEntityWith(targetWithUrl("catalog-a"), targetWithUrl("catalog-b"));
        var items = List.of(CatalogItemEntityContextMother.of());

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(items);

        service.warmupCatalogsBitbucketServiceCache();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — one catalog fails, the other still loads
    // -------------------------------------------------------------------------

    @Test
    void givenOneCatalogFails_whenWarmup_CatalogsCache_thenOtherCatalogsStillLoaded() throws Exception {
        var entity = catalogsCollectionsEntityWith(targetWithUrl("catalog-ok"), targetWithUrl("catalog-bad"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any()))
                .thenReturn(List.of(CatalogItemEntityContextMother.of()))
                .thenThrow(new InvalidIdException("catalog-bad"));

        service.warmupCatalogsBitbucketServiceCache();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — RuntimeException on one catalog is also swallowed
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogThrowsRuntimeException_whenWarmup_CatalogsCache_thenContinuesWithOtherCatalogs() throws Exception {
        var entity = catalogsCollectionsEntityWith(targetWithUrl("catalog-err"), targetWithUrl("catalog-fine"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any()))
                .thenThrow(new RuntimeException("Unexpected error"))
                .thenReturn(List.of());

        service.warmupCatalogsBitbucketServiceCache();

        verify(catalogEntitiesService, times(2)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — target with null URL is skipped gracefully
    // -------------------------------------------------------------------------

    @Test
    void givenTargetWithNullUrl_whenWarmup_CatalogsCache_thenSkippedGracefully() throws Exception {
        var nullUrlTarget = new CatalogsCollectionsEntityTarget();
        nullUrlTarget.setSlug("bad-target");
        nullUrlTarget.setUrl(null);

        var entity = catalogsCollectionsEntityWith(nullUrlTarget, targetWithUrl("valid-catalog"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(List.of());

        service.warmupCatalogsBitbucketServiceCache();

        // Only the valid target should reach getCatalogItemsEntities
        verify(catalogEntitiesService, times(1)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // warmup() — getCatalogsCollection throws unexpectedly
    // -------------------------------------------------------------------------

    @Test
    void givenGetCatalogsCollectionThrows_whenWarmup_CatalogsCache_thenNoExceptionPropagated() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection())
                .thenThrow(new RuntimeException("Connection refused"));

        service.warmupCatalogsBitbucketServiceCache();

        verifyNoInteractions(catalogEntitiesService);
    }

    // -------------------------------------------------------------------------
    // warmup() — catalog with zero items
    // -------------------------------------------------------------------------

    @Test
    void givenCatalogWithNoItems_whenWarmup_CatalogsCache_thenCompletesNormally() throws Exception {
        var entity = catalogsCollectionsEntityWith(targetWithUrl("empty-catalog"));

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(entity));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(List.of());

        service.warmupCatalogsBitbucketServiceCache();

        verify(catalogEntitiesService, times(1)).getCatalogItemsEntities(any());
    }

    // -------------------------------------------------------------------------
    // run() — ApplicationRunner entry point
    // -------------------------------------------------------------------------

    @Test
    void whenRun_thenWarmupProjectComponentsCacheIsCalled() {
        // @Async is ignored in unit tests (no Spring proxy); run() delegates to warmup() synchronously here.
        when(provisionerActionsService.listAllProjectsJsons()).thenReturn(List.of());

        service.run(new DefaultApplicationArguments());

        verify(provisionerActionsService).listAllProjectsJsons();
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: empty list returned
    // -------------------------------------------------------------------------

    @Test
    void givenNoProjects_whenWarmup_ProjectComponentsCache_thenSkips() {
        when(provisionerActionsService.listAllProjectsJsons()).thenReturn(List.of());

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService).listAllProjectsJsons();
        verify(provisionerActionsService, never()).getProjectComponents(any(String.class));
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: null list returned
    // -------------------------------------------------------------------------

    @Test
    void givenNullProjectList_whenWarmup_ProjectComponentsCache_thenSkips() {
        when(provisionerActionsService.listAllProjectsJsons()).thenReturn(null);

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService).listAllProjectsJsons();
        verify(provisionerActionsService, never()).getProjectComponents(any(String.class));
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: single project loaded
    // -------------------------------------------------------------------------

    @Test
    void givenSingleProject_whenWarmup_ProjectComponentsCache_thenLoadsComponents() {
        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of("PROJECT_KEY.json"));

        ProjectComponents components = mock(ProjectComponents.class);
        when(components.getComponents()).thenReturn(Map.of("c1", ProjectComponent.builder().build()));

        when(provisionerActionsService.getProjectComponents("PROJECT_KEY"))
                .thenReturn(components);

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService).getProjectComponents("PROJECT_KEY");
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: non-json files are ignored
    // -------------------------------------------------------------------------

    @Test
    void givenNonJsonFiles_whenWarmup_ProjectComponentsCache_thenIgnored() {
        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of("PROJECT_KEY.json", "readme.txt"));

        ProjectComponents components = mock(ProjectComponents.class);
        when(components.getComponents()).thenReturn(Map.of());

        when(provisionerActionsService.getProjectComponents("PROJECT_KEY"))
                .thenReturn(components);

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService).getProjectComponents("PROJECT_KEY");
        verify(provisionerActionsService, never()).getProjectComponents("readme");
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: multiple projects loaded successfully
    // -------------------------------------------------------------------------

    @Test
    void givenMultipleProjects_whenWarmup_ProjectComponentsCache_thenAllProjectsLoaded() {
        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of("PROJECT_A_KEY.json", "PROJECT_B_KEY.json"));

        ProjectComponents components = mock(ProjectComponents.class);
        when(components.getComponents()).thenReturn(Map.of("c1", ProjectComponent.builder().build()));

        when(provisionerActionsService.getProjectComponents(any(String.class)))
                .thenReturn(components);

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService, times(2)).getProjectComponents(any(String.class));
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: project returns null data
    // -------------------------------------------------------------------------

    @Test
    void givenProjectReturnsNull_whenWarmup_ProjectComponentsCache_thenHandledGracefully() {
        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of("PROJECT_KEY.json"));

        when(provisionerActionsService.getProjectComponents("PROJECT_KEY"))
                .thenReturn(null);

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService).getProjectComponents("PROJECT_KEY");
    }

    // -------------------------------------------------------------------------
    // warmup() — project components cache: exception does not interrupt processing
    // -------------------------------------------------------------------------

    @Test
    void givenExceptionWhileLoadingProject_whenWarmup_ProjectComponentsCache_thenContinues() {
        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of("PROJECT_A_KEY.json", "PROJECT_B_KEY.json"));

        when(provisionerActionsService.getProjectComponents(any(String.class)))
                .thenThrow(new RuntimeException("Some error"));

        service.warmupProjectComponentsCache();

        verify(provisionerActionsService, times(2)).getProjectComponents(any(String.class));
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
