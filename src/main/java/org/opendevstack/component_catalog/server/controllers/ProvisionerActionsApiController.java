package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_catalog.server.api.ProvisionerActionsApi;
import org.opendevstack.component_catalog.server.facade.AuthenticationFacade;
import org.opendevstack.component_catalog.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponentRequest;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.opendevstack.component_catalog.server.facade.ProvisionerActionsApiFacade.map;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiController implements ProvisionerActionsApi {

    public static final String DELETION_REQUESTER = "deletion_requester";
    private final AuthenticationFacade authenticationFacade;

    private final ProvisionerActionsApiFacade provisionerActionsApiFacade;
    private final ProvisionerActionsService provisionerActionsService;


    @SneakyThrows
    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey,
                                                               ProvisioningStatus status,
                                                               ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Received project component update for project key: '{}', provisioningStatusUpdateRequest: {}, ",
                projectKey, provisioningStatusUpdateRequest.toString());

        var normalizedProjectKey = projectKey.toUpperCase();
        provisionerActionsApiFacade.validateGroupRestrictions(normalizedProjectKey);
        var normalizedComponentUrl = provisioningStatusUpdateRequest.getComponentUrl().orElse(Strings.EMPTY);
        var parameters = map(provisioningStatusUpdateRequest);

        var request = ProjectComponentRequest.builder()
                .componentId(provisioningStatusUpdateRequest.getComponentId())
                .catalogItemId(provisioningStatusUpdateRequest.getCatalogItemId())
                .status(Status.valueOf(status.getValue()))
                .componentUrl(normalizedComponentUrl)
                .workflowJobId(provisioningStatusUpdateRequest.getWorkflowJobId().orElse(""))
                .parameters(parameters)
                .build();

        provisionerActionsService.updateComponentProvisioningStatus(normalizedProjectKey, request);

        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdatePartially(String projectKey, ProvisioningStatus status, ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Received partial update notification for project key: '{}', provisioningStatusUpdateRequest: {}, ",
                projectKey, provisioningStatusUpdateRequest.toString());

        var normalizedProjectKey = projectKey.toUpperCase();
        provisionerActionsApiFacade.validateGroupRestrictions(normalizedProjectKey);
        var normalizedComponentUrl = provisioningStatusUpdateRequest.getComponentUrl().orElse(Strings.EMPTY);
        var parameters = map(provisioningStatusUpdateRequest);

        var request = ProjectComponentRequest.builder()
                .componentId(provisioningStatusUpdateRequest.getComponentId())
                .catalogItemId(provisioningStatusUpdateRequest.getCatalogItemId())
                .status(Status.valueOf(status.getValue()))
                .componentUrl(normalizedComponentUrl)
                .workflowJobId(provisioningStatusUpdateRequest.getWorkflowJobId().orElse(""))
                .parameters(parameters)
                .build();

        provisionerActionsService.updatePartiallyComponentProvisioningStatus(normalizedProjectKey, request);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteProvisioningStatus(String projectKey, ProvisioningDeleteRequest provisioningDeleteRequest) {
        log.debug("Received delete provisioning status for project key: '{}', componentId: {}", projectKey, provisioningDeleteRequest.getComponentId());

        try {
            var requester = Optional.ofNullable(provisioningDeleteRequest.getParameters())
                    .flatMap(paramsList -> paramsList.stream().filter(param -> DELETION_REQUESTER.equals(param.getName())).findFirst())
                    .map( requestParam -> requestParam.getValues().stream().findFirst().orElse("N/A"))
                    .orElse("N/A");

            provisionerActionsService.deleteComponentProvisioningStatus(projectKey, provisioningDeleteRequest.getComponentId(), requester);
        } catch (JsonProcessingException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.noContent().build();
    }
}
