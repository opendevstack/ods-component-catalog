package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.apache.commons.lang3.tuple.Pair;
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
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEmpty();
    }

    @Test
    void givenNullProjectKey_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        RestrictionsParams params = RestrictionsParamsMother.of();
        EvaluationRestrictions restrictions =
                new EvaluationRestrictions(null, null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight()).isEqualTo("You need to have access to a project in order to provision components.");
    }

    @Test
    void givenEmptyProjectKey_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        RestrictionsParams params = RestrictionsParamsMother.of();
        EvaluationRestrictions restrictions =
                new EvaluationRestrictions("", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight()).isEqualTo("You need to have access to a project in order to provision components.");
    }
}
