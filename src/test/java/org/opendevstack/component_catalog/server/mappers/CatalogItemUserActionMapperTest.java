package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionMother;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntityMother;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Collections;
import java.util.List;

import static org.opendevstack.component_catalog.server.mappers.EntitiesMapperTest.assertCatalogItemUserActionParameterMapping;
import static org.opendevstack.component_catalog.server.mappers.EntitiesMapperTest.assertToJsonNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CatalogItemUserActionMapperTest {

    private CatalogItemUserActionMapper catalogItemUserActionMapper;

    @BeforeEach
    void setUp() {
        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> Pair.of(true, "");

        var groupsRestrictionProps = ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        this.catalogItemUserActionMapper = new CatalogItemUserActionMapper(new CatalogItemUserActionParameterMapper(),
                List.of(dummyEvaluator), groupsRestrictionProps);

    }

    @Test
    void asCatalogItemUserAction_mapsUserActionEntityToCatalogItemUserAction() {
        var baseUserActionEntity = UserActionEntityMother.of();
        List<String> clusters = Collections.emptyList();
        List<String> userGroups = Collections.emptyList();
        String projectKey = Strings.EMPTY;
        String catalogItemId = Strings.EMPTY;

        var customCatalogItemUserAction = catalogItemUserActionMapper.asCatalogItemUserAction(baseUserActionEntity,
                clusters, userGroups, projectKey, catalogItemId);

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

}
