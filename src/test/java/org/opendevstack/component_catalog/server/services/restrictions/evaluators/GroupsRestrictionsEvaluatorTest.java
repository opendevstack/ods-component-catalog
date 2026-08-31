package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.services.RolesWhitelistedService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestrictionMother;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;

import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupsRestrictionsEvaluatorTest {

    private static final String CATALOG_ITEM_ID = Base64.getUrlEncoder().encodeToString("catalog-item".getBytes());

    @Mock
    private RolesWhitelistedService rolesWhitelistedService;

    @InjectMocks
    private GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationPass_AndReturnTrue() {
        // Given
        when(rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(anyString())).thenReturn(List.of());

        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-projectKey-suffix-2"));
        params.setProjectKey(projectKey);
        params.setCatalogItemId(CATALOG_ITEM_ID);
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isTrue();
    }

    @Test
    void givenValidRestrictions_AndInValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        when(rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(anyString())).thenReturn(List.of());

        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-group-1-suffix-2"));
        params.setCatalogItemId(CATALOG_ITEM_ID);
        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
    }

    @Test
    void givenValidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        when(rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(anyString())).thenReturn(List.of());

        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);
        RestrictionsParams params = RestrictionsParamsMother.of();
        params.setCatalogItemId(CATALOG_ITEM_ID);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
        assertThat(evaluateResult.reason()).isEqualTo("Only project members with Manager or Team roles can provision this component.");
    }

    @Test
    void givenInvalidRestrictions_AndValidParams_whenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        when(rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(anyString())).thenReturn(List.of());

        var projectKey = "projectKey";

        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of();
        RestrictionsParams params = RestrictionsParamsMother.of();
        params.setCatalogItemId(CATALOG_ITEM_ID);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
        assertThat(evaluateResult.reason()).isEqualTo("Only project members with Manager or Team roles can provision this component.");
    }

    @Test
    void givenWhitelistedRole_WhenEvaluate_ThenEvaluationPass_AndReturnTrue() {
        // Given
        when(rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(anyString())).thenReturn(List.of("CUSTOM_ROLE"));

        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);

        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-projectKey-CUSTOM_ROLE"));
        params.setProjectKey(projectKey);
        params.setCatalogItemId(CATALOG_ITEM_ID);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isTrue();
    }

    @Test
    void givenWhitelistedRole_AndUserNotInRole_WhenEvaluate_ThenEvaluationNotPass_AndReturnFalse() {
        // Given
        when(rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(anyString())).thenReturn(List.of("CUSTOM_ROLE"));

        var projectKey = "projectKey";

        CatalogItemUserActionGroupsRestriction groupsRestriction = CatalogItemUserActionGroupsRestrictionMother.of();
        UserActionEntityRestrictions restrictions = UserActionEntityRestrictionsMother.of(groupsRestriction);

        RestrictionsParams params = RestrictionsParamsMother.of(List.of("prefix-1-projectKey-OTHER_ROLE"));
        params.setProjectKey(projectKey);
        params.setCatalogItemId(CATALOG_ITEM_ID);

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // When
        RestrictionsEvaluatorResult evaluateResult = groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // Then
        assertThat(evaluateResult.requestable()).isFalse();
    }
}