package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.mother.CatalogsCollectionsEntityMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;

@ExtendWith(MockitoExtension.class)
class CatalogItemBySlugServiceTest {

    private static final String BASE_RAW_URL = "https://bitbucket.example.com";
    private static final String BASE_REST_URL = "https://bitbucket.example.com/rest/api/latest";

    @Mock
    private CatalogsCollectionService catalogsCollectionService;

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @Mock
    private BitbucketService bitbucketService;

    @InjectMocks
    private CatalogItemBySlugService catalogItemBySlugService;

    @BeforeEach
    void setUpPathAtBuilder() {
        lenient().when(bitbucketService.pathAtBuilder()).thenReturn(
                BitbucketPathAt.builder()
                        .baseRawUrl(BASE_RAW_URL)
                        .baseRestUrl(BASE_REST_URL)
        );
    }

    @Test
    void findByCatalogItemSlug_whenNoCatalogsCollection_returnsEmpty() throws Exception {
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.empty());

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_my-repo"));

        assertThat(result).isEmpty();
        verifyNoInteractions(catalogEntitiesService);
    }

    @Test
    void findByCatalogItemSlug_whenCollectionHasNullTargets_returnsEmpty() throws Exception {
        var collection = new CatalogsCollectionsEntity();
        var metadata = new org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityMetadata();
        var spec = new CatalogsCollectionsEntitySpec();
        spec.setTargets(null);
        metadata.setSpec(spec);
        collection.setMetadata(metadata);

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_my-repo"));

        assertThat(result).isEmpty();
        verifyNoInteractions(catalogEntitiesService);
    }

    @Test
    void findByCatalogItemSlug_whenCollectionHasEmptyTargets_returnsEmpty() throws Exception {
        var collection = new CatalogsCollectionsEntity();
        var metadata = new org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityMetadata();
        var spec = new CatalogsCollectionsEntitySpec();
        spec.setTargets(new CatalogsCollectionsEntityTarget[]{});
        metadata.setSpec(spec);
        collection.setMetadata(metadata);

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_my-repo"));

        assertThat(result).isEmpty();
        verifyNoInteractions(catalogEntitiesService);
    }

    @Test
    void findByCatalogItemSlug_whenItemMatchesOnFirstCatalog_returnsIt() throws Exception {
        var itemCtx = mock(CatalogItemEntityContext.class);

        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "MYPROJECT");

        when(catalogEntitiesService.getCatalogEntity(idEncode(target.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "repo-slug"))));
        when(catalogEntitiesService.getCatalogItemEntity(encodedItemId("MYPROJECT", "repo-slug")))
                .thenReturn(Optional.of(itemCtx));

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"));

        assertThat(result).isPresent().contains(itemCtx);
    }

    @Test
    void findByCatalogItemSlug_whenProjectKeyMatchesCaseInsensitively_returnsItem() throws Exception {
        var itemCtx = mock(CatalogItemEntityContext.class);

        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "MYPROJECT");

        when(catalogEntitiesService.getCatalogEntity(idEncode(target.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "repo-slug"))));
        when(catalogEntitiesService.getCatalogItemEntity(encodedItemId("MYPROJECT", "repo-slug")))
                .thenReturn(Optional.of(itemCtx));

        assertThat(catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"))).isPresent();
    }

    @Test
    void findByCatalogItemSlug_whenRepoNameMatchesCaseInsensitively_returnsItem() throws Exception {
        var itemCtx = mock(CatalogItemEntityContext.class);

        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "MYPROJECT");

        when(catalogEntitiesService.getCatalogEntity(idEncode(target.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "repo-slug"))));
        when(catalogEntitiesService.getCatalogItemEntity(encodedItemId("MYPROJECT", "repo-slug")))
                .thenReturn(Optional.of(itemCtx));

        assertThat(catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_REPO-SLUG"))).isPresent();
    }

    @Test
    void findByCatalogItemSlug_whenItemFoundInSecondCatalog_returnsIt() throws Exception {
        var itemCtx = mock(CatalogItemEntityContext.class);

        var target1 = catalogTargetWithUrl("https://catalog-url/catalog1");
        var target2 = catalogTargetWithUrl("https://catalog-url/catalog2");
        var collection = collectionWithTargets(target1, target2);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target1, "MYPROJECT");
        mockCatalogPathAt(target2, "MYPROJECT");

        // First catalog has a non-matching item
        when(catalogEntitiesService.getCatalogEntity(idEncode(target1.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "other-repo"))));
        // Second catalog has the matching item
        when(catalogEntitiesService.getCatalogEntity(idEncode(target2.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "repo-slug"))));
        when(catalogEntitiesService.getCatalogItemEntity(encodedItemId("MYPROJECT", "repo-slug")))
                .thenReturn(Optional.of(itemCtx));

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"));

        assertThat(result).isPresent().contains(itemCtx);
        verify(catalogEntitiesService).getCatalogEntity(idEncode(target1.getUrl()));
        verify(catalogEntitiesService).getCatalogEntity(idEncode(target2.getUrl()));
    }

    @Test
    void findByCatalogItemSlug_whenItemProjectKeyDoesNotMatchSlug_returnsEmpty() throws Exception {
        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "MYPROJECT");

        when(catalogEntitiesService.getCatalogEntity(idEncode(target.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("DIFFERENTPROJECT", "repo-slug"))));

        assertThat(catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"))).isEmpty();
        verify(catalogEntitiesService, never()).getCatalogItemEntity(any());
    }

    @Test
    void findByCatalogItemSlug_whenNoItemMatchesRepoName_returnsEmpty() throws Exception {
        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "MYPROJECT");

        when(catalogEntitiesService.getCatalogEntity(idEncode(target.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "other-repo"))));

        assertThat(catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"))).isEmpty();
        verify(catalogEntitiesService, never()).getCatalogItemEntity(any());
    }

    @Test
    void findByCatalogItemSlug_whenCatalogEntityAbsent_returnsEmpty() throws Exception {
        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "MYPROJECT");
        when(catalogEntitiesService.getCatalogEntity(idEncode(target.getUrl()))).thenReturn(Optional.empty());

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"));

        assertThat(result).isEmpty();
        verify(catalogEntitiesService, never()).getCatalogItemEntity(any());
    }

    @Test
    void findByCatalogItemSlug_whenCatalogProjectKeyDoesNotMatchSlug_skipsItemsQuery() throws Exception {
        var target = catalogTargetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target, "DIFFERENTPROJECT");

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"));

        assertThat(result).isEmpty();
        verifyNoInteractions(catalogEntitiesService);
    }

    @Test
    void findByCatalogItemSlug_whenMatchFoundInFirstCatalog_doesNotQuerySecondCatalog() throws Exception {
        var itemCtx = mock(CatalogItemEntityContext.class);

        var target1 = catalogTargetWithUrl("https://catalog-url/catalog1");
        var target2 = catalogTargetWithUrl("https://catalog-url/catalog2");
        var collection = collectionWithTargets(target1, target2);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        mockCatalogPathAt(target1, "MYPROJECT");

        when(catalogEntitiesService.getCatalogEntity(idEncode(target1.getUrl())))
                .thenReturn(Optional.of(catalogEntityWithItemUrl(bitbucketItemUrl("MYPROJECT", "repo-slug"))));
        when(catalogEntitiesService.getCatalogItemEntity(encodedItemId("MYPROJECT", "repo-slug")))
                .thenReturn(Optional.of(itemCtx));

        catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_repo-slug"));

        verify(catalogEntitiesService, never()).getCatalogEntity(idEncode(target2.getUrl()));
    }


    /**
     * Returns a real Bitbucket-format item URL that BitbucketPathAt will parse to the given
     * projectKey and repoSlug. This is the URL that appears in CatalogEntityTarget.url.
     */
    private static String bitbucketItemUrl(String projectKey, String repoSlug) {
        return BASE_RAW_URL + "/projects/" + projectKey + "/repos/" + repoSlug + "/raw/catalog.yaml?at=refs/heads/main";
    }

    /**
     * Returns the encoded item ID the service derives from itemPathAt.getPathAt() after parsing
     * bitbucketItemUrl(projectKey, repoSlug) through BitbucketPathAt.
     */
    private static String encodedItemId(String projectKey, String repoSlug) {
        return idEncode("projects/" + projectKey + "/repos/" + repoSlug + "/raw/catalog.yaml?at=refs/heads/main");
    }

    private static CatalogsCollectionsEntityTarget catalogTargetWithUrl(String url) {
        var target = new CatalogsCollectionsEntityTarget();
        target.setUrl(url);
        target.setSlug("a-slug");
        return target;
    }

    private static CatalogsCollectionsEntity collectionWithTargets(CatalogsCollectionsEntityTarget... targets) {
        var collection = CatalogsCollectionsEntityMother.of();
        collection.getMetadata().getSpec().setTargets(targets);
        return collection;
    }

    private static CatalogEntity catalogEntityWithItemUrl(String itemUrl) {
        var entity = CatalogEntityMother.of();
        var itemTarget = new CatalogEntityTarget();
        itemTarget.setUrl(itemUrl);
        entity.getMetadata().getSpec().setTargets(new CatalogEntityTarget[]{itemTarget});
        return entity;
    }

    private void mockCatalogPathAt(CatalogsCollectionsEntityTarget target, String projectKey) throws Exception {
        var catalogPathAt = mock(BitbucketPathAt.class);
        when(catalogPathAt.getProjectKey()).thenReturn(projectKey);
        when(catalogServiceAdapter.bitbucketPathAtFromId(idEncode(target.getUrl()))).thenReturn(catalogPathAt);
    }
}
