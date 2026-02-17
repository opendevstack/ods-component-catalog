package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;

public record EvaluationRestrictions (String projectKey, UserActionEntityRestrictions restrictions) {
}
