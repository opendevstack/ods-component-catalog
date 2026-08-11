package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(60)
@Slf4j
public class WorkflowsEvaluator implements RestrictionsEvaluator {
    private static final String WORKFLOW_PARAM_NAME = "workflow";
    private static final String WORKFLOW_NAME_PARAM_NAME = "workflow_name";
    private static final String DELETE_WORKFLOW_PARAM_NAME = "deletion_workflow";

    // At this stage, there is no merge with default parameters yet.
    // Having that in mind, we can just evaluate the existence of the workflow parameters in the item configuration.
    @Override
    public Pair<Boolean, String> evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        var paramNames = params.parameters.stream()
                .map(CatalogItemUserActionParameter::getName)
                .toList();

        var provisionWorkflowParamExists = paramNames.contains(WORKFLOW_PARAM_NAME) || paramNames.contains(WORKFLOW_NAME_PARAM_NAME);
        var deleteWorkflowParamExists = paramNames.contains(DELETE_WORKFLOW_PARAM_NAME);

        if (provisionWorkflowParamExists && deleteWorkflowParamExists) {
            return Pair.of(true, "WorkflowsEvaluator: Both provision and delete workflow params exist");
        } else if (!provisionWorkflowParamExists && !deleteWorkflowParamExists) {
            return Pair.of(true, "WorkflowsEvaluator: Neither provision nor delete workflow params exist");
        } else {
            return Pair.of(false, "WorkflowsEvaluator: Either both or neither provision nor delete workflow params should exist. If only one exist, this is a misconfiguration");
        }
    }
}
