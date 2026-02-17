package org.opendevstack.component_catalog.server.mappers;

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
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Collections;
import java.util.List;

import static org.opendevstack.component_catalog.server.mappers.EntitiesMapper.asCatalogItemUserAction;
import static org.opendevstack.component_catalog.server.mappers.EntitiesMapper.overrideNullFields;
import static org.opendevstack.component_catalog.server.mappers.MapperUtils.nullish;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EntitiesMapperTest {

    private CatalogItemUserActionParameterMapper catalogItemUserActionParameterMapper;
    private EntitiesMapper entitiesMapper;

    @BeforeEach
    void setUp() {
        this.catalogItemUserActionParameterMapper =  new CatalogItemUserActionParameterMapper();

        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> Pair.of(true, "");

        var groupsRestrictionProps = ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        CatalogItemUserActionMapper catalogItemUserActionMapper = new CatalogItemUserActionMapper(catalogItemUserActionParameterMapper,
                List.of(dummyEvaluator), groupsRestrictionProps);

        this.entitiesMapper = new EntitiesMapper(catalogItemUserActionMapper);
    }

    @Test
    void asCatalog_mapsCatalogEntityContextToCatalog() {
        var catalogEntityCtx = CatalogEntityContextMother.of();

        var catalog = entitiesMapper.asCatalog(catalogEntityCtx);

        assertEquals("Catalog Name", catalog.getName());
        assertEquals("Catalog Description", catalog.getDescription());
        assertEquals("cHJvamVjdHMvTVlQUk9KRUNUL3JlcG9zL3JlcG8tc2x1Zy9yYXcvc29tZS1wYWNrYWdlL1NvbWVGaWxlT3JEaXI_YXQ9cmVmcy9oZWFkcy9tYXN0ZXI=", catalog.getCommunityPageId());
        assertEquals(3, catalog.getLinks().size());
        assertEquals(2, catalog.getTags().size());
    }

    @Test
    void asCatalogDescriptor_mapsCollectionsTargetToCatalogDescriptor() {
        var collectionsTarget = CatalogsCollectionsEntityTargetMother.of();

        var descriptor = entitiesMapper.asCatalogDescriptor(collectionsTarget);

        assertEquals("L3BhdGgvdG8vc2ltcGxlLXNsdWc=", descriptor.getId());
        assertEquals("simple-slug", descriptor.getSlug());
    }

    @Test
    void asCatalogItem_mapsCatalogItemEntityContextToCatalogItem() {
        var catalogItemEntityCtx = CatalogItemEntityContextMother.of();
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var catalogItem = entitiesMapper.asCatalogItem(catalogItemEntityCtx, clusters, userGroups, Strings.EMPTY);

        assertEquals("Appshell in Angular", catalogItem.getTitle());
        assertEquals(2, catalogItem.getUserActions().size());
    }

    @Test
    void asCatalogItemUserAction_mapsUserActionEntityToCatalogItemUserAction() {
        var baseUserActionEntity = UserActionEntityMother.of();
        var customCatalogItemUserAction = asCatalogItemUserAction(baseUserActionEntity);

        // Non-nullable fields
        assertEquals(baseUserActionEntity.getId(), customCatalogItemUserAction.getId());
        assertEquals(baseUserActionEntity.getDisplayName(), customCatalogItemUserAction.getDisplayName());

        // Nullable fields
        assertToJsonNullable(baseUserActionEntity.getTriggerMessage(), customCatalogItemUserAction.getTriggerMessage());
        assertToJsonNullable(baseUserActionEntity.getUrl(), customCatalogItemUserAction.getUrl());

        // Check that the parameters are mapped correctly
        var entityParams = baseUserActionEntity.getParameters();
        var itemParams = customCatalogItemUserAction.getParameters();

        assertFalse(itemParams.isEmpty());
        assertEquals(entityParams.length, itemParams.size());

        for (var i = 0; i < entityParams.length; i++) {
            var entityParam = entityParams[i];
            var itemParam = itemParams.get(i);
            assertCatalogItemUserActionParameterMapping(entityParam, itemParam);
        }
    }

    @Test
    void asCatalogItemUserActionParameter_mapsUserActionEntityParameterToCatalogItemUserActionParameter() {
        var userActionEntityParameter = UserActionEntityParameterMother.of();
        var itemUserActionParameter = catalogItemUserActionParameterMapper.asCatalogItemUserActionParameter(userActionEntityParameter);

        assertCatalogItemUserActionParameterMapping(userActionEntityParameter, itemUserActionParameter);
    }

    @Test
    void asCatalogItemUserActionParameter_whenNullFieldOnUserActionEntityParameter_thenJsonNullableUndefinedFieldOnItemUserActionParameter() {
        var userActionEntityParameter = UserActionEntityParameterMother.of();
        // Set some fields to null to test the mapping of undefined fields
        userActionEntityParameter.setDefaultValue(null);
        userActionEntityParameter.setPlaceholder(null);
        userActionEntityParameter.setHint(null);

        var itemUserActionParameter = catalogItemUserActionParameterMapper.asCatalogItemUserActionParameter(userActionEntityParameter);

        assertEquals(JsonNullable.undefined(), itemUserActionParameter.getDefaultValue());
        assertEquals(JsonNullable.undefined(), itemUserActionParameter.getPlaceholder());
        assertEquals(JsonNullable.undefined(), itemUserActionParameter.getHint());
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
                .visible(true)
                .build();

        var result = EntitiesMapper.overrideNullFields(src, dest);

        assertEquals("dest-name", result.getName());
        assertEquals("dest-type", result.getType());
        assertEquals(false, result.getRequired());

        assertFalse(nullish(result.getDefaultValue()));
        assertEquals("dest-default", result.getDefaultValue().get());

        assertEquals("src-label", result.getLabel());

        assertFalse(nullish(result.getPlaceholder()));
        assertEquals("dest-placeholder", result.getPlaceholder().get());

        assertFalse(nullish(result.getHint()));
        assertEquals("src-hint", result.getHint().get());

        assertTrue(result.getVisible());
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
        assertThat(userActionEntityParameter.isVisible()).isFalse();
    }

    @Test
    void asCatalogItemUserActionMessageDefinition_mapUserActionEntityMessageTitleAndUserActionEntityMessageDefinition() {
        // given
        var userActionEntityMessageTitle = UserActionEntityMessageTitleMother.of();
        var userActionEntityMessageDefinition = UserActionEntityMessageDefinitionMother.of();

        // when
        var catalogItemUserActionMessageDefinition = entitiesMapper.asCatalogItemUserActionMessageDefinition(
                userActionEntityMessageTitle, userActionEntityMessageDefinition);

        // then
        assertThat(catalogItemUserActionMessageDefinition).isNotNull();
        assertThat(catalogItemUserActionMessageDefinition.getId()).isEqualTo("Message Definition ID");
        assertThat(catalogItemUserActionMessageDefinition.getType()).isEqualTo(CatalogItemUserActionMessageType.SUCCESS);
        assertThat(catalogItemUserActionMessageDefinition.getTitle()).isEqualTo("User Action Entity Message Title for success");
        assertThat(catalogItemUserActionMessageDefinition.getMessage()).isEqualTo("Simple message for testing purposes for Message Definition ID with type success");
        assertThat(catalogItemUserActionMessageDefinition.getCreatesIncident()).isTrue();
    }

    @Test
    void asCatalogItemUserActionMessageDefinition_mapUserActionEntityMessageDefinition() {
        // given
        var userActionMessageDefinition = UserActionEntityMessageDefinitionMother.of();

        // when
        var catalogItemUserActionMessageDefinition = entitiesMapper.asCatalogItemUserActionMessageDefinition(userActionMessageDefinition);

        // then
        assertThat(catalogItemUserActionMessageDefinition).isNotNull();
        assertThat(catalogItemUserActionMessageDefinition.getId()).isEqualTo("Message Definition ID");
        assertThat(catalogItemUserActionMessageDefinition.getType()).isEqualTo(CatalogItemUserActionMessageType.SUCCESS);
        assertThat(catalogItemUserActionMessageDefinition.getTitle()).isNull();
        assertThat(catalogItemUserActionMessageDefinition.getMessage()).isEqualTo("Simple message for testing purposes for Message Definition ID with type success");
    }

    @Test
    void asCatalogItemUserActionMessageType_mapUserActionEntityMessageType() {
        // given
        var userActionEntityMessageType = UserActionEntityMessageTypeMother.of();

        // when
        var catalogItemUserActionMessageType = entitiesMapper.asCatalogItemUserActionMessageType(userActionEntityMessageType);

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

        var sourceCatalogItemUserActionParameter = CatalogItemUserActionParameterMother.of("source parameter name", "parameter type", List.of(sourceCatalogItemUserActionParameterValidation));
        var destCatalogItemUserActionParameter = CatalogItemUserActionParameterMother.of("dest parameter name", null, List.of(destCatalogItemUserActionParameterValidation));

        // when
        var result = EntitiesMapper.overrideNullFields(sourceCatalogItemUserActionParameter, destCatalogItemUserActionParameter);

        // then
        assertThat(result.getName()).isEqualTo(destCatalogItemUserActionParameter.getName());
        assertThat(result.getType()).isEqualTo(sourceCatalogItemUserActionParameter.getType());
        assertThat(result.getValidations()).isEqualTo(destCatalogItemUserActionParameter.getValidations());
    }

    @Test
    void givenACatalogItemEntityUserActionParameterValidation_whenAsUserActionEntityParameterValidation_thenReturnUserActionEntityParameterValidation() {
        // given
        var catalogItemEntityUserActionParameterValidation = CatalogItemEntityUserActionParameterValidationMother.of();

        // when
        var userActionEntityParameterValidation = catalogItemUserActionParameterMapper.asCatalogItemUserActionParameterValidation(catalogItemEntityUserActionParameterValidation);

        // then
        assertThat(userActionEntityParameterValidation).isNotNull();
        assertThat(userActionEntityParameterValidation.getRegex()).isEqualTo(catalogItemEntityUserActionParameterValidation.getRegex());
        assertThat(userActionEntityParameterValidation.getErrorMessage()).isEqualTo(catalogItemEntityUserActionParameterValidation.getErrorMessage());
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
        assertThat(userActionEntityRestrictions.getProjects()).isEqualTo(new String[]{"ProjectA", "ProjectB"});
        assertThat(userActionEntityRestrictions.getLocations()).isEqualTo(new String[]{"LocationA", "LocationB"});
    }

    static void assertCatalogItemUserActionParameterMapping(UserActionEntityParameter from,
                                                            CatalogItemUserActionParameter to) {
        // Non-nullable fields
        assertEquals(from.getName(), to.getName());
        assertEquals(from.getType(), to.getType());
        assertEquals(from.getLabel(), to.getLabel());
        assertEquals(from.isVisible(), to.getVisible());

        // Nullable fields
        assertToJsonNullable(from.getDefaultValue(), to.getDefaultValue());
        assertToJsonNullable(from.getPlaceholder(), to.getPlaceholder());
        assertToJsonNullable(from.getHint(), to.getHint());

        if (from.getValidations() != null && from.getValidations().length > 0) {
            assertThat(from.getValidations()).hasSize(1);
            assertThat(from.getValidations()).hasSize(1);
            assertThat(from.getValidations()[0].getRegex()).isEqualTo(to.getValidations().get().getFirst().getRegex());
            assertThat(from.getValidations()[0].getErrorMessage()).isEqualTo(to.getValidations().get().getFirst().getErrorMessage());
        }

        if (from.getLocations() != null && from.getLocations().length > 0) {
            assertThat(from.getLocations()).hasSize(to.getLocations().get().size());
            for(var i=0; i<from.getLocations().length; i++) {
                assertThat(from.getLocations()[i].getLocation()).isEqualTo(to.getLocations().get().get(i).getLocation());
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
            assertEquals(JsonNullable.undefined(), to);
        } else {
            if (from.getClass().isInstance(JsonNullable.class)) {
                assertEquals(from, to.get());
            } else {
                assertEquals(JsonNullable.of(from), to);
            }
        }
    }
}