package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

public record RestrictionsEvaluatorResult(boolean requestable, boolean valid, String reason) {
}
