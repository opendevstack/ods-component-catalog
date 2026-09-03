package org.opendevstack.component_catalog.server.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluatorResultMother;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsParams;
import org.opendevstack.component_catalog.util.JwtUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProvisionerActionsApiFacadeTest {

    private static final String PROJECT_KEY = "PROJECT";
    private static final String CATALOG_ITEM_ID = "catalog-item-id";

    @Mock
    private ProjectsInfoService projectsInfoService;
    @Mock
    private GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    @Mock
    private ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps groupsRestrictionProps;
    @Mock
    private AuthenticationFacade authenticationFacade;

    private ProvisionerActionsApiFacade provisionerActionsApiFacade;

    @BeforeEach
    void setUp() {
        var permittedOids = List.of("oid1", "oid2", "oid3");

        provisionerActionsApiFacade = new ProvisionerActionsApiFacade(projectsInfoService,
                groupsRestrictionsEvaluator, groupsRestrictionProps, authenticationFacade, permittedOids);
    }

    @Test
    void map_convertsParametersToPlainParameter() {
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
                Parameter.builder()
                        .name("param1")
                        .values(List.of("value1", "value2"))
                        .build(),
                Parameter.builder()
                        .name("param2")
                        .values(List.of("value3"))
                        .build()
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

    private ProvisioningStatusUpdateRequest requestWithCatalogItemId(String catalogItemId) {
        return new ProvisioningStatusUpdateRequest().catalogItemId(catalogItemId);
    }

     @Test
     void validateGroupRestrictions_whenUserHasPermissions_doesNotThrow() {
         // given
         var accessToken = "accessToken";
         var userGroups = List.of("group1");
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
         when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
         when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
         when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
         when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                 .thenReturn(RestrictionsEvaluatorResultMother.of(true, "allowed"));

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.empty());

             // when / then
             provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request);

             verify(groupsRestrictionsEvaluator)
                     .evaluate(any(EvaluationRestrictions.class), eq(RestrictionsParams.builder()
                             .userGroups(userGroups)
                             .projectKey(PROJECT_KEY)
                             .catalogItemId(CATALOG_ITEM_ID)
                             .build()));
         }
     }

     @Test
     void validateGroupRestrictions_whenUserHasNoPermissions_throwsForbiddenException() {
         // given
         var accessToken = "accessToken";
         var userGroups = List.of("group1");
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
         when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
         when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
         when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
         when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                 .thenReturn(RestrictionsEvaluatorResultMother.of(false, "forbidden"));

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.empty());

             // when / then
             assertThatThrownBy(() -> provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request))
                     .isInstanceOf(ForbiddenException.class)
                     .hasMessage("User not allowed to perform this action");
         }
     }

     @Test
     void validateGroupRestrictions_whenEvaluatorReturnsNull_doesNotThrow() {
         // given
         var accessToken = "accessToken";
         var userGroups = List.of("group1");
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
         when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
         when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
         when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
         when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                 .thenReturn(null);

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.empty());

             // when / then
             provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request);
         }
     }

     @Test
     void validateGroupRestrictions_whenPermittedOidsContainsExtractedOid_bypassesGroupRestrictions() {
         // given
         var accessToken = "accessToken";
         var permittedOid = "oid1";
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.of(permittedOid));

             // when
              provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request);

             // then - Verify that the group restrictions evaluator is never called when oid is in permittedOids
             verify(groupsRestrictionsEvaluator, never())
                     .evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class));
             verify(projectsInfoService, never()).getProjectGroups(accessToken);
         }
     }

     @Test
     void validateGroupRestrictions_whenPermittedOidsContainsExtractedOid_doesNotThrow() {
         // given
         var accessToken = "accessToken";
         var permittedOid = "oid2";
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.of(permittedOid));

             // when / then - should not throw any exception
              provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request);
         }
     }

     @Test
     void validateGroupRestrictions_whenTokenHasNoOid_callsGroupRestrictionsEvaluation() {
         // given
         var accessToken = "accessToken";
         var userGroups = List.of("group1");
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
         when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
         when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
         when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
         when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                 .thenReturn(RestrictionsEvaluatorResultMother.of(true, "allowed"));

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.empty());

             // when
              provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request);

             // then - Verify that the group restrictions evaluator is called when oid is not in permittedOids
             verify(groupsRestrictionsEvaluator)
                     .evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class));
             verify(projectsInfoService).getProjectGroups(accessToken);
         }
     }

     @Test
     void validateGroupRestrictions_whenCatalogItemIdIsNull_throwsInvalidRestEntityException() {
         // given
         var request = requestWithCatalogItemId(null);

         // when / then
         assertThatThrownBy(() -> provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request))
                 .isInstanceOf(InvalidRestEntityException.class)
                 .hasMessage("Catalog item id is null. Cannot validate group restrictions");

         verify(authenticationFacade).getAccessToken();
         verify(groupsRestrictionsEvaluator, never()).evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class));
         verify(projectsInfoService, never()).getProjectGroups(any());
     }

     @Test
     void validateGroupRestrictions_whenExtractedOidIsNotPermitted_callsGroupRestrictionsEvaluation() {
         // given
         var accessToken = "accessToken";
         var userGroups = List.of("group1");
         var request = requestWithCatalogItemId(CATALOG_ITEM_ID);

         when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
         when(groupsRestrictionProps.getPrefix()).thenReturn(List.of("prefix-"));
         when(groupsRestrictionProps.getSuffix()).thenReturn(List.of("-suffix"));
         when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);
         when(groupsRestrictionsEvaluator.evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class)))
                 .thenReturn(RestrictionsEvaluatorResultMother.of(true, "allowed"));

         try (var jwtUtilsMocked = mockStatic(JwtUtils.class)) {
             jwtUtilsMocked.when(() -> JwtUtils.extractClaim(accessToken, "oid"))
                     .thenReturn(Optional.of("non-permitted-oid"));

             // when
             provisionerActionsApiFacade.validateGroupRestrictions(PROJECT_KEY, request);

             // then
             verify(groupsRestrictionsEvaluator)
                     .evaluate(any(EvaluationRestrictions.class), any(RestrictionsParams.class));
             verify(projectsInfoService).getProjectGroups(accessToken);
         }
     }
}

