package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(10)
@Slf4j
public class WorkflowsEvaluator implements RestrictionsEvaluator {
    private static final String WORKFLOW_PARAM_NAME = "workflow";
    private static final String WORKFLOW_NAME_PARAM_NAME = "workflow_name";
    private static final String DELETE_WORKFLOW_PARAM_NAME = "deletion_workflow";
    private static final String DELETE_WORKFLOW_NAME_PARAM_NAME = "deletion_workflow_name";

    // At this stage, there is no merge with default parameters yet.
    // Having that in mind, we can just evaluate the existence of the workflow parameters in the item configuration.
    @Override
    public RestrictionsEvaluatorResult evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        var paramNames = params.parameters.stream()
                .map(CatalogItemUserActionParameter::getName)
                .toList();

        var provisionWorkflowParamExists = paramNames.contains(WORKFLOW_PARAM_NAME) || paramNames.contains(WORKFLOW_NAME_PARAM_NAME);
        var deleteWorkflowParamExists = paramNames.contains(DELETE_WORKFLOW_PARAM_NAME) || paramNames.contains(DELETE_WORKFLOW_NAME_PARAM_NAME);

        if (provisionWorkflowParamExists && deleteWorkflowParamExists) {
            return new RestrictionsEvaluatorResult(true, true, "WorkflowsEvaluator: Both provision and delete workflow params exist");
        } else if (!provisionWorkflowParamExists && !deleteWorkflowParamExists) {
            return new RestrictionsEvaluatorResult(true, true, "WorkflowsEvaluator: Neither provision nor delete workflow params exist");
        } else {
            return new RestrictionsEvaluatorResult(false, false, "WorkflowsEvaluator: Either both or neither provision nor delete workflow params should exist. If only one exist, this is a misconfiguration");
        }
    }
}
