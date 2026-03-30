package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterLocation;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationsRestrictionsEvaluatorTest {

    private static final String[] restrictionLoc = {"eu", "us"};

    private final LocationsRestrictionsEvaluator evaluator = new LocationsRestrictionsEvaluator();

    @Test
    void evaluateRestrictions_returnsTrue_whenSharedLocationsMatchClusters() {
        //given
        var projectKey = "projectKey";

        var clusters = List.of("eu");
        var parameters = List.of(CatalogItemUserActionParameterMother.of("locations", "locations",
                new ArrayList<>(), List.of("eu", "us")));

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenNoSharedLocationsMatchClusters() {
        //given
        var projectKey = "projectKey";

        var clusters = List.of("us");
        var parameters = List.of(CatalogItemUserActionParameterMother.of("locations", "locations",
                new ArrayList<>(), List.of("eu", "ch")));

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(false, result.getLeft());
        assertEquals("This product is not provisionable in the project location.", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsRestrictionLocations_whenAllParameterListsAreEmpty() {
        //given
        var projectKey = "projectKey";

        var clusters = List.of("eu");
        var parameters = List.of(CatalogItemUserActionParameterMother.of("locations", "locations",
                new ArrayList<>(), Collections.emptyList()));

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsTrue_whenIntersectionExistsAcrossLists() {
        //given
        var projectKey = "projectKey";

        var clusters = List.of("eu");
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("locations", "locations", new ArrayList<>(), List.of("eu", "ch")),
                CatalogItemUserActionParameterMother.of("locations", "locations", new ArrayList<>(), List.of("eu", "us"))
        );

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenNoCommonValuesAcrossLists() {
        //given
        var projectKey = "projectKey";

        var clusters = List.of("eu");
        var parameters = List.of(
                CatalogItemUserActionParameterMother.of("locations", "locations", new ArrayList<>(), List.of("ch", "us")),
                CatalogItemUserActionParameterMother.of("locations", "locations", new ArrayList<>(), List.of("us"))
        );

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(false, result.getLeft());
        assertEquals("This product is not provisionable in the project location.", result.getRight());
    }

    @Test
    void evaluateRestrictions_doNotConvertsLocationsToLowerCase() {
        //given
        var projectKey = "projectKey";

        var clusters = List.of("eu");
        var parameters = List.of(CatalogItemUserActionParameterMother.of("locations", "locations",
                new ArrayList<>(), List.of("EU", "US")));

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(false, result.getLeft());
        assertEquals("This product is not provisionable in the project location.", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenClustersAreNull() {
        //given
        var projectKey = "projectKey";

        var parameters = new ArrayList<CatalogItemUserActionParameter>();

        List<String> clusters = null;

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(false, result.getLeft());
        assertEquals("This product is not provisionable in the project location.", result.getRight());
    }

    @Test
    void evaluateRestrictions_returnsFalse_whenClustersAreEmpty() {
        //given
        var projectKey = "projectKey";

        var parameters = new ArrayList<CatalogItemUserActionParameter>();

        List<String> clusters = Collections.emptyList();

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(false, result.getLeft());
        assertEquals("This product is not provisionable in the project location.", result.getRight());
    }

    @Test
    void givenRestrictionsWithTwoClusters_AndParamsWithOneCluster_whenEvaluate_AndParamsClusterIsInRestrictions_AndParamsClusterIsNotTheFirstOne_ThenReturnFalse() {
        //given
        var projectKey = "projectKey";

        var parameters = List.of(CatalogItemUserActionParameter.builder()
                        .locations(
                                List.of(
                                        CatalogItemUserActionParameterLocation.builder()
                                        .location("us-test")
                                        .build(),
                                        CatalogItemUserActionParameterLocation.builder()
                                        .location("eu-dev")
                                        .build()
                                )
                        )
                .build());

        String[] restrictionLocations = {"us-test", "eu"};
        List<String> clusters = List.of("eu", "us-test"); // This is what we mean as project locations

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLocations);
        var params = RestrictionsParamsMother.of(parameters, clusters);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        //when
        var result = evaluator.evaluate(evaluationRestrictions, params);

        //then
        assertEquals(false, result.getLeft());
        assertEquals("This product is not provisionable in the project location.", result.getRight());
    }

    @Test
    void GivenRestrictionExpectsLowercaseEu_whenParameterLocationsIsUppercaseEU_thenFails() {
        // given
        var projectKey = "projectKey";
        var restrictions = UserActionEntityRestrictionsMother.of(new String[]{"us-test", "eu"});

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        var params = RestrictionsParamsMother.of(
                List.of(CatalogItemUserActionParameterMother.of(List.of("us-test", "EU", "US-DEV"))),           // no parameters → simplest path
                null
        );

        // when
        Pair<Boolean, String> result = evaluator.evaluate(evaluationRestrictions, params);

        // then
        assertThat(result.getLeft())
                .as("Uppercase 'EU' should NOT match lowercase 'eu'")
                .isFalse();

        assertThat(result.getRight())
                .isEqualTo("This product is not provisionable in the project location.");
    }

}
