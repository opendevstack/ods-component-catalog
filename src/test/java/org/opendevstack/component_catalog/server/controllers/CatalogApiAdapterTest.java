package org.opendevstack.component_catalog.server.controllers;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionMapper;
import org.opendevstack.component_catalog.server.mappers.CatalogItemUserActionParameterMapper;
import org.opendevstack.component_catalog.server.mappers.EntitiesMapper;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.mother.CatalogEntityContextMother;
import org.opendevstack.component_catalog.server.mother.CatalogItemEntityMetadataMother;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionMother;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;
import org.opendevstack.component_catalog.server.mother.CatalogsCollectionsEntityMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityParameterLocationMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityParameterMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityParameterValidationMother;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictionsMother;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserAction;
import org.opendevstack.component_catalog.server.services.filters.CatalogItemsFilter;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluatorResultMother;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> RestrictionsEvaluatorResultMother.of();

        var groupsRestrictionProps =
                ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        var catalogItemUserActionParameterMapper = new CatalogItemUserActionParameterMapper();
        var catalogItemUserActionMapper = new CatalogItemUserActionMapper(
                catalogItemUserActionParameterMapper,
                List.of(dummyEvaluator),
                groupsRestrictionProps
        );
        CatalogItemsFilter filter = Mockito.mock(CatalogItemsFilter.class);

        var entitiesMapper = new EntitiesMapper(catalogItemUserActionMapper, List.of(filter));

        catalogApiAdapter = new CatalogApiAdapter(
                entitiesMapper,
                catalogItemUserActionParameterMapper,
                catalogItemUserActionMapper
        );
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

        UserActionsEntity repoUserActions = UserActionsEntityMother.of(
                List.of(codeUserAction, provisionUserAction, extraUserAction)
        );

        var projectKey = Strings.EMPTY;
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(repoItemCtx)
                .userActionsEntity(repoUserActions)
                .permissions(principalPermissions)
                .projectKey(projectKey)
                .build();

        var componentCount = 5; // Example component count for testing
        var expectedUpdatedAt =
                catalogRequestParams.getCatalogItemEntityContext().getLastCommitDateUTC().toInstant().toEpochMilli();
        var expectedDescriptionFileId =
                "cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2Rlc2NyaXB0aW9uUGF0aD9h"
                        + "dD1yZWZzL2hlYWRzL21hc3Rlcg==";
        var expectedImageFileId =
                "cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2ltYWdlUGF0aD9hdD1yZWZzL2hl"
                        + "YWRzL21hc3Rlcg==";

        var item = catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);

        // updatedAt should be set from CatalogItemEntityContext.lastCommitDateUTC (milliseconds since epoch)
        assertThat(item.catalogItem().getUpdatedAt()).isEqualTo(expectedUpdatedAt);

        // Mandatory fields
        Optional<CatalogItemUserAction> codeActionOnItem = item.catalogItem().getUserActions().stream()
                .filter(ua -> Objects.equals("CODE", ua.getId()))
                .findFirst();

        assertThat(item.catalogItem().getId()).isEqualTo("id");
        assertThat(item.catalogItem().getTitle()).isEqualTo("Appshell in Angular");
        assertThat(item.catalogItem().getShortDescription())
                .isEqualTo("Quickstart template to boost the development of web applications on the EDP.");

        assertThat(codeActionOnItem).isPresent();

        assertThat(codeActionOnItem.get().getUrl().get()).isEqualTo("src");
        assertThat(item.catalogItem().getTags()).hasSize(3);
        assertThat(item.catalogItem().getDate()).isEqualTo(OffsetDateTime.parse("2000-01-01T00:00Z"));

        // Optional fields
        assertThat(item.catalogItem().getAuthors()).hasSize(1);
        assertThat(item.catalogItem().getAuthors()).contains("author");

        // Assert ids encoding
        // Source of the token: id(repoItemCtx.descriptionPath)
        assertThat(item.catalogItem().getDescriptionFileId()).isEqualTo(expectedDescriptionFileId);
        // Source of the token:  id(repoItemCtx.imagePath)
        assertThat(item.catalogItem().getImageFileId()).isEqualTo(expectedImageFileId);

        assertThat(item.catalogItem().getUserActions()).hasSize(3);
        assertThat(item.catalogItem().getUserActions().get(0).getId()).isEqualTo("CODE");
        assertThat(item.catalogItem().getUserActions().get(1).getId()).isEqualTo("PROVISION");
        assertThat(item.catalogItem().getUserActions().get(2).getId()).isEqualTo("ACTION_ID_1");
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

        UserActionsEntity repoUserActions = UserActionsEntityMother.of(
                List.of(codeUserAction, provisionUserAction, extraUserAction)
        );

        var projectKey = Strings.EMPTY;
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(repoItemCtx)
                .userActionsEntity(repoUserActions)
                .permissions(principalPermissions)
                .projectKey(projectKey)
                .build();

        var componentCount = 5; // Example component count for testing
        var expectedUpdatedAt =
                catalogRequestParams.getCatalogItemEntityContext().getLastCommitDateUTC().toInstant().toEpochMilli();
        var expectedDescriptionFileId =
                "cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2Rlc2NyaXB0aW9uUGF0aD9h"
                        + "dD1yZWZzL2hlYWRzL21hc3Rlcg==";
        var expectedImageFileId =
                "cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL2ltYWdlUGF0aD9hdD1yZWZzL2hl"
                        + "YWRzL21hc3Rlcg==";

        var item = catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);

        // updatedAt should be set even when the principal has no repo permissions
        assertThat(item.catalogItem().getUpdatedAt()).isEqualTo(expectedUpdatedAt);

        Optional<CatalogItemUserAction> codeAction = item.catalogItem().getUserActions().stream()
                .filter(ua -> Objects.equals("CODE", ua.getId()))
                .findFirst();

        // Removed fields due to lack of permissions
        assertThat(item.catalogItem().getItemSrc()).isNull();
        assertThat(item.catalogItem().getPath()).isNull();
        assertThat(item.catalogItem().getVisible()).isFalse();

        // Mandatory fields
        assertThat(item.catalogItem().getId()).isEqualTo("id");
        assertThat(item.catalogItem().getTitle()).isEqualTo("Appshell in Angular");
        assertThat(item.catalogItem().getShortDescription())
                .isEqualTo("Quickstart template to boost the development of web applications on the EDP.");
        assertThat(codeAction).isPresent();
        assertThat(codeAction.orElseThrow().getUrl().orElse(null)).isNull();
        assertThat(item.catalogItem().getTags()).hasSize(3);
        assertThat(item.catalogItem().getDate().toString()).isEqualTo("2000-01-01T00:00Z");

        // Optional fields
        assertThat(item.catalogItem().getAuthors()).hasSize(1);
        assertThat(item.catalogItem().getAuthors().getFirst()).isEqualTo("author");

        // Assert ids encoding
        // id(repoItemCtx.descriptionPath)
        assertThat(item.catalogItem().getDescriptionFileId()).isEqualTo(expectedDescriptionFileId);
        // id(repoItemCtx.imagePath)
        assertThat(item.catalogItem().getImageFileId()).isEqualTo(expectedImageFileId);
    }

    @Test
    void givenRequestParams_AndRestrictions_whenAsCatalogItem_thenRestrictionsAreMapped() {
        // given
        var projects = List.of("a", "b");

        var restrictions = CatalogItemEntityRestrictionsMother.of(projects);

        var catalogRequestParams = CatalogRequestParamsMother.of(restrictions);
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var componentCount = 5; // Example component count for testing

        // when
        var catalogItem = catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);

        // then
        assertThat(catalogItem.catalogItem().getRestrictions().getProjects()).isEqualTo(new HashSet<>(projects));
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

        var componentCount = 5; // Example component count for testing

        List<CatalogItemFilter> filters = catalogApiAdapter.catalogItemFiltersFrom(
                catalogItemRequestParams,
                clusters,
                userGroups,
                componentCount
        );

        assertThat(filters).hasSize(3);

        // Filters should match the order defined in catalog labels
        var labels = filters.stream()
                .map(CatalogItemFilter::getLabel)
                .toList();

        assertThat(labels).containsExactly("catalogLabel1", "catalogLabel2", "catalogLabel3");

        var filter1 = filters.get(0);
        var filter2 = filters.get(1);
        var filter3 = filters.get(2);

        assertThat(filter1.getLabel()).isEqualTo("catalogLabel1");
        assertThat(filter1.getOptions()).hasSize(1).contains("option1");

        assertThat(filter2.getLabel()).isEqualTo("catalogLabel2");
        assertThat(filter2.getOptions()).hasSize(1).contains("option2");

        assertThat(filter3.getLabel()).isEqualTo("catalogLabel3");
        assertThat(filter3.getOptions()).hasSize(2).containsAll(Set.of("option3", "option4"));
    }

    @Test
    void catalogItemFiltersFrom_withoutMatchingLabels() {
        CatalogItemEntity catalogItemEntity1 = catalogItemEntityFixture(
                Map.of("nonCatalogLabel", Set.of("missingOption"))
        );

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

        var componentCount = 5; // Example component count for testing

        List<CatalogItemFilter> filters = catalogApiAdapter.catalogItemFiltersFrom(
                catalogItemRequestParams,
                clusters,
                userGroups,
                componentCount
        );

        assertThat(filters).hasSize(1);

        Set<String> actual = filters.getFirst().getOptions();

        assertThat(actual).hasSize(1).contains("option1");
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
        assertThat(catalog.getCommunityPageId())
                .isEqualTo(
                        "cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL1Nv"
                                + "bWVGaWxlT3JEaXI_YXQ9cmVm"
                                + "cy9oZWFkcy9tYXN0ZXI="
                );
        assertThat(catalog.getLinks()).hasSize(3);
        assertThat(catalog.getTags()).hasSize(2);
    }

    @Test
    void givenCustomAndDefaultUserActions_whenFinalizeUserActions_thenOnlyMandatoryActionsAreReturned(
    ) {
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
        UserActionEntity[] defaultUserActions = {
                defaultUserActionCode,
                defaultUserActionProvision,
                defaultUserActionDummy
        };

        // when
        var mergedUserActions = catalogApiAdapter.finalizeUserActions(
                customUserActions,
                defaultUserActions,
                clusters,
                userGroups,
                projectKey,
                catalogItemId
        );

        // then
        assertThat(mergedUserActions).hasSize(2);
        assertThat(mergedUserActions)
                .extracting("catalogItemUserAction")
                .extracting("id")
                .containsExactlyInAnyOrder("CODE", "PROVISION")
                .doesNotContain("DUMMY")
                .doesNotContain("TEST");
    }

    @Test
    void givenCustomAndDefaultParameters_whenFinalizeUserActions_thenNonCustomizableParameterIsPreserved(
    ) {
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
                customUserActions,
                defaultUserActions,
                clusters,
                userGroups,
                projectKey,
                catalogItemId
        );

        // then
        assertThat(mergedUserActions).hasSize(1);

        var parameters =  mergedUserActions.getFirst().catalogItemUserAction().getParameters();

        assertThat(parameters).hasSize(3);
        var parameterNonCustomizable = parameters.stream()
                .filter(p -> p.getName().equals(PARAMETER_NON_CUSTOMIZABLE_NAME))
                .findFirst();
        assertThat(parameterNonCustomizable).isPresent();
        assertThat(parameterNonCustomizable.get().getDefaultValue().get()).isEqualTo("123");
    }

    @Test
    void givenMissingCustomizableParameter_whenFinalizeUserActions_thenGeneratedParameterAppears(
    ) {
        // given
        var customUserActionProvision = generateCustomUserActions();
        var defaultUserActionProvision = generateDefaultUserActionEntity();
        var parametersWithExtraOneList = new ArrayList<>(Arrays.asList(defaultUserActionProvision.getParameters()));
        var extraCustomizableParameterName = "extraCustomizableParameter";
        var extraCustomizableParameterValue = "123999";
        var extraCustomizableParameter = UserActionEntityParameterMother.of(
                extraCustomizableParameterName,
                "string",
                true,
                extraCustomizableParameterValue,
                Collections.emptyList(),
                List.of(UserActionEntityParameterLocationMother.of()),
                Collections.emptyList(),
                "Workflow to execute.",
                "workflow placeholder",
                "workflow hint",
                true,
                false,
                false,
                List.of(UserActionEntityParameterValidationMother.of()));
        parametersWithExtraOneList.add(extraCustomizableParameter);

        defaultUserActionProvision.setParameters(parametersWithExtraOneList.toArray(UserActionEntityParameter[]::new));

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();
        var projectKey = Strings.EMPTY;
        var catalogItemId = Strings.EMPTY;

        List<CatalogItemUserAction> customUserActions = List.of(customUserActionProvision);
        UserActionEntity[] defaultUserActions = {defaultUserActionProvision};

        // when
        var mergedUserActions = catalogApiAdapter.finalizeUserActions(
                customUserActions,
                defaultUserActions,
                clusters,
                userGroups,
                projectKey,
                catalogItemId
        );

        // then
        assertThat(mergedUserActions).hasSize(1);

        var parameters =  mergedUserActions.getFirst().catalogItemUserAction().getParameters();

        assertThat(parameters).hasSize(4);
        var generatedExtraCustomizableParameter = parameters.stream()
                .filter(p -> p.getName().equals(extraCustomizableParameterName))
                .findFirst();
        assertThat(generatedExtraCustomizableParameter).isPresent();
        assertThat(generatedExtraCustomizableParameter.get().getDefaultValue().get())
                .isEqualTo(extraCustomizableParameterValue);
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
        List<CatalogItemUserActionParameter> itemUserActionParameters =
                List.of(customParameter, customParameterToBeOverridden);

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