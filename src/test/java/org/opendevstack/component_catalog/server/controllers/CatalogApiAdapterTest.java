package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionMapper;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionParameterMapper;
import org.opendevstack.component_catalog.server.mappers.EntitiesMapper;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.mother.*;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.*;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CatalogApiAdapterTest {

    public static final String PARAMETER_NON_CUSTOMIZABLE_NAME = "parameter-non-Customizable";
    private CatalogApiAdapter catalogApiAdapter;

    CatalogEntity defaultCatalogEntity;
    
    @BeforeEach
    void setUp() {
        var spec = new CatalogEntitySpec();
        var metadata = new CatalogEntityMetadata();
        defaultCatalogEntity = new CatalogEntity();

        spec.setTags(Arrays.asList("catalogLabel1", "catalogLabel2", "catalogLabel3"));
        metadata.setSpec(spec);
        defaultCatalogEntity.setMetadata(metadata);

        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> Pair.of(true, "");

        var groupsRestrictionProps = ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        var catalogItemUserActionParameterMapper = new CatalogItemUserActionParameterMapper();
        var catalogItemUserActionMapper = new CatalogItemUserActionMapper(catalogItemUserActionParameterMapper, List.of(dummyEvaluator), groupsRestrictionProps);
        var entitiesMapper = new EntitiesMapper(catalogItemUserActionMapper);

        catalogApiAdapter = new CatalogApiAdapter(entitiesMapper, catalogItemUserActionParameterMapper, catalogItemUserActionMapper);
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

        var projectKey = Strings.EMPTY;
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(repoItemCtx)
                .userActionsEntity(repoUserActions)
                .permissions(principalPermissions)
                .projectKey(projectKey)
                .build();

        CatalogItem item = catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups);

        // Mandatory fields
        Optional<CatalogItemUserAction> codeActionOnItem = item.getUserActions().stream()
                .filter(ua -> Objects.equals("CODE", ua.getId()))
                .findFirst();

        assertThat(item.getId()).isEqualTo("id");
        assertThat(item.getTitle()).isEqualTo("Appshell in Angular");
        assertThat(item.getShortDescription()).isEqualTo("Quickstart template to boost the development of web applications on the EDP.");

        assertThat(codeActionOnItem).isPresent();

        assertThat(codeActionOnItem.get().getUrl().get()).isEqualTo("src");
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

        var projectKey = Strings.EMPTY;
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(repoItemCtx)
                .userActionsEntity(repoUserActions)
                .permissions(principalPermissions)
                .projectKey(projectKey)
                .build();

        CatalogItem item = catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups);

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
        assertEquals("author", item.getAuthors().getFirst());

        // Assert ids encoding
        // id(repoItemCtx.descriptionPath)
        assertEquals("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2Rlc2NyaXB0aW9uUGF0aD9hdD1yZWZzL2hlYWRzL21hc3Rlcg==", item.getDescriptionFileId());
        // id(repoItemCtx.imagePath)
        assertEquals("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2ltYWdlUGF0aD9hdD1yZWZzL2hlYWRzL21hc3Rlcg==", item.getImageFileId());
    }

    @Test
    void givenRequestParams_AndRestrictions_whenAsCatalogItem_thenRestrictionsAreMapped() {
        // given
        var projects = List.of("a", "b");

        var restrictions = CatalogItemEntityRestrictionsMother.of(projects);

        var catalogRequestParams = CatalogRequestParamsMother.of(restrictions);
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        // when
        var catalogItem = catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups);

        // then
        assertThat(catalogItem.getRestrictions().getProjects()).isEqualTo(new HashSet<>(projects));
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

        CatalogEntity catalogEntity = catalogEntityFixture(List.of("catalogLabel1", "catalogLabel2", "catalogLabel3"));

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();
        var itemEntitiesCtxs = List.of(repoItemCtx2, repoItemCtx1, repoItemCtx3); // verify sorting by label
        var permissions = Set.of(CatalogEntityPermissionEnum.REPO_READ);
        var projectKey = Strings.EMPTY;

        var catalogItemRequestParams = CatalogRequestParams.builder()
                .catalogEntity(catalogEntity)
                .catalogItemEntityContextList(itemEntitiesCtxs)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .build();

        List<CatalogItemFilter> filters = catalogApiAdapter.catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups);

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

        CatalogEntity catalogEntity = catalogEntityFixture(List.of("catalogLabel1"));

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();
        var itemEntitiesCtxs = List.of(repoItemCtx1, repoItemCtx2);
        var permissions = Set.of(CatalogEntityPermissionEnum.REPO_READ);
        var projectKey = Strings.EMPTY;

        var catalogItemRequestParams = CatalogRequestParams.builder()
                .catalogEntity(catalogEntity)
                .catalogItemEntityContextList(itemEntitiesCtxs)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .build();

        List<CatalogItemFilter> filters = catalogApiAdapter.catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups);

        assertEquals(1, filters.size());

        Set<String> actual = filters.getFirst().getOptions();

        assertEquals(1, actual.size());
        assertTrue(actual.contains("option1"));
    }

    @Test
    void givenACatalogsCollectionsEntity_whenAsCatalogDescriptors_thenAListOfCatalogsIsReturned() {
        // given
        var catalogsCollectionsEntity = CatalogsCollectionsEntityMother.of();

        // when
        var catalogs = catalogApiAdapter.asCatalogDescriptors(catalogsCollectionsEntity);

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
        var catalog = catalogApiAdapter.asCatalog(catalogEntityContext);

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
                .url("https://component-provisioner-devstack-dev.openshift.com/v1/provision-actions")
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
                "https://component-provisioner-devstack-dev.openshift.com/v1/provision-actions",
                "is being provisioned, it will take some minutes",
                false,
                UserActionEntityParameterMother.ofArray());

        UserActionEntity defaultUserActionDummy = UserActionEntityMother.of("DUMMY",
                "Dummy Action",
                "https://component-provisioner-devstack-dev.openshift.com/v1/provision-actions",
                "is being provisioned, it will take some minutes",
                false,
                UserActionEntityParameterMother.ofArray());

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();
        var projectKey = Strings.EMPTY;
        var catalogItemId = Strings.EMPTY;

        List<CatalogItemUserAction> customUserActions = List.of(customUserActionProvision, customUserActionTest);
        UserActionEntity[] defaultUserActions = {defaultUserActionCode, defaultUserActionProvision, defaultUserActionDummy};

        // when
        var mergedUserActions = catalogApiAdapter.finalizeUserActions(
                customUserActions, defaultUserActions, clusters, userGroups, projectKey, catalogItemId);

        // then
        assertThat(mergedUserActions).hasSize(2);
        assertThat(mergedUserActions).extracting("id").containsExactlyInAnyOrder("CODE", "PROVISION");
        assertThat(mergedUserActions).extracting("id").doesNotContain("DUMMY"); // It is not appearing, even when on default actions, because mandatory = false
        assertThat(mergedUserActions).extracting("id").doesNotContain("TEST"); // It is not appearing, because it is not defined in mandatory. We ignore custom actions that are not on default actions, so we can keep control.
    }

    @Test
    void givenACatalogItem_AndItemContainsParameters_AndOneParameterSharesNameWithANonCustomizableParameter_whenFinalizeUserActions_nonCustomizableParameterIsFilteredOut() {
        // given
        var customUserActionProvision = generateCustomUserActions();
        var defaultUserActionProvision = generateDefaultUserActionEntity();

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();
        var projectKey = Strings.EMPTY;
        var catalogItemId = Strings.EMPTY;

        List<CatalogItemUserAction> customUserActions = List.of(customUserActionProvision);
        UserActionEntity[] defaultUserActions = {defaultUserActionProvision};

        // when
        var mergedUserActions = catalogApiAdapter.finalizeUserActions(
                customUserActions, defaultUserActions, clusters, userGroups, projectKey, catalogItemId);

        // then
        assertThat(mergedUserActions).hasSize(1);

        var parameters =  mergedUserActions.getFirst().getParameters();

        assertThat(parameters).hasSize(3);
        var parameterNonCustomizable = parameters.stream().filter(p -> p.getName().equals(PARAMETER_NON_CUSTOMIZABLE_NAME)).findFirst();
        assertThat(parameterNonCustomizable).isPresent();
        assertThat(parameterNonCustomizable.get().getDefaultValue().get()).isEqualTo("123");
    }

    private UserActionEntity generateDefaultUserActionEntity() {
        var defaultParameter = UserActionEntityParameterMother.of();
        var defaultParameterNonCustomizable =  UserActionEntityParameterMother.of(PARAMETER_NON_CUSTOMIZABLE_NAME);

        var entityParameters = new UserActionEntityParameter[]{defaultParameter, defaultParameterNonCustomizable};

        return UserActionEntityMother.of("PROVISION",
                "Provision",
                "https://component-provisioner-devstack-dev.openshift.com/v1/provision-actions",
                "is being provisioned, it will take some minutes",
                false,
                entityParameters);
    }

    private CatalogItemUserAction generateCustomUserActions() {
        var customParameterToBeOverridden = CatalogItemUserActionParameterMother.of(PARAMETER_NON_CUSTOMIZABLE_NAME);
        var customParameter = CatalogItemUserActionParameterMother.of();
        List<CatalogItemUserActionParameter> itemUserActionParameters = List.of(customParameter, customParameterToBeOverridden);

        return CatalogItemUserActionMother.of(itemUserActionParameters)
                .id("PROVISION")
                .displayName("Provision")
                .url("https://component-provisioner-devstack-dev.openshift.com/v1/provision-actions")
                .triggerMessage("is being provisioned, it will take some minutes");
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

        if (codeAction != null) {
            codeAction.setUrl("src");
            codeAction.setRestrictions(UserActionEntityRestrictionsMother.of());
        }

        return CatalogItemEntityContextMother.of(
                "id",
                catalogItemEntity,
                OffsetDateTime.parse("2000-01-01T00:00Z"),
                List.of("author"),
                BitbucketPathAtMother.of("descriptionPath"),
                BitbucketPathAtMother.of("imagePath")
        );
    }

    private CatalogEntity catalogEntityFixture(List<String> labelsInCatalogYamlOrder) {
        CatalogEntity entity = new CatalogEntity();
        CatalogEntityMetadata metadata = new CatalogEntityMetadata();
        CatalogEntitySpec spec = new  CatalogEntitySpec();
        spec.setTags(labelsInCatalogYamlOrder);
        metadata.setSpec(spec);
        entity.setMetadata(metadata);
        return entity;
    }

    CatalogItemEntityContext repoItemCtxFixture() {
        return repoItemCtxFixture(CatalogItemEntityMother.of());
    }
}