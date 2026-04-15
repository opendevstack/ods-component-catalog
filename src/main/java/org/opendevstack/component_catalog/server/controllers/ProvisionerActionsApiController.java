package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.opendevstack.component_catalog.server.api.ProvisionerActionsApi;
import org.opendevstack.component_catalog.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.opendevstack.component_catalog.server.facade.ProvisionerActionsApiFacade.map;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProvisionerActionsApiController implements ProvisionerActionsApi {

    private final ProvisionerActionsApiFacade provisionerActionsApiFacade;
    private final ProvisionerActionsService provisionerActionsService;


    @SneakyThrows
    @Override
    public ResponseEntity<Void> notifyProvisioningStatusUpdate(String projectKey,
                                                               String status,
                                                               ProvisioningStatusUpdateRequest provisioningStatusUpdateRequest) {
        log.debug("Received project component update for project key: '{}', provisioningStatusUpdateRequest: {}, ",
                projectKey, provisioningStatusUpdateRequest.toString());

        var normalizedProjectKey = projectKey.toUpperCase();
        provisionerActionsApiFacade.validateGroupRestrictions(normalizedProjectKey);
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
        provisionerActionsApiFacade.validateGroupRestrictions(normalizedProjectKey);
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
}
