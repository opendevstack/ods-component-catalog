package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestrictionMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GroupsRestrictionsEvaluatorTest {

    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator = new GroupsRestrictionsEvaluator();

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationPass_AndReturnTrue() {
        // Given
        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-group-1-suffix-2"));
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.getLeft()).isTrue();
    }

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.getLeft()).isFalse();
        assertThat(evaluateResult.getRight()).isEqualTo("Only project members with Manager or Team roles can provision components.");
    }

    @Test
    void givenInvalidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        var projectKey = "projectKey";

        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of();
        RestrictionsParams params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        Pair<Boolean, String> evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.getLeft()).isFalse();
        assertThat(evaluateResult.getRight()).isEqualTo("Only project members with Manager or Team roles can provision components.");
    }
}
