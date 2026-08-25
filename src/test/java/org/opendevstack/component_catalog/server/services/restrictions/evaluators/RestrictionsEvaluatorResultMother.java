package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

public class RestrictionsEvaluatorResultMother {

    public static RestrictionsEvaluatorResult of() {
        return of(true, "Dummy evaluator always returns true");
    }

    public static RestrictionsEvaluatorResult of(boolean requestable, String reason) {
        return new RestrictionsEvaluatorResult(requestable, true, reason);
    }
}
