package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(30)
@AllArgsConstructor
@Slf4j
public class OneTimeOnlyRestrictionsEvaluator implements  RestrictionsEvaluator {

    private ProvisionerActionsService provisionerActionsService;

    @Override
    public Pair<Boolean, String> evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        if (!restrictions.restrictions().isOneTimeOnly()) {
            return Pair.of(true, "");
        } else if (evaluateConditions(params)) {
            return Pair.of(true, "");
        } else {
            log.debug("Validation failed for restrictions: {}, and params: {}", restrictions, params);

            return Pair.of(false, "This product can be provisioned only once per project.");
        }
    }

    private boolean evaluateConditions(RestrictionsParams params) {
        return !provisionerActionsService.isCatalogItemAlreadyProvisionedInProject(params.projectKey, params.catalogItemId);
    }
}
