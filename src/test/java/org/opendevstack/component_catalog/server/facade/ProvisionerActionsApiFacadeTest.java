package org.opendevstack.component_catalog.server.facade;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsParams;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiFacadeTest {

    @Mock
    private ProjectsInfoService projectsInfoService;
    @Mock
    private GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    @Mock
    private ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps groupsRestrictionProps;
    @Mock
    private AuthenticationFacade authenticationFacade;
    @InjectMocks
    private ProvisionerActionsApiFacade provisionerActionsApiFacade;

    @Test
    void map_convertsParametersToPairs() {
        // given
        var parameter1 = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("param1")
                .values(List.of("value1", "value2"))
                .build();
        var parameter2 = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("param2")
                .values(List.of("value3"))
                .build();
        var request = new ProvisioningStatusUpdateRequest()
                .parameters(List.of(parameter1, parameter2));

        // when
        var result = ProvisionerActionsApiFacade.map(request);

        // then
        assertThat(result).containsExactly(
                Pair.of("param1", List.of("value1", "value2")),
                Pair.of("param2", List.of("value3"))
        );
    }

    @Test
    void map_withEmptyParameters_returnsEmptyList() {
        // given
        var request = new ProvisioningStatusUpdateRequest().parameters(List.of());

        // when
        var result = ProvisionerActionsApiFacade.map(request);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void validateGroupRestrictions_whenUserHasPermissions_doesNotThrow() {
        // given
        var projectKey = "PROJECT";
        var accessToken = "accessToken";
        var request = new ProvisioningStatusUpdateRequest();
        var userGroups = List.of("group1");

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
        when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
        when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                .thenReturn(Pair.of(true, "Allowed"));

        // when / then
        provisionerActionsApiFacade.validateGroupRestrictions(projectKey, request);
    }

    @Test
    void validateGroupRestrictions_whenUserHasNoPermissions_throwsForbiddenException() {
        // given
        var projectKey = "PROJECT";
        var accessToken = "accessToken";
        var request = new ProvisioningStatusUpdateRequest();
        var userGroups = List.of("group1");

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
        when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
        when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                .thenReturn(Pair.of(false, "Forbidden"));

        // when / then
        assertThatThrownBy(() -> provisionerActionsApiFacade.validateGroupRestrictions(projectKey, request))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage("User not allowed to perform this action");
    }

    @Test
    void validateGroupRestrictions_whenEvaluatorReturnsNull_doesNotThrow() {
        // given
        var projectKey = "PROJECT";
        var accessToken = "accessToken";
        var request = new ProvisioningStatusUpdateRequest();
        var userGroups = List.of("group1");

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
        when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
        when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                .thenReturn(Pair.of(null, "Unknown"));

        // when / then
        provisionerActionsApiFacade.validateGroupRestrictions(projectKey, request);
    }
}
