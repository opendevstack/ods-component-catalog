package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Order(50)
@Slf4j
public class RequestableProjectsRestrictionsEvaluator implements RestrictionsEvaluator{

    @Override
    public RestrictionsEvaluatorResult evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        if (!skipEvaluation(restrictions.restrictions())) {
            if (evaluateConditions(restrictions.restrictions(), params)) {
                return new RestrictionsEvaluatorResult(true, true, "");
            } else {
                log.debug("Validation failed for restrictions: {}, and params: {}", restrictions, params);

                return new RestrictionsEvaluatorResult(false, true,"In order to get this product, you need to first contact the provider to get an invitation. See the product’s description for more information.");
            }
        } else {
            return new RestrictionsEvaluatorResult(true, true, "");
        }
    }

    private boolean skipEvaluation(UserActionEntityRestrictions restrictions) {
        return restrictions.getProjects() == null || restrictions.getProjects().length == 0;
    }

    private boolean evaluateConditions(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        if (params.getProjectKey() == null || params.getProjectKey().isEmpty()) {
            return false;
        } else {
            return Arrays.stream(restrictions.getProjects()).anyMatch(params.getProjectKey()::equalsIgnoreCase);
        }
    }
}
