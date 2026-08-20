package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionEntityMother;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluator;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CatalogItemUserActionMapperTest {

    private CatalogItemUserActionMapper catalogItemUserActionMapper;

    @BeforeEach
    void setUp() {
        RestrictionsEvaluator dummyEvaluator = (restrictions, params) -> Pair.of(true, "");

        var groupsRestrictionProps =
                ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps.builder()
                .prefix(List.of("prefix1", "prefix2", "prefix3"))
                .suffix(List.of("suffix1", "suffix2", "suffix3"))
                .build();

        this.catalogItemUserActionMapper = new CatalogItemUserActionMapper(
                new CatalogItemUserActionParameterMapper(),
                List.of(dummyEvaluator),
                groupsRestrictionProps
        );

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
        assertThat(customCatalogItemUserAction.getId()).isEqualTo(baseUserActionEntity.getId());
        assertThat(customCatalogItemUserAction.getDisplayName()).isEqualTo(baseUserActionEntity.getDisplayName());

        // Nullable fields
        EntitiesMapperTest.assertToJsonNullable(
                baseUserActionEntity.getTriggerMessage(),
                customCatalogItemUserAction.getTriggerMessage()
        );
        EntitiesMapperTest.assertToJsonNullable(baseUserActionEntity.getUrl(), customCatalogItemUserAction.getUrl());

        // Check that the parameters are mapped correctly
        var entityParams = baseUserActionEntity.getParameters();
        var itemParams = customCatalogItemUserAction.getParameters();

        assertThat(itemParams).isNotEmpty();
        assertThat(itemParams).hasSize(entityParams.length);

        for (var i = 0; i < entityParams.length; i++) {
            var entityParam = entityParams[i];
            var itemParam = itemParams.get(i);
            EntitiesMapperTest.assertCatalogItemUserActionParameterMapping(entityParam, itemParam);
        }
    }

}
