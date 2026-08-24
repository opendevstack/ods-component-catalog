package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(40)
@AllArgsConstructor
@Slf4j
public class OneTimeOnlyRestrictionsEvaluator implements  RestrictionsEvaluator {

    private ProvisionerActionsService provisionerActionsService;

    @Override
    public RestrictionsEvaluatorResult evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        if (!restrictions.restrictions().isOneTimeOnly()) {
            return new RestrictionsEvaluatorResult(true, true, "");
        } else if (evaluateConditions(params)) {
            return new RestrictionsEvaluatorResult(true, true, "");
        } else {
            log.debug("Validation failed for restrictions: {}, and params: {}", restrictions, params);

            return new RestrictionsEvaluatorResult(false, true, "This product can be provisioned only once per project.");
        }
    }

    private boolean evaluateConditions(RestrictionsParams params) {
        return !provisionerActionsService.isCatalogItemAlreadyProvisionedInProject(params.projectKey, params.catalogItemId);
    }
}
