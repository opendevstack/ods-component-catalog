package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.mother.CatalogsCollectionsEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;

@ExtendWith(MockitoExtension.class)
class CatalogItemBySlugServiceTest {

    @Mock
    private CatalogsCollectionService catalogsCollectionService;

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

    @InjectMocks
    private CatalogItemBySlugService catalogItemBySlugService;

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
        // BitbucketPathAtMother.of() gives projectKey="MYPROJECT", repoSlug="repo-slug"
        var repoCatalogItemPathAt = BitbucketPathAtMother.of();
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target = targetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));

        var catalogId = idEncode(target.getUrl());
        when(catalogEntitiesService.getCatalogItemsEntities(catalogId)).thenReturn(List.of(itemCtx));

        // MYPROJECT normalises to "myproject"; repo-slug matches case-insensitively
        var slug = CatalogItemSlug.parse("myproject_repo-slug");
        var result = catalogItemBySlugService.findByCatalogItemSlug(slug);

        assertThat(result).isPresent().contains(itemCtx);
    }

    @Test
    void findByCatalogItemSlug_whenProjectKeyMatchesCaseInsensitively_returnsItem() throws Exception {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of(); // projectKey=MYPROJECT
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target = targetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));

        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target.getUrl()))).thenReturn(List.of(itemCtx));

        // "MYPROJECT" normalises to "myproject" — slug uses lowercase which is what normalise produces
        var slug = CatalogItemSlug.parse("myproject_repo-slug");
        assertThat(catalogItemBySlugService.findByCatalogItemSlug(slug)).isPresent();
    }

    @Test
    void findByCatalogItemSlug_whenRepoNameMatchesCaseInsensitively_returnsItem() throws Exception {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of(); // repoSlug="repo-slug"
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target = targetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));

        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target.getUrl()))).thenReturn(List.of(itemCtx));

        var slug = CatalogItemSlug.parse("myproject_REPO-SLUG");
        assertThat(catalogItemBySlugService.findByCatalogItemSlug(slug)).isPresent();
    }

    @Test
    void findByCatalogItemSlug_whenItemFoundInSecondCatalog_returnsIt() throws Exception {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of();
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target1 = targetWithUrl("https://catalog-url/catalog1");
        var target2 = targetWithUrl("https://catalog-url/catalog2");
        var collection = collectionWithTargets(target1, target2);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));

        // First catalog has no matching items
        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target1.getUrl()))).thenReturn(List.of());
        // Second catalog has the matching item
        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target2.getUrl()))).thenReturn(List.of(itemCtx));

        var slug = CatalogItemSlug.parse("myproject_repo-slug");
        var result = catalogItemBySlugService.findByCatalogItemSlug(slug);

        assertThat(result).isPresent().contains(itemCtx);
        verify(catalogEntitiesService).getCatalogItemsEntities(idEncode(target1.getUrl()));
        verify(catalogEntitiesService).getCatalogItemsEntities(idEncode(target2.getUrl()));
    }

    @Test
    void findByCatalogItemSlug_whenNoItemMatchesProjectKey_returnsEmpty() throws Exception {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of(); // projectKey=MYPROJECT
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target = targetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target.getUrl()))).thenReturn(List.of(itemCtx));

        var slug = CatalogItemSlug.parse("otherproject_repo-slug");
        assertThat(catalogItemBySlugService.findByCatalogItemSlug(slug)).isEmpty();
    }

    @Test
    void findByCatalogItemSlug_whenNoItemMatchesRepoName_returnsEmpty() throws Exception {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of(); // repoSlug="repo-slug"
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target = targetWithUrl("https://catalog-url/catalog1");
        var collection = collectionWithTargets(target);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target.getUrl()))).thenReturn(List.of(itemCtx));

        var slug = CatalogItemSlug.parse("myproject_other-repo");
        assertThat(catalogItemBySlugService.findByCatalogItemSlug(slug)).isEmpty();
    }

    @Test
    void findByCatalogItemSlug_whenAllCatalogsEmpty_returnsEmpty() throws Exception {
        var target1 = targetWithUrl("https://catalog-url/catalog1");
        var target2 = targetWithUrl("https://catalog-url/catalog2");
        var collection = collectionWithTargets(target1, target2);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        when(catalogEntitiesService.getCatalogItemsEntities(any())).thenReturn(List.of());

        var result = catalogItemBySlugService.findByCatalogItemSlug(CatalogItemSlug.parse("myproject_my-repo"));

        assertThat(result).isEmpty();
    }

    @Test
    void findByCatalogItemSlug_whenMatchFoundInFirstCatalog_doesNotQuerySecondCatalog() throws Exception {
        var repoCatalogItemPathAt = BitbucketPathAtMother.of();
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getRepoCatalogItemPathAt()).thenReturn(repoCatalogItemPathAt);

        var target1 = targetWithUrl("https://catalog-url/catalog1");
        var target2 = targetWithUrl("https://catalog-url/catalog2");
        var collection = collectionWithTargets(target1, target2);
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(collection));
        when(catalogEntitiesService.getCatalogItemsEntities(idEncode(target1.getUrl()))).thenReturn(List.of(itemCtx));

        var slug = CatalogItemSlug.parse("myproject_repo-slug");
        catalogItemBySlugService.findByCatalogItemSlug(slug);

        verify(catalogEntitiesService, never()).getCatalogItemsEntities(idEncode(target2.getUrl()));
    }

    private static CatalogsCollectionsEntityTarget targetWithUrl(String url) {
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
}
