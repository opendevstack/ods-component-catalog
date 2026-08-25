package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

// Evaluators are triggered BEFORE merge parameters on items.
public interface RestrictionsEvaluator {

    RestrictionsEvaluatorResult evaluate(EvaluationRestrictions restrictions, RestrictionsParams params);
}
