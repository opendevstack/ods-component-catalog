package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WorkflowsEvaluatorTest {

    private final WorkflowsEvaluator evaluator = new WorkflowsEvaluator();

    @Test
    void givenBothWorkflowParams_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("workflow", "string"),
                CatalogItemUserActionParameterMother.of("deletion_workflow", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Both provision and delete workflow params exist");
    }

    @Test
    void givenWorkflowNameAndDeletionWorkflow_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("workflow_name", "string"),
                CatalogItemUserActionParameterMother.of("deletion_workflow", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Both provision and delete workflow params exist");
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
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Both provision and delete workflow params exist");
    }

    @Test
    void givenNoWorkflowParams_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("other_param", "string"),
                CatalogItemUserActionParameterMother.of("another_param", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Neither provision nor delete workflow params exist");
    }

    @Test
    void givenEmptyParametersList_whenEvaluate_thenReturnTrueAndSuccessMessage() {
        // given
        var params = RestrictionsParamsMother.of(Collections.emptyList(), Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Neither provision nor delete workflow params exist");
    }

    @Test
    void givenOnlyProvisionWorkflow_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("workflow", "string"),
                CatalogItemUserActionParameterMother.of("other_param", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Either both or neither provision nor delete workflow params should exist. If only one exist, this is a misconfiguration");
    }

    @Test
    void givenOnlyWorkflowName_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("workflow_name", "string"),
                CatalogItemUserActionParameterMother.of("other_param", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Either both or neither provision nor delete workflow params should exist. If only one exist, this is a misconfiguration");
    }

    @Test
    void givenOnlyDeletionWorkflow_whenEvaluate_thenReturnFalseAndErrorMessage() {
        // given
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("deletion_workflow", "string"),
                CatalogItemUserActionParameterMother.of("other_param", "string")
        );
        var params = RestrictionsParamsMother.of(parameters, Collections.emptyList());
        var restrictions = new EvaluationRestrictions("PROJECT_KEY", null);

        // when
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isFalse();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Either both or neither provision nor delete workflow params should exist. If only one exist, this is a misconfiguration");
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
        Pair<Boolean, String> result = evaluator.evaluate(restrictions, params);

        // then
        assertThat(result.getLeft()).isTrue();
        assertThat(result.getRight()).isEqualTo("WorkflowsEvaluator: Both provision and delete workflow params exist");
    }
}

