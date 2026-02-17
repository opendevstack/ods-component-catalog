package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestableProjectsRestrictionsEvaluatorTest {

    private static final String INVITATION_MSG =
            "In order to get this product, you need to first contact the provider to get an invitation. See the " +
                    "product’s description for more information.";
    private static final String[] restrictionLoc = {"eu", "us"};
    private static final String[] restrictionProject = {"DEVSTACK"};

    private final RequestableProjectsRestrictionsEvaluator evaluator = new RequestableProjectsRestrictionsEvaluator();

    @Test
    void evaluateRestrictions_returnsTrue_whenProjectsIsNull() {
        // given
        String[] projects = null;
        var projectKey = "PRJ-001";

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, projects);
        var params = RestrictionsParamsMother.of(projectKey);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsTrue_whenProjectsIsEmpty() {
        // given
        var projects = new String[]{};
        var projectKey = "PRJ-001";

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, projects);
        var params = RestrictionsParamsMother.of(projectKey);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenProjectsPresent_andProjectKeyIsNull() {
        // given
        String projectKey = null;

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, restrictionProject);
        var params = RestrictionsParamsMother.of(projectKey);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(false, result.getLeft());
        assertEquals(INVITATION_MSG, result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenProjectsPresent_andProjectKeyIsEmpty() {
        // given
        String projectKey = Strings.EMPTY;

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, restrictionProject);
        var params = RestrictionsParamsMother.of(projectKey);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(false, result.getLeft());
        assertEquals(INVITATION_MSG, result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsTrue_whenProjectKeyMatchesOneProject_caseInsensitive() {
        // given
        var projectKey = "DEVSTACK"; // valida equalsIgnoreCase

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, restrictionProject);
        var params = RestrictionsParamsMother.of(projectKey);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenProjectKeyDoesNotMatchAnyProject() {
        // given
        var projectKey = "PRJ-NOPE";

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, restrictionProject);
        var params = RestrictionsParamsMother.of(projectKey);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(false, result.getLeft());
        assertEquals(INVITATION_MSG, result.getRight());
    }
}
