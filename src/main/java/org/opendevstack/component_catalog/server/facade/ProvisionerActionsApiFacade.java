package org.opendevstack.component_catalog.server.facade;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsEvaluatorResult;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsParams;
import org.opendevstack.component_catalog.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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


    public static @NonNull List<Parameter> map(ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        return provisioningStatusUpdateRequest.getParameters().stream()
                .map(param -> Parameter.builder().name(param.getName()).values(param.getValues()).build())
                .toList();
    }

    public void validateGroupRestrictions(String projectKey, ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        var accessToken = authenticationFacade.getAccessToken();
        var catalogItemId = provisioningStatusUpdateRequest.getCatalogItemId();

        if (catalogItemId == null) {
            log.error("Catalog item id is null. Cannot validate group restrictions");

            throw new InvalidRestEntityException("Catalog item id is null. Cannot validate group restrictions");
        }

        var oid = JwtUtils.extractClaim(accessToken, "oid");

        boolean isAValidApplicationToken = oid.map(permittedOids::contains).orElse(false);

        if (isAValidApplicationToken) {
            log.debug("Token with oid '{}' is allowed to bypass group restrictions for project {}", oid.orElse("unknown"), projectKey);
        } else {
            log.debug("Token with oid '{}' is NOT allowed to bypass group restrictions for project {}. Validating group restrictions", oid.orElse("unknown"), projectKey);

            validateGroupRestrictions(projectKey, catalogItemId, accessToken);
        }
    }

    private void validateGroupRestrictions(String projectKey, String catalogItemId, String accessToken) {
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
                .catalogItemId(catalogItemId)
                .build();

        var requestable = Optional.ofNullable(groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params))
                .map(RestrictionsEvaluatorResult::requestable)
                .orElse(true);

        if (!requestable) {
            log.error("The user has no permissions to perform this action based on group restrictions for project {}", projectKey);
            throw new ForbiddenException("User not allowed to perform this action");
        }
    }

}