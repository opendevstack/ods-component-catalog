package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestrictionMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;

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
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-projectKey-suffix-2"));
        params.setProjectKey(projectKey);
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isTrue();
    }

    @Test
    void givenValidRestrictions_AndInValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-group-1-suffix-2"));
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
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
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
        assertThat(evaluateResult.reason()).isEqualTo("Only project members with Manager or Team roles can provision components.");
    }

    @Test
    void givenInvalidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        var projectKey = "projectKey";

        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of();
        RestrictionsParams params = RestrictionsParamsMother.of();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
        assertThat(evaluateResult.reason()).isEqualTo("Only project members with Manager or Team roles can provision components.");
    }
}
