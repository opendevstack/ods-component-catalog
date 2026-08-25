package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkflowsEvaluatorTest {

    private final WorkflowsEvaluator evaluator = new WorkflowsEvaluator();

    private static final String MISCONFIGURATION_ERROR = "WorkflowsEvaluator: Either both or neither provision nor delete workflow params should exist. If only one exist, this is a misconfiguration";
    private static final String BOTH_PARAMS_SUCCESS = "WorkflowsEvaluator: Both provision and delete workflow params exist";
    private static final String NEITHER_PARAMS_SUCCESS = "WorkflowsEvaluator: Neither provision nor delete workflow params exist";

    @ParameterizedTest
    @CsvSource({
            "workflow,deletion_workflow",
            "workflow_name,deletion_workflow",
            "workflow,deletion_workflow_name",
            "workflow_name,deletion_workflow_name"
    })
    void givenBothWorkflowParams_whenEvaluate_thenReturnTrueAndSuccessMessage(String provisionParam, String deleteParam) {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of(provisionParam, "string"),
                CatalogItemUserActionParameterMother.of(deleteParam, "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isTrue();
        assertThat(result.reason()).isEqualTo(BOTH_PARAMS_SUCCESS);
    }

    @Test
    void givenBothProvisionWorkflowVariants_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("workflow", "string"),
                CatalogItemUserActionParameterMother.of("workflow_name", "string"),
                CatalogItemUserActionParameterMother.of("deletion_workflow", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isTrue();
        assertThat(result.reason()).isEqualTo(BOTH_PARAMS_SUCCESS);
    }

    @ParameterizedTest
    @CsvSource({
            "other_param,another_param",
            "some_random_param,another_random_param"
    })
    void givenNoWorkflowParams_whenEvaluate_thenReturnTrueAndSuccessMessage(String param1, String param2) {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of(param1, "string"),
                CatalogItemUserActionParameterMother.of(param2, "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isTrue();
        assertThat(result.reason()).isEqualTo(NEITHER_PARAMS_SUCCESS);
    }

    @Test
    void givenEmptyParametersList_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var params = RestrictionsParamsMother.of(Collections.emptyList(), Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isTrue();
        assertThat(result.reason()).isEqualTo(NEITHER_PARAMS_SUCCESS);
    }

    @ParameterizedTest
    @CsvSource({
            "workflow",
            "workflow_name",
            "deletion_workflow",
            "deletion_workflow_name"
    })
    void givenOnlyOneWorkflowParam_whenEvaluate_thenReturnFalseAndErrorMessage(String workflowParam) {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of(workflowParam, "string"),
                CatalogItemUserActionParameterMother.of("other_param", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isFalse();
        assertThat(result.reason()).isEqualTo(MISCONFIGURATION_ERROR);
    }

    @Test
    void givenProvisionAndDeleteWorkflowWithOtherParams_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("workflow", "string"),
                CatalogItemUserActionParameterMother.of("deletion_workflow", "string"),
                CatalogItemUserActionParameterMother.of("custom_param_1", "string"),
                CatalogItemUserActionParameterMother.of("custom_param_2", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        RestrictionsEvaluatorResult result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.requestable()).isTrue();
        assertThat(result.reason()).isEqualTo(BOTH_PARAMS_SUCCESS);
    }
}

