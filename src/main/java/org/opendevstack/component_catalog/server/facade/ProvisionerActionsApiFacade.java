package org.opendevstack.component_catalog.server.facade;

import jakarta.validation.constraints.NotNull;
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
import org.opendevstack.component_catalog.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProvisionerActionsApiFacade {
    private final ProjectsInfoService projectsInfoService;
    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    private final ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps groupsRestrictionProps;
    private final AuthenticationFacade authenticationFacade;

    private final List<String> permittedOids;

    public ProvisionerActionsApiFacade(ProjectsInfoService projectsInfoService,
                                       GroupsRestrictionsEvaluator groupsRestrictionsEvaluator,
                                       ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps groupsRestrictionProps,
                                       AuthenticationFacade authenticationFacade,
                                       @Value("${devstack.marketplace-api.permitted-oids}") List<String> permittedOids) {
        this.projectsInfoService = projectsInfoService;
        this.groupsRestrictionsEvaluator = groupsRestrictionsEvaluator;
        this.groupsRestrictionProps = groupsRestrictionProps;
        this.authenticationFacade = authenticationFacade;
        this.permittedOids = permittedOids;
    }


    public static @NonNull List<Pair<@NotNull String, @NotNull List<String>>> map(ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        return provisioningStatusUpdateRequest.getParameters().stream()
                .map(parameter -> Pair.of(parameter.getName(), parameter.getValues()))
                .toList();
    }

    public void validateGroupRestrictions(String projectKey) {
        var accessToken = authenticationFacade.getAccessToken();

        var oid = JwtUtils.extractClaim(accessToken, "oid");

        boolean isAValidApplicationToken = oid.map(permittedOids::contains).orElse(false);

        if (isAValidApplicationToken) {
            log.debug("Token with oid '{}' is allowed to bypass group restrictions for project {}", oid.orElse("unknown"), projectKey);
        } else {
            validateGroupRestrictions(projectKey, accessToken);
        }
    }

    private void validateGroupRestrictions(String projectKey, String accessToken) {
        var groupRestriction = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(groupsRestrictionProps.getPrefix())
                .suffix(groupsRestrictionProps.getSuffix())
                .build();

        var userActionEntityRestrictions = UserActionEntityRestrictions.builder()
                .groups(groupRestriction)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, userActionEntityRestrictions);
        var userGroups = projectsInfoService.getProjectGroups(accessToken);

        var params = RestrictionsParams.builder()
                .userGroups(userGroups)
                .projectKey(projectKey)
                .build();

        if (Boolean.FALSE.equals(groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params).getLeft())) {
            log.error("The user has no permissions to perform this action based on group restrictions for project {}", projectKey);
            throw new ForbiddenException("User not allowed to perform this action");
        }
    }

}