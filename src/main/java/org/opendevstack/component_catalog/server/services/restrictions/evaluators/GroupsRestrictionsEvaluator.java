package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(20)
@Slf4j
public class GroupsRestrictionsEvaluator implements RestrictionsEvaluator {

    @Override
    public Pair<Boolean, String> evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        if (validate(restrictions.restrictions(), params) && evaluateConditions(restrictions.restrictions(), params)) {
            return Pair.of(true, "");
        } else {
            log.debug("Validation failed for restrictions: {}, and params: {}", restrictions, params);

            return Pair.of(false, "Only project members with Manager or Team roles can provision components.");
        }
    }

    private boolean validate(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        return restrictions.getGroups() != null
                && params.getUserGroups() != null
                && restrictions.getGroups().getPrefix() != null
                && restrictions.getGroups().getSuffix() != null;
    }

    private boolean evaluateConditions(UserActionEntityRestrictions restrictions, RestrictionsParams params) {
        return params.getUserGroups().stream().anyMatch(group ->
                restrictions.getGroups().getPrefix().stream().anyMatch(group::startsWith) &&
                        restrictions.getGroups().getSuffix().stream().anyMatch(group::endsWith));
    }
}
