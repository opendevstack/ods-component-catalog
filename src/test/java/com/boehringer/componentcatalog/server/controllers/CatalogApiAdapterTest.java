package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.model.CatalogItem;
import com.boehringer.componentcatalog.server.model.CatalogItemFilter;
import com.boehringer.componentcatalog.server.mother.CatalogEntityContextMother;
import com.boehringer.componentcatalog.server.mother.CatalogEntityMother;
import com.boehringer.componentcatalog.server.mother.CatalogsCollectionsEntityMother;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityContext;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityMetadata;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityPermissionEnum;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntitySpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Base64.getEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogApiAdapterTest {

    CatalogEntity catalogEntity;
    
    @BeforeEach
    void setUp() {
        var spec = new CatalogEntitySpec();
        var metadata = new CatalogEntityMetadata();
        catalogEntity = new CatalogEntity();

        spec.setTags(Arrays.asList("catalogLabel1", "catalogLabel2", "catalogLabel3"));
        metadata.setSpec(spec);
        catalogEntity.setMetadata(metadata);
    }

    CatalogItemEntityContext repoItemCtxFixture() {
        CatalogItemEntityContext repoItemCtx = mock(CatalogItemEntityContext.class);

        // Mandatory fields
        when(repoItemCtx.getCatalogItemId()).thenReturn("id");
        when(repoItemCtx.getName()).thenReturn("name");
        when(repoItemCtx.getRepoItemPath()).thenReturn("path");
        when(repoItemCtx.getRepoItemSrc()).thenReturn("src");
        when(repoItemCtx.getRepoItemTags()).thenReturn(Map.of("label", Set.of("option")));
        when(repoItemCtx.getShortDescription()).thenReturn("shortDescription");
        when(repoItemCtx.getRepoLastCommitDateUTC()).thenReturn(OffsetDateTime.parse("2000-01-01T00:00Z"));

        // Optional fields
        when(repoItemCtx.getContributors()).thenReturn(List.of("author"));
        when(repoItemCtx.getDescriptionPath()).thenReturn("descriptionPath");
        when(repoItemCtx.getImagePath()).thenReturn("imagePath");

        return repoItemCtx;
    }


    @Test
    void asCatalogItem_withPermissions() {
        Set<CatalogEntityPermissionEnum> principalPermissions = Set.of(CatalogEntityPermissionEnum.REPO_READ);
        CatalogItemEntityContext repoItemCtx = repoItemCtxFixture();
        CatalogItem item = CatalogApiAdapter.asCatalogItem(repoItemCtx, principalPermissions);

        // Mandatory fields
        assertEquals("id", item.getId());
        assertEquals("name", item.getTitle());
        assertEquals("shortDescription", item.getShortDescription());
        assertEquals("src", item.getItemSrc());
        assertEquals("path", item.getPath());
        assertEquals(1, item.getTags().size());
        assertEquals("2000-01-01T00:00Z", item.getDate().toString());

        // Optional fields
        assertEquals(1, item.getAuthors().size());
        assertEquals("author", item.getAuthors().get(0));

        // Assert ids encoding
        var descriptionFileId = getEncoder().encodeToString("descriptionPath".getBytes());
        var imageFileId = getEncoder().encodeToString("imagePath".getBytes());

        assertEquals(descriptionFileId, item.getDescriptionFileId());
        assertEquals(imageFileId, item.getImageFileId());
    }

    @Test
    void asCatalogItem_withoutPermissions() {
        Set<CatalogEntityPermissionEnum> principalPermissions = Set.of();
        CatalogItemEntityContext repoItemCtx = repoItemCtxFixture();

        CatalogItem item = CatalogApiAdapter.asCatalogItem(repoItemCtx, principalPermissions);

        // Removed fields due to lack of permissions
        assertNull(item.getItemSrc());
        assertNull(item.getPath());

        // Mandatory fields
        assertEquals("id", item.getId());
        assertEquals("name", item.getTitle());
        assertEquals("shortDescription", item.getShortDescription());
        assertEquals(1, item.getTags().size());
        assertEquals("2000-01-01T00:00Z", item.getDate().toString());

        // Optional fields
        assertEquals(1, item.getAuthors().size());
        assertEquals("author", item.getAuthors().get(0));

        // Assert ids encoding
        var descriptionFileId = getEncoder().encodeToString("descriptionPath".getBytes());
        var imageFileId = getEncoder().encodeToString("imagePath".getBytes());

        assertEquals(descriptionFileId, item.getDescriptionFileId());
        assertEquals(imageFileId, item.getImageFileId());
    }

    @Test
    void catalogItemFiltersFrom_withMatchingLabels() {
        CatalogItemEntityContext repoItemCtx1 = repoItemCtxFixture();
        CatalogItemEntityContext repoItemCtx2 = repoItemCtxFixture();
        CatalogItemEntityContext repoItemCtx3 = repoItemCtxFixture();

        when(repoItemCtx1.getRepoItemTags()).thenReturn(Map.of("catalogLabel1", Set.of("option1")));
        when(repoItemCtx2.getRepoItemTags()).thenReturn(Map.of(
                "catalogLabel2", Set.of("option2"),
                "catalogLabel3", Set.of("option3")
        ));

        when(repoItemCtx3.getRepoItemTags()).thenReturn(Map.of("catalogLabel3", Set.of("option3", "option4")));

        List<CatalogItemFilter> filters = CatalogApiAdapter.catalogItemFiltersFrom(catalogEntity,
                List.of(repoItemCtx2, repoItemCtx1, repoItemCtx3), // verify sorting by label
                Set.of(CatalogEntityPermissionEnum.REPO_READ));

        assertEquals(3, filters.size());

        // Filters should match the order defined in catalog labels
        var labels = filters.stream()
                .map(CatalogItemFilter::getLabel)
                .toArray();

        assertArrayEquals(
                new String[]{"catalogLabel1", "catalogLabel2", "catalogLabel3"},
                labels);

        var filter1 = filters.get(0);
        var filter2 = filters.get(1);
        var filter3 = filters.get(2);

        assertEquals("catalogLabel1", filter1.getLabel());
        assertEquals(1, filter1.getOptions().size());
        assertTrue(filter1.getOptions().contains("option1"));

        assertEquals("catalogLabel2", filter2.getLabel());
        assertEquals(1, filter2.getOptions().size());
        assertTrue(filter2.getOptions().contains("option2"));

        assertEquals("catalogLabel3", filter3.getLabel());
        assertEquals(2, filter3.getOptions().size());
        assertTrue(filter3.getOptions().containsAll(Set.of("option3", "option4")));
    }

    @Test
    void catalogItemFiltersFrom_withoutMatchingLabels() {
        CatalogItemEntityContext repoItemCtx1 = repoItemCtxFixture();
        CatalogItemEntityContext repoItemCtx2 = repoItemCtxFixture();

        when(repoItemCtx1.getRepoItemTags()).thenReturn(Map.of("nonCatalogLabel", Set.of("missingOption")));
        when(repoItemCtx2.getRepoItemTags()).thenReturn(Map.of("catalogLabel1", Set.of("option1")));

        List<CatalogItemFilter> filters = CatalogApiAdapter.catalogItemFiltersFrom(catalogEntity,
                List.of(repoItemCtx1, repoItemCtx2),
                Set.of(CatalogEntityPermissionEnum.REPO_READ));

        assertEquals(1, filters.size());

        Set<String> actual = filters.get(0).getOptions();

        assertEquals(1, actual.size());
        assertTrue(actual.contains("option1"));
    }

    @Test
    void givenACatalogsCollectionsEntity_whenAsCatalogDescriptors_thenAListOfCatalogsIsReturned() {
        // given
        var catalogsCollectionsEntity = CatalogsCollectionsEntityMother.of();

        // when
        var catalogs = CatalogApiAdapter.asCatalogDescriptors(catalogsCollectionsEntity);

        // then
        assertThat(catalogs).isNotNull();
        assertThat(catalogs.size()).isEqualTo(2);
        assertThat(catalogs.get(0).getId()).isEqualTo("L3BhdGgvdG8vY2F0YWxvZzE=");
        assertThat(catalogs.get(0).getSlug()).isEqualTo("catalog1");
        assertThat(catalogs.get(1).getId()).isEqualTo("L3BhdGgvdG8vY2F0YWxvZzI=");
        assertThat(catalogs.get(1).getSlug()).isEqualTo("catalog2");
    }

    @Test
    void givenACatalogContext_whenAsCatalog_thenReturnCatalog() {
        // given
        var catalogEntityContext = CatalogEntityContextMother.of();

        // when
        var catalog = CatalogApiAdapter.asCatalog(catalogEntityContext);

        // then
        assertThat(catalog).isNotNull();
        assertThat(catalog.getName()).isEqualTo("Catalog Name");
        assertThat(catalog.getDescription()).isEqualTo("Catalog Description");
        assertThat(catalog.getCommunityPageId()).isEqualTo("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL1NvbWVGaWxlT3JEaXI_YXQ9cmVmcy9oZWFkcy9tYXN0ZXI=");
        assertThat(catalog.getLinks()).hasSize(3);
        assertThat(catalog.getTags()).hasSize(2);

    }
}