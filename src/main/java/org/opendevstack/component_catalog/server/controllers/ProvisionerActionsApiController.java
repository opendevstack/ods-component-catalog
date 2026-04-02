package org.opendevstack.component_catalog.server.controllers;

import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.tuple.Pair;
import org.jspecify.annotations.NonNull;
import org.opendevstack.component_catalog.server.api.ProvisionerActionsApi;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.EvaluationRestrictions;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.GroupsRestrictionsEvaluator;
import org.opendevstack.component_catalog.server.services.restrictions.evaluators.RestrictionsParams;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiController implements ProvisionerActionsApi {

    private final ProvisionerActionsService provisionerActionsService;
    private final GroupsRestrictionsEvaluator groupsRestrictionsEvaluator;
    private final ApplicationPropertiesConfiguration.CatalogItemUserActionGroupsRestrictionProps groupsRestrictionProps;
    private final AuthorizationInfo authorizationInfo;

    @SneakyThrows
    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey,
                                                               String status,
                                                               ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Received project component update for project key: '{}', provisioningStatusUpdateRequest: {}, ",
                projectKey, provisioningStatusUpdateRequest.toString());

        var normalizedProjectKey = projectKey.toUpperCase();
        validateGroupRestrictions(normalizedProjectKey);
        var normalizedComponentUrl = provisioningStatusUpdateRequest.getComponentUrl().orElse(Strings.EMPTY);
        var parameters = map(provisioningStatusUpdateRequest);

        provisionerActionsService.updateComponentProvisioningStatus(normalizedProjectKey, Status.valueOf(status),
                    provisioningStatusUpdateRequest.getComponentId(), provisioningStatusUpdateRequest.getCatalogItemId(),
                    normalizedComponentUrl, parameters);

        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdatePartially(String projectKey, String status, ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Received partial update notification for project key: '{}', provisioningStatusUpdateRequest: {}, ",
                projectKey, provisioningStatusUpdateRequest.toString());

        var normalizedProjectKey = projectKey.toUpperCase();
        validateGroupRestrictions(normalizedProjectKey);
        var normalizedComponentUrl = provisioningStatusUpdateRequest.getComponentUrl().orElse(Strings.EMPTY);
        var parameters = map(provisioningStatusUpdateRequest);

        provisionerActionsService.updatePartiallyComponentProvisioningStatus(normalizedProjectKey, Status.valueOf(status),
                provisioningStatusUpdateRequest.getComponentId(), provisioningStatusUpdateRequest.getCatalogItemId(),
                normalizedComponentUrl, parameters);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProvisioningStatus(String projectKey, ProvisioningDeleteRequest provisioningDeleteRequest) {
        log.debug("Received delete provisioning status for project key: '{}', componentId: {}", projectKey, provisioningDeleteRequest.getComponentId());

        try {
            provisionerActionsService.deleteComponentProvisioningStatus(projectKey, provisioningDeleteRequest.getComponentId());
        } catch (JsonProcessingException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok().build();
    }

    private static @NonNull List<Pair<@NotNull String, @NotNull List<String>>> map(ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        return provisioningStatusUpdateRequest.getParameters().stream()
                .map(parameter -> Pair.of(parameter.getName(), parameter.getValues()))
                .toList();
    }

    private void validateGroupRestrictions(String projectKey) {
        var groupRestriction = CatalogItemUserActionGroupsRestriction.builder()
                .prefix(groupsRestrictionProps.getPrefix())
                .suffix(groupsRestrictionProps.getSuffix())
                .build();

        var userActionEntityRestrictions = UserActionEntityRestrictions.builder()
                .groups(groupRestriction)
                .build();

        var evaluationRestrictions = new EvaluationRestrictions(projectKey, userActionEntityRestrictions);

        var params = RestrictionsParams.builder()
                .userGroups(authorizationInfo.getCurrentRoles())
                .projectKey(projectKey)
                .build();

        if (Boolean.FALSE.equals(groupsRestrictionsEvaluator.evaluate(evaluationRestrictions, params).getLeft())) {
            log.error("The user has no permissions to perform this action based on group restrictions for project {}", projectKey);
            throw new ForbiddenException("User not allowed to perform this action");
        }
    }
}
