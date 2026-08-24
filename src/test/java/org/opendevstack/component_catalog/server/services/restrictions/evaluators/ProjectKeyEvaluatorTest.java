package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectKeyEvaluatorTest {

    private final ProjectKeyEvaluator evaluator = new ProjectKeyEvaluator();

    @Test
    void givenValidProjectKey_whenEvaluate_thenReturnTrueAndEmptyMessage() {
        // given
        RestrictionsParams params = RestrictionsParamsMother.of();
        EvaluationRestrictions restrictions =
                new EvaluationRestrictions("PROJECT_123", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isTrue();
        assertThat(result.reason()).isEmpty();
    }

    @Test
    void givenNullProjectKey_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        RestrictionsParams params = RestrictionsParamsMother.of();
        EvaluationRestrictions restrictions =
                new EvaluationRestrictions(null, null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isFalse();
        assertThat(result.reason()).isEqualTo("You need to have access to a project in order to provision components.");
    }

    @Test
    void givenEmptyProjectKey_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        RestrictionsParams params = RestrictionsParamsMother.of();
        EvaluationRestrictions restrictions =
                new EvaluationRestrictions("", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isFalse();
        assertThat(result.reason()).isEqualTo("You need to have access to a project in order to provision components.");
    }
}
