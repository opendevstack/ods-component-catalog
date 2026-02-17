package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.apache.commons.lang3.tuple.Pair;

public interface RestrictionsEvaluator {

    Pair<Boolean, String> evaluate(EvaluationRestrictions restrictions, RestrictionsParams params);
}
