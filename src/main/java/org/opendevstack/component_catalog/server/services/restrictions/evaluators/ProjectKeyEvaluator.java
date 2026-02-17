package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(10)
@Slf4j
public class ProjectKeyEvaluator implements RestrictionsEvaluator {

    @Override
    public Pair<Boolean, String> evaluate(EvaluationRestrictions restrictions, RestrictionsParams params) {
        if (validate(restrictions.projectKey())) {
            return Pair.of(true, "");
        } else {
            return Pair.of(false, "You need to have access to a project in order to provision components.");
        }
    }

    private boolean validate(String projectKey) {
        /*
        TODO: Currently with this approach all user actions will require to have a project and defined the locations in the restrictions.
            Since the code action is later overridden to be always requestable, now it works. In the future we may need a way to make these
            restrictions checks more robust. Affected checks: projectKey && evaluateLocationRestrictions.
        */
        return !(projectKey == null || projectKey.isEmpty());
    }
}
