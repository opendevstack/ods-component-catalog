package org.opendevstack.component_catalog.server.mappers;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionMessageType;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.mother.*;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityUserActionParameterValidationMother;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityUserActionMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.UserActionRestrictionsMother;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluatorResultMother;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.opendevstack.component_catalog.server.mappers.EntitiesMapper.asCatalogItemUserAction;
import static org.opendevstack.component_catalog.server.mappers.EntitiesMapper.overrideNullFields;
import static org.opendevstack.component_catalog.server.mappers.MapperUtils.isAbsent;
import static org.opendevstack.component_catalog.server.mappers.MapperUtils.isNull;

class EntitiesMapperTest {

    private CatalogItemUserActionParameterMapper catalogItemUserActionParameterMapper;
    private EntitiesMapper entitiesMapper;

    @BeforeEach
    void setUp() {
        this.catalogItemUserActionParameterMapper =  new CatalogItemUserActionParameterMapper();

        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> RestrictionsEvaluatorResultMother.of();

        var groupsRestrictionProps =
                ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        CatalogItemUserActionMapper catalogItemUserActionMapper = new CatalogItemUserActionMapper(
                catalogItemUserActionParameterMapper,
                List.of(dummyEvaluator),
                groupsRestrictionProps
        );

        this.entitiesMapper = new EntitiesMapper(catalogItemUserActionMapper);
    }

    @Test
    void asCatalog_mapsCatalogEntityContextToCatalog() {
        var catalogEntityCtx = CatalogEntityContextMother.of();

        var catalog = entitiesMapper.asCatalog(catalogEntityCtx);

        assertThat(catalog.getName()).isEqualTo("Catalog Name");
        assertThat(catalog.getDescription()).isEqualTo("Catalog Description");
        assertThat(catalog.getCommunityPageId())
                .isEqualTo(
                        "cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL1NvbWVGaWxl"
                                + "T3JEaXI_YXQ9cmVmcy9oZWFkcy9tYXN0ZXI="
                );
        assertThat(catalog.getLinks()).hasSize(3);
        assertThat(catalog.getTags()).hasSize(2);
        assertThat(catalog.getOwners()).hasSize(2);
    }

    @Test
    void asCatalogDescriptor_mapsCollectionsTargetToCatalogDescriptor() {
        var collectionsTarget = CatalogsCollectionsEntityTargetMother.of();

        var descriptor = entitiesMapper.asCatalogDescriptor(collectionsTarget);

        assertThat(descriptor.getId()).isEqualTo("L3BhdGgvdG8vc2ltcGxlLXNsdWc=");
        assertThat(descriptor.getSlug()).isEqualTo("simple-slug");
    }

    @Test
    void asCatalogItem_mapsCatalogItemEntityContextToCatalogItem() {
        var catalogItemEntityCtx = CatalogItemEntityContextMother.of();
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();
        var componentCount = 5; // Example component count for testing

        var catalogItem = entitiesMapper.asCatalogItem(
                catalogItemEntityCtx,
                clusters,
                userGroups,
                Strings.EMPTY,
                componentCount
        );

        assertThat(catalogItem.catalogItem().getTitle()).isEqualTo("Appshell in Angular");
        assertThat(catalogItem.catalogItem().getUserActions()).hasSize(2);
    }

    @Test
    void asCatalogItemUserAction_mapsUserActionEntityToCatalogItemUserAction() {
        var baseUserActionEntity = UserActionEntityMother.of();
        var customCatalogItemUserAction = asCatalogItemUserAction(baseUserActionEntity);

        // Non-nullable fields
        assertThat(customCatalogItemUserAction.getId()).isEqualTo(baseUserActionEntity.getId());
        assertThat(customCatalogItemUserAction.getDisplayName()).isEqualTo(baseUserActionEntity.getDisplayName());

        // Nullable fields
        assertToJsonNullable(baseUserActionEntity.getTriggerMessage(), customCatalogItemUserAction.getTriggerMessage());
        assertToJsonNullable(baseUserActionEntity.getUrl(), customCatalogItemUserAction.getUrl());

        // Check that the parameters are mapped correctly
        var entityParams = baseUserActionEntity.getParameters();
        var itemParams = customCatalogItemUserAction.getParameters();

        assertThat(itemParams).isNotEmpty();
        assertThat(itemParams).hasSize(entityParams.length);

        for (var i = 0; i < entityParams.length; i++) {
            var entityParam = entityParams[i];
            var itemParam = itemParams.get(i);
            assertCatalogItemUserActionParameterMapping(entityParam, itemParam);
        }
    }

    @Test
    void asCatalogItemUserActionParameter_mapsUserActionEntityParameterToCatalogItemUserActionParameter() {
        var userActionEntityParameter = UserActionEntityParameterMother.of();
        var itemUserActionParameter =
                catalogItemUserActionParameterMapper.asCatalogItemUserActionParameter(userActionEntityParameter);

        assertCatalogItemUserActionParameterMapping(userActionEntityParameter, itemUserActionParameter);
    }

    @Test
    void asCatalogItemUserActionParameter_whenNullFieldOnEntityParameter_thenJsonNullableFieldIsUndefined() {
        var userActionEntityParameter = UserActionEntityParameterMother.of();
        // Set some fields to null to test the mapping of undefined fields
        userActionEntityParameter.setDefaultValue(null);
        userActionEntityParameter.setPlaceholder(null);
        userActionEntityParameter.setHint(null);

        var itemUserActionParameter =
                catalogItemUserActionParameterMapper.asCatalogItemUserActionParameter(userActionEntityParameter);

        assertThat(itemUserActionParameter.getDefaultValue()).isEqualTo(JsonNullable.undefined());
        assertThat(itemUserActionParameter.getPlaceholder()).isEqualTo(JsonNullable.undefined());
        assertThat(itemUserActionParameter.getHint()).isEqualTo(JsonNullable.undefined());
    }

    @Test
    void overrideNullFields_correctlyOverridesOrNotFieldsInDestination() {
        // Create a well-formed source and destination
        var src = CatalogItemUserActionParameter.builder()
                .name("src-name")
                .type("src-type")
                .required(true)
                .defaultValue(JsonNullable.of("src-default"))
                .label("src-label")
                .placeholder(JsonNullable.undefined())
                .hint(JsonNullable.of("src-hint"))
                .sendOnDeletion(false)
                .visible(false)
                .build();

        // Rules are:
        // null-ish: field is either null or JsonNullable.undefined() or JsonNullable.get() == null
        // dest field is not null-ish -> do not override dest field
        // src field is null-ish -> do not override dest field
        var dest = CatalogItemUserActionParameter.builder()
                .name("dest-name")
                .type("dest-type")
                .required(false)
                .defaultValue(JsonNullable.of("dest-default"))
                .label(null)
                .placeholder(JsonNullable.of("dest-placeholder"))
                .hint(JsonNullable.undefined())
                .sendOnDeletion(false)
                .visible(true)
                .build();

        var result = EntitiesMapper.overrideNullFields(src, dest);

        assertThat(result.getName()).isEqualTo("dest-name");
        assertThat(result.getType()).isEqualTo("dest-type");
        assertThat(result.getRequired()).isFalse();

        assertThat(isAbsent(result.getDefaultValue())).isFalse();
        assertThat(result.getDefaultValue().get()).isEqualTo("dest-default");

        assertThat(result.getLabel()).isEqualTo("src-label");

        assertThat(isAbsent(result.getPlaceholder())).isFalse();
        assertThat(result.getPlaceholder().get()).isEqualTo("dest-placeholder");

        assertThat(isAbsent(result.getHint())).isFalse();
        assertThat(result.getHint().get()).isEqualTo("src-hint");

        assertThat(isAbsent(result.getSendOnDeletion())).isFalse();

        assertThat(result.getVisible()).isTrue();
    }

    @Test
    void asUserActionsEntity_mapsCatalogItemUserActionToUserActionEntity() {
        // given
        var catalogItemEntityUserAction = CatalogItemEntityUserActionMother.of();


        // when
        var userActionEntity = entitiesMapper.asUserActionsEntity(catalogItemEntityUserAction);

        // then
        assertThat(userActionEntity).isNotNull();
        assertThat(userActionEntity.getId()).isEqualTo("TEST_PROVISION");
        assertThat(userActionEntity.getDisplayName()).isEqualTo("TEST Provision");

        assertThat(userActionEntity.getUrl()).isEqualTo("http://example.com/action1");
        assertThat(userActionEntity.getTriggerMessage()).isEqualTo("Trigger Action 1");
        assertThat(userActionEntity.isMandatory()).isFalse();

        assertThat(userActionEntity.getParameters()).hasSize(2);
        assertThat(userActionEntity.getMessagesTitles()).hasSize(2);
        assertThat(userActionEntity.getMessagesDefinitions()).hasSize(1);
    }

    @Test
    void asUserActionEntityParameter_mapCatalogItemEntityUserActionParameterToUserActionEntityParameter() {
        // given
        var catalogItemUserActionParameter = CatalogItemEntityUserActionParameterMother.of();

        // when
        var userActionEntityParameter = entitiesMapper.asUserActionEntityParameter(catalogItemUserActionParameter);

        // then
        assertThat(userActionEntityParameter).isNotNull();
        assertThat(userActionEntityParameter.getName()).isEqualTo("workflow");
        assertThat(userActionEntityParameter.getType()).isEqualTo("string");
        assertThat(userActionEntityParameter.isRequired()).isTrue();
        assertThat(userActionEntityParameter.getDefaultValue()).isEqualTo("9987");
        assertThat(userActionEntityParameter.getLabel()).isEqualTo("Workflow to execute.");
        assertThat(userActionEntityParameter.getPlaceholder()).isEqualTo("Simple placeholder");
        assertThat(userActionEntityParameter.getHint()).isEqualTo("Wait for the hint");
        assertThat(userActionEntityParameter.isCustomizable()).isFalse();
        assertThat(userActionEntityParameter.isSendOnDeletion()).isFalse();
        assertThat(userActionEntityParameter.isVisible()).isFalse();
    }

    @Test
    void asCatalogItemUserActionMessageDefinition_mapsTitleAndMessageDefinition() {
        // given
        var userActionEntityMessageTitle = UserActionEntityMessageTitleMother.of();
        var userActionEntityMessageDefinition = UserActionEntityMessageDefinitionMother.of();

        // when
        var catalogItemUserActionMessageDefinition = entitiesMapper.asCatalogItemUserActionMessageDefinition(
                userActionEntityMessageTitle, userActionEntityMessageDefinition);

        // then
        assertThat(catalogItemUserActionMessageDefinition).isNotNull();
        assertThat(catalogItemUserActionMessageDefinition.getId()).isEqualTo("Message Definition ID");
        assertThat(catalogItemUserActionMessageDefinition.getType())
                .isEqualTo(CatalogItemUserActionMessageType.SUCCESS);
        assertThat(catalogItemUserActionMessageDefinition.getTitle())
                .isEqualTo("User Action Entity Message Title for success");
        assertThat(catalogItemUserActionMessageDefinition.getMessage())
                .isEqualTo("Simple message for testing purposes for Message Definition ID with type success");
        assertThat(catalogItemUserActionMessageDefinition.getCreatesIncident()).isTrue();
    }

    @Test
    void asCatalogItemUserActionMessageDefinition_mapUserActionEntityMessageDefinition() {
        // given
        var userActionMessageDefinition = UserActionEntityMessageDefinitionMother.of();

        // when
        var catalogItemUserActionMessageDefinition =
                entitiesMapper.asCatalogItemUserActionMessageDefinition(userActionMessageDefinition);

        // then
        assertThat(catalogItemUserActionMessageDefinition).isNotNull();
        assertThat(catalogItemUserActionMessageDefinition.getId()).isEqualTo("Message Definition ID");
        assertThat(catalogItemUserActionMessageDefinition.getType())
                .isEqualTo(CatalogItemUserActionMessageType.SUCCESS);
        assertThat(catalogItemUserActionMessageDefinition.getTitle()).isNull();
        assertThat(catalogItemUserActionMessageDefinition.getMessage())
                .isEqualTo("Simple message for testing purposes for Message Definition ID with type success");
    }

    @Test
    void asCatalogItemUserActionMessageType_mapUserActionEntityMessageType() {
        // given
        var userActionEntityMessageType = UserActionEntityMessageTypeMother.of();

        // when
        var catalogItemUserActionMessageType =
                entitiesMapper.asCatalogItemUserActionMessageType(userActionEntityMessageType);

        // then
        assertThat(catalogItemUserActionMessageType).isEqualTo(CatalogItemUserActionMessageType.SUCCESS);
    }

    @Test
    void overrideNullFields_forCatalogItemUserActionEntities() {
        // given
        var customParameter = CatalogItemUserActionParameterMother.of("custom parameter name", "custom parameter type");

        CatalogItemUserAction baseCatalogItemUserAction = CatalogItemUserActionMother.of();
        CatalogItemUserAction customCatalogItemUserAction = CatalogItemUserAction.builder()
                .id("TEST_PROVISION")
                .displayName("Custom TEST Provision")
                .parameters(List.of(customParameter))
                .build();

        // when
        var mergedCatalogItemUserAction = overrideNullFields(baseCatalogItemUserAction, customCatalogItemUserAction);

        // then
        assertThat(mergedCatalogItemUserAction).isNotNull();
        assertThat(mergedCatalogItemUserAction.getId()).isEqualTo("TEST_PROVISION");
        assertThat(mergedCatalogItemUserAction.getDisplayName()).isEqualTo("Custom TEST Provision");
        assertThat(mergedCatalogItemUserAction.getUrl()).isEqualTo(JsonNullable.of("http://example.com/action1"));
        assertThat(mergedCatalogItemUserAction.getTriggerMessage()).isEqualTo(JsonNullable.of("Trigger Action 1"));
        assertThat(mergedCatalogItemUserAction.getParameters()).hasSize(1);
        assertThat(mergedCatalogItemUserAction.getParameters()).contains(customParameter);
    }

    @Test
    void givenCatalogItemUserActionParameter_whenOverrideNullFields_thenReturnValidations() {
        // given
        var sourceCatalogItemUserActionParameterValidation = CatalogItemUserActionParameterValidationMother.of();
        var destCatalogItemUserActionParameterValidation = CatalogItemUserActionParameterValidationMother.of();

        var sourceCatalogItemUserActionParameter = CatalogItemUserActionParameterMother.of(
                "source parameter name",
                "parameter type",
                List.of(sourceCatalogItemUserActionParameterValidation)
        );
        var destCatalogItemUserActionParameter = CatalogItemUserActionParameterMother.of(
                "dest parameter name",
                null,
                List.of(destCatalogItemUserActionParameterValidation)
        );

        // when
        var result = EntitiesMapper.overrideNullFields(
                sourceCatalogItemUserActionParameter,
                destCatalogItemUserActionParameter
        );

        // then
        assertThat(result.getName()).isEqualTo(destCatalogItemUserActionParameter.getName());
        assertThat(result.getType()).isEqualTo(sourceCatalogItemUserActionParameter.getType());
        assertThat(result.getValidations()).isEqualTo(destCatalogItemUserActionParameter.getValidations());
    }

    @Test
    void givenCatalogItemUserActionParameterValidation_whenAsMapperValidation_thenReturnMappedValidation() {
        // given
        var catalogItemEntityUserActionParameterValidation = CatalogItemEntityUserActionParameterValidationMother.of();

        // when
        var userActionEntityParameterValidation =
                catalogItemUserActionParameterMapper.asCatalogItemUserActionParameterValidation(
                        catalogItemEntityUserActionParameterValidation
                );

        // then
        assertThat(userActionEntityParameterValidation).isNotNull();
        assertThat(userActionEntityParameterValidation.getRegex())
                .isEqualTo(catalogItemEntityUserActionParameterValidation.getRegex());
        assertThat(userActionEntityParameterValidation.getErrorMessage())
                .isEqualTo(catalogItemEntityUserActionParameterValidation.getErrorMessage());
    }

    @Test
    void givenAUserActionRestrictions_whenAsUserActionsRestrictions_thenReturnUserActionEntityRestrictions() {
        // given
        var userActionRestrictions = UserActionRestrictionsMother.of();

        // when
        var userActionEntityRestrictions = entitiesMapper.asUserActionsRestrictions(userActionRestrictions);

        // then
        assertThat(userActionEntityRestrictions).isNotNull()
                .extracting(UserActionEntityRestrictions::isOneTimeOnly)
                .isEqualTo(false);
        assertThat(userActionEntityRestrictions.getProjects())
                .isEqualTo(new String[]{"ProjectA", "ProjectB"});
        assertThat(userActionEntityRestrictions.getLocations())
                .isEqualTo(new String[]{"LocationA", "LocationB"});
    }

    @Test
    void givenPresentNullJsonNullable_whenOverrideAbsentFields_thenDoesntOverrideValue() {
        // given
        var src = CatalogItemUserActionParameter.builder()
                .defaultValue(JsonNullable.of(null))
                .build();

        var dest = CatalogItemUserActionParameter.builder()
                .defaultValue(JsonNullable.of("dest-default"))
                .build();

        // when
        var result = EntitiesMapper.overrideNullFields(src, dest);

        // then
        assertThat(isAbsent(result.getDefaultValue())).isFalse();
        assertThat(isNull(result.getDefaultValue())).isFalse();
        assertThat(result.getDefaultValue().get()).isNotNull();
    }

    @Test
    void givenUndefinedJsonNullable_whenOverrideAbsentFields_thenDoesntOverrideValue() {
        // given
        var src = CatalogItemUserActionParameter.builder()
                .defaultValue(JsonNullable.undefined())
                .build();

        var dest = CatalogItemUserActionParameter.builder()
                .defaultValue(JsonNullable.of("dest-default"))
                .build();

        // when
        var result = EntitiesMapper.overrideNullFields(src, dest);

        // then
        assertThat(isAbsent(result.getDefaultValue())).isFalse();
        assertThat(isNull(result.getDefaultValue())).isFalse();
        assertThat(result.getDefaultValue().get()).isEqualTo("dest-default");
    }

    @Test
    void givenPresentNullList_whenOverrideAbsentFields_thenDoesntOverrideList() {
        // given
        var src = CatalogItemUserActionParameter.builder()
                .options(JsonNullable.of(null))
                .build();

        var dest = CatalogItemUserActionParameter.builder()
                .options(JsonNullable.of(List.of("opt1")))
                .build();

        // when
        var result = EntitiesMapper.overrideNullFields(src, dest);

        // then
        assertThat(isAbsent(result.getOptions())).isFalse();
        assertThat(result.getOptions().get()).isNotNull();
    }

    @Test
    void givenUndefinedList_whenOverrideAbsentFields_thenKeepDestinationList() {
        // given
        var src = CatalogItemUserActionParameter.builder()
                .options(JsonNullable.undefined())
                .build();

        var dest = CatalogItemUserActionParameter.builder()
                .options(JsonNullable.of(List.of("opt1")))
                .build();

        // when
        var result = EntitiesMapper.overrideNullFields(src, dest);

        // then
        assertThat(result.getOptions().get()).isEqualTo(List.of("opt1"));
    }

    static void assertCatalogItemUserActionParameterMapping(UserActionEntityParameter from,
                                                            CatalogItemUserActionParameter to) {
        // Non-nullable fields
        assertThat(to.getName()).isEqualTo(from.getName());
        assertThat(to.getType()).isEqualTo(from.getType());
        assertThat(to.getLabel()).isEqualTo(from.getLabel());
        assertThat(to.getVisible()).isEqualTo(from.isVisible());

        // Nullable fields
        assertToJsonNullable(from.getDefaultValue(), to.getDefaultValue());
        assertToJsonNullable(from.getPlaceholder(), to.getPlaceholder());
        assertToJsonNullable(from.getHint(), to.getHint());
        assertToJsonNullable(from.isSendOnDeletion(), to.getSendOnDeletion());

        if (from.getValidations() != null && from.getValidations().length > 0) {
            assertThat(from.getValidations()).hasSize(1);
            assertThat(from.getValidations()).hasSize(1);
            assertThat(from.getValidations()[0].getRegex())
                    .isEqualTo(to.getValidations().get().getFirst().getRegex());
            assertThat(from.getValidations()[0].getErrorMessage())
                    .isEqualTo(to.getValidations().get().getFirst().getErrorMessage());
        }

        if (from.getLocations() != null && from.getLocations().length > 0) {
            assertThat(from.getLocations()).hasSize(to.getLocations().get().size());
            for(var i=0; i<from.getLocations().length; i++) {
                assertThat(from.getLocations()[i].getLocation())
                        .isEqualTo(to.getLocations().get().get(i).getLocation());
                assertThat(from.getLocations()[i].getValue()).isEqualTo(to.getLocations().get().get(i).getValue());
            }
        }

        if(from.getDefaultValues() != null && from.getDefaultValues().length > 0) {
            assertThat(from.getDefaultValues()).hasSize(to.getDefaultValues().get().size());
        }

        if(from.getOptions() != null && from.getOptions().length > 0) {
            assertThat(from.getOptions()).hasSize(to.getOptions().get().size());
        }

    }

    static <T> void assertToJsonNullable(T from, JsonNullable<T> to) {
        if (from == null) {
            assertThat(to).isEqualTo(JsonNullable.undefined());
        } else {
            if (from.getClass().isInstance(JsonNullable.class)) {
                assertThat(to.get()).isEqualTo(from);
            } else {
                assertThat(to).isEqualTo(JsonNullable.of(from));
            }
        }
    }
}