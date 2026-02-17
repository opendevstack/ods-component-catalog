package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OneTimeOnlyRestrictionsEvaluatorTest {

    private static final String ONE_TIME_ONLY_MSG = "This product can be provisioned only once per project.";

    private static final String[] restrictionLoc = {"eu", "us"};
    private static final List<String> clustersOk = List.of("eu");
    private static final List<CatalogItemUserActionParameter> parametersOk = List.of(CatalogItemUserActionParameterMother.of("locations", "locations",
            new ArrayList<>(), List.of("eu", "us")));

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @InjectMocks
    private OneTimeOnlyRestrictionsEvaluator oneTimeOnlyRestrictionsEvaluator;

    @Test
    void evaluateRestrictions_returnsFalseAndOneTimeOnlyMessage_whenOneTimeOnly_andAlreadyProvisioned() {
        // given
        var projectKey = "PRJ-001";

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, null, true);
        var catalogItemId = "CAT-001";

        when(provisionerActionsService.isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId)).thenReturn(true);

        List<CatalogItemUserActionParameter> parameters = List.of();

        List<String> userGroups = Collections.emptyList();
        List<String> clusters = null;

        var params = RestrictionsParams.builder()
                .userGroups(userGroups)
                .clusters(clusters)
                .projectKey(projectKey)
                .catalogItemId(catalogItemId)
                .parameters(parameters)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> result = oneTimeOnlyRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(false, result.getLeft());
        assertEquals(ONE_TIME_ONLY_MSG, result.getRight());
        verify(provisionerActionsService, times(1)).isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId);
    }

    @Test
    void evaluateRestrictions_returnsTrue_whenOneTimeOnly_andNotProvisioned_andRestPasses() {
        // given
        var projectKey = "PRJ-001";

        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, null, true);
        var catalogItemId = "CAT-XYZ";

        when(provisionerActionsService.isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId)).thenReturn(false);

        List<String> userGroups = Collections.emptyList();

        var params = RestrictionsParams.builder()
                .userGroups(userGroups)
                .clusters(clustersOk)
                .parameters(parametersOk)
                .userGroups(userGroups)
                .projectKey(projectKey)
                .catalogItemId(catalogItemId)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> result = oneTimeOnlyRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
        verify(provisionerActionsService, times(1)).isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId);
    }

    @Test
    void evaluateRestrictions_returnsTrue_whenNotOneTimeOnly_andRestPasses() {
        // given
        var projectKey = "PRJ-003";
        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, null, false);
        var catalogItemId = "CAT-123";

        List<String> userGroups = Collections.emptyList();

        var params = RestrictionsParams.builder()
                .userGroups(userGroups)
                .clusters(clustersOk)
                .parameters(parametersOk)
                .userGroups(userGroups)
                .projectKey(projectKey)
                .catalogItemId(catalogItemId)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> result = oneTimeOnlyRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(true, result.getLeft());
        assertEquals("", result.getRight());
        verifyNoInteractions(provisionerActionsService);
    }

    @Test
    void evaluateRestrictions_prefersOneTimeOnlyMessage_overProjects_whenAlreadyProvisioned() {
        // given
        var projectKey = Strings.EMPTY;
        var restrictions = UserActionEntityRestrictionsMother.of(restrictionLoc, new String[]{"DEVSTACK"}, true);
        var catalogItemId = "CAT-INV";

        when(provisionerActionsService.isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId)).thenReturn(true);

        List<String> userGroups = Collections.emptyList();

        var params = RestrictionsParams.builder()
                .userGroups(userGroups)
                .clusters(clustersOk)
                .parameters(parametersOk)
                .userGroups(userGroups)
                .projectKey(projectKey)
                .catalogItemId(catalogItemId)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, restrictions);

        // when
        Pair<Boolean, String> result = oneTimeOnlyRestrictionsEvaluator.evaluate(evaluationRestrictions, params);

        // then
        assertEquals(false, result.getLeft());
        assertEquals(ONE_TIME_ONLY_MSG, result.getRight());
        verify(provisionerActionsService, times(1)).isCatalogItemAlreadyProvisionedInProject(projectKey, catalogItemId);
    }
}
