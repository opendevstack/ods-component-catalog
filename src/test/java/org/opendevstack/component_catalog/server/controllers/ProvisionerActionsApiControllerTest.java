package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @InjectMocks
    private ProvisionerActionsApiController provisionerActionsApiController;

    @Test
    void givenAProjectKey_whenNotifyProvisioningCompleted_thenServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = Status.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), request);

        // then
        verify(provisionerActionsService).updateComponentProvisioningStatus(projectKey.toUpperCase(),
                status, componentId, catalogItemId, componentUrl);
    }

    @Test
    void givenAProjectKey_whenDeleteProvisioningStatus_thenServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

        var request = new ProvisioningDeleteRequest()
                .componentId(componentId);

        // when
        provisionerActionsApiController.deleteProvisioningStatus(projectKey, request);

        // then
        verify(provisionerActionsService).deleteComponentProvisioningStatus(projectKey, componentId);
    }
}
