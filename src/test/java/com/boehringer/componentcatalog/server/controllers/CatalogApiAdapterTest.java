package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.model.CatalogItem;
import com.boehringer.componentcatalog.server.model.CatalogItemFilter;
import com.boehringer.componentcatalog.server.model.CatalogItemUserAction;
import com.boehringer.componentcatalog.server.mother.*;
import com.boehringer.componentcatalog.server.services.catalog.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void asCatalogItem_withPermissions() {
        Set<CatalogEntityPermissionEnum> principalPermissions = Set.of(CatalogEntityPermissionEnum.REPO_READ);
        CatalogItemEntityContext repoItemCtx = repoItemCtxFixture();
        UserActionEntity codeUserAction = UserActionEntityMother.of("CODE",
                "Code Action 1",
                "http://code.action1-url",
                "Code Trigger message 1",
                true,
                UserActionEntityParameterMother.ofArray());

        UserActionEntity provisionUserAction = UserActionEntityMother.of("PROVISION",
                "Provision Action 1",
                "http://provision.action1-url",
                "Provision Trigger message 1",
                true,
                UserActionEntityParameterMother.ofArray());

        UserActionEntity extraUserAction = UserActionEntityMother.of();

        UserActionsEntity repoUserActions = UserActionsEntityMother.of(List.of(codeUserAction, provisionUserAction, extraUserAction));

        CatalogItem item = CatalogApiAdapter.asCatalogItem(repoItemCtx, repoUserActions, principalPermissions);

        // Mandatory fields
        Optional<CatalogItemUserAction> codeAction = item.getUserActions().stream()
                .filter(ua -> Objects.equals("CODE", ua.getId()))
                .findFirst();

        assertThat(item.getId()).isEqualTo("id");
        assertThat(item.getTitle()).isEqualTo("Appshell in Angular");
        assertThat(item.getShortDescription()).isEqualTo("Quickstart template to boost the development of web applications on the EDP.");

        assertThat(codeAction).isPresent();

        assertThat(codeAction.get().getUrl().get()).isEqualTo("src");
        assertThat(item.getTags()).hasSize(3);
        assertThat(item.getDate()).isEqualTo(OffsetDateTime.parse("2000-01-01T00:00Z"));

        // Optional fields
        assertThat(item.getAuthors()).hasSize(1);
        assertThat(item.getAuthors()).contains("author");

        // Assert ids encoding
        // Source of the token: id(repoItemCtx.descriptionPath)
        assertThat(item.getDescriptionFileId()).isEqualTo("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2Rlc2NyaXB0aW9uUGF0aD9hdD1yZWZzL2hlYWRzL21hc3Rlcg==");
        // Source of the token:  id(repoItemCtx.imagePath)
        assertThat(item.getImageFileId()).isEqualTo("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2ltYWdlUGF0aD9hdD1yZWZzL2hlYWRzL21hc3Rlcg==");

        assertThat(item.getUserActions()).hasSize(3);
        assertThat(item.getUserActions().get(0).getId()).isEqualTo("CODE");
        assertThat(item.getUserActions().get(1).getId()).isEqualTo("PROVISION");
        assertThat(item.getUserActions().get(2).getId()).isEqualTo("ACTION_ID_1");
    }

    @Test
    void asCatalogItem_withoutPermissions() {
        Set<CatalogEntityPermissionEnum> principalPermissions = Set.of();
        CatalogItemEntityContext repoItemCtx = repoItemCtxFixture();

        UserActionsEntity repoUserActions = UserActionsEntityMother.of();
        CatalogItem item = CatalogApiAdapter.asCatalogItem(repoItemCtx, repoUserActions, principalPermissions);

        Optional<CatalogItemUserAction> codeAction = item.getUserActions().stream()
                .filter(ua -> Objects.equals("CODE", ua.getId()))
                .findFirst();

        // Removed fields due to lack of permissions
        assertNull(item.getItemSrc());
        assertNull(item.getPath());

        // Mandatory fields
        assertEquals("id", item.getId());
        assertEquals("Appshell in Angular", item.getTitle());
        assertEquals("Quickstart template to boost the development of web applications on the EDP.", item.getShortDescription());
        assertThat(codeAction).isPresent();
        assertNull(codeAction.get().getUrl().get());
        assertEquals(3, item.getTags().size());
        assertEquals("2000-01-01T00:00Z", item.getDate().toString());

        // Optional fields
        assertEquals(1, item.getAuthors().size());
        assertEquals("author", item.getAuthors().get(0));

        // Assert ids encoding
        // id(repoItemCtx.descriptionPath)
        assertEquals("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2Rlc2NyaXB0aW9uUGF0aD9hdD1yZWZzL2hlYWRzL21hc3Rlcg==", item.getDescriptionFileId());
        // id(repoItemCtx.imagePath)
        assertEquals("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2ltYWdlUGF0aD9hdD1yZWZzL2hlYWRzL21hc3Rlcg==", item.getImageFileId());
    }

    @Test
    void catalogItemFiltersFrom_withMatchingLabels() {
        CatalogItemEntity catalogItemEntity1 = catalogItemEntityFixture(Map.of(
                "catalogLabel1", Set.of("option1")
        ));

        CatalogItemEntity catalogItemEntity2 = catalogItemEntityFixture(Map.of(
                "catalogLabel2", Set.of("option2"),
                "catalogLabel3", Set.of("option3")
        ));

        CatalogItemEntity catalogItemEntity3 = catalogItemEntityFixture(Map.of(
                "catalogLabel3", Set.of("option3", "option4")
        ));

        CatalogItemEntityContext repoItemCtx1 = repoItemCtxFixture(catalogItemEntity1);
        CatalogItemEntityContext repoItemCtx2 = repoItemCtxFixture(catalogItemEntity2);
        CatalogItemEntityContext repoItemCtx3 = repoItemCtxFixture(catalogItemEntity3);

        UserActionsEntity userActionsEntity = UserActionsEntityMother.of();

        List<CatalogItemFilter> filters = CatalogApiAdapter.catalogItemFiltersFrom(
                catalogEntity,
                List.of(repoItemCtx2, repoItemCtx1, repoItemCtx3), // verify sorting by label
                userActionsEntity, Set.of(CatalogEntityPermissionEnum.REPO_READ)
        );

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
        CatalogItemEntity catalogItemEntity1 = catalogItemEntityFixture(Map.of("nonCatalogLabel", Set.of("missingOption")));

        CatalogItemEntity catalogItemEntity2 = catalogItemEntityFixture(Map.of("catalogLabel1", Set.of("option1")));

        CatalogItemEntityContext repoItemCtx1 = repoItemCtxFixture(catalogItemEntity1);
        CatalogItemEntityContext repoItemCtx2 = repoItemCtxFixture(catalogItemEntity2);

        UserActionsEntity userActionsEntity = UserActionsEntityMother.of();

        List<CatalogItemFilter> filters = CatalogApiAdapter.catalogItemFiltersFrom(catalogEntity,
                List.of(repoItemCtx1, repoItemCtx2),
                userActionsEntity, Set.of(CatalogEntityPermissionEnum.REPO_READ));

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
        assertThat(catalogs).hasSize(2);
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

    @Test
    void givenTwoCustomUserActionEntities_andThreeDefaultUserActionEntities_andSomeActionsMerge_whenFinalizeUserActions_thenReturnFourUserActionEntities() {
        // given
        CatalogItemUserAction customUserActionProvision = CatalogItemUserActionMother.of()
                .id("PROVISION")
                .displayName("Provision")
                .url("https://component-provisioner-devstack-dev.apps.eu-dev.ocp.aws.boehringer.com/v1/provision-actions")
                .triggerMessage("is being provisioned, it will take some minutes");

        CatalogItemUserAction customUserActionTest = CatalogItemUserActionMother.of()
                .id("TEST")
                .displayName("Test Action")
                .url(null)
                .triggerMessage(null);

        UserActionEntity defaultUserActionCode = UserActionEntityMother.of("CODE",
                "View Code",
                null,
                null,
                true,
                UserActionEntityParameterMother.ofArray());

        UserActionEntity defaultUserActionProvision = UserActionEntityMother.of("PROVISION",
                "Provision",
                "https://component-provisioner-devstack-dev.apps.eu-dev.ocp.aws.boehringer.com/v1/provision-actions",
                "is being provisioned, it will take some minutes",
                false,
                UserActionEntityParameterMother.ofArray());

        UserActionEntity defaultUserActionDummy = UserActionEntityMother.of("DUMMY",
                "Dummy Action",
                "https://component-provisioner-devstack-dev.apps.eu-dev.ocp.aws.boehringer.com/v1/provision-actions",
                "is being provisioned, it will take some minutes",
                false,
                UserActionEntityParameterMother.ofArray());

        List<CatalogItemUserAction> customUserActions = List.of(customUserActionProvision, customUserActionTest);
        UserActionEntity[] defaultUserActions = {defaultUserActionCode, defaultUserActionProvision, defaultUserActionDummy};

        // when
        var mergedUserActions = CatalogApiAdapter.finalizeUserActions(customUserActions, defaultUserActions);

        // then
        assertThat(mergedUserActions).hasSize(2);
        assertThat(mergedUserActions).extracting("id").containsExactlyInAnyOrder("CODE", "PROVISION");
        assertThat(mergedUserActions).extracting("id").doesNotContain("DUMMY"); // It is not appearing, even when on default actions, because mandatory = false
        assertThat(mergedUserActions).extracting("id").doesNotContain("TEST"); // It is not appearing, because it is not defined in mandatory. We ignore custom actions that are not on default actions, so we can keep control.
    }

    private CatalogItemEntity catalogItemEntityFixture(Map<String, Set<String>> tags) {
        CatalogItemEntityMetadata metadata = CatalogItemEntityMetadataMother.of(
                "name", "shortDescription", tags);

        return CatalogItemEntityMother.of(metadata);
    }

    private CatalogItemEntityContext repoItemCtxFixture(CatalogItemEntity catalogItemEntity) {
        // Source code url is set on the special "CODE" user action, should always be present
        // due to Mother building a formally correct CatalogItemEntity with proper user actions.
        CatalogItemEntityUserAction codeAction = Stream.of(catalogItemEntity.getSpec().getUserActions())
                .filter(ua -> Objects.equals("CODE", ua.getId()))
                .findFirst()
                .orElse(null);

        codeAction.setUrl("src");

        return CatalogItemEntityContextMother.of(
                "id",
                catalogItemEntity,
                OffsetDateTime.parse("2000-01-01T00:00Z"),
                List.of("author"),
                BitbucketPathAtMother.of("descriptionPath"),
                BitbucketPathAtMother.of("imagePath")
        );
    }

    CatalogItemEntityContext repoItemCtxFixture() {
        return repoItemCtxFixture(CatalogItemEntityMother.of());
    }
}