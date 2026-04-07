package org.opendevstack.component_catalog.server.facade;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.jspecify.annotations.NonNull;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsParams;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiFacade {
    private final ProjectsInfoService projectsInfoService;
    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    private final ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps groupsRestrictionProps;

    public static @NonNull List<Pair<@NotNull String, @NotNull List<String>>> map(ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        return provisioningStatusUpdateRequest.getParameters().stream()
                .map(parameter -> Pair.of(parameter.getName(), parameter.getValues()))
                .toList();
    }

    public void validateGroupRestrictions(String projectKey, ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        var groupRestriction = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(groupsRestrictionProps.getPrefix())
                .suffix(groupsRestrictionProps.getSuffix())
                .build();

        var userActionEntityRestrictions = UserActionEntityRestrictions.builder()
                .groups(groupRestriction)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, userActionEntityRestrictions);
        var userGroups = projectsInfoService.getProjectGroups(getIdToken(), provisioningStatusUpdateRequest.getAccessToken());

        var params = RestrictionsParams.builder()
                .userGroups(userGroups)
                .projectKey(projectKey)
                .build();

        if (Boolean.FALSE.equals(groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params).getLeft())) {
            log.error("The user has no permissions to perform this action based on group restrictions for project {}", projectKey);
            throw new ForbiddenException("User not allowed to perform this action");
        }
    }

    public String getIdToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.debug("Authenticated user '{}'", auth.getName());

        var principal = (UserPrincipal) auth.getPrincipal();

        var idToken = principal.getAadIssuedBearerToken();

        log.debug("Extracted idToken: {} from request.", idToken);

        return idToken;
    }

}
