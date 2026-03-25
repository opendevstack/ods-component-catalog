package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
        var parameter = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .value("parameterValue")
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .parameters(parameters);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), request);

        // then
        verify(provisionerActionsService).updateComponentProvisioningStatus(projectKey.toUpperCase(),
                status, componentId, catalogItemId, componentUrl, List.of(Pair.of(parameter.getName(), parameter.getValue())));
    }

    @Test
    void givenAProjectKey_whenNotifyProvisioningStatusUpdatePartially_thenServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = Status.CREATING; // any valid Status works, CREATING is an example
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var parameter = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .value("parameterValue")
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .parameters(parameters);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), request);

        // then
        verify(provisionerActionsService).updatePartiallyComponentProvisioningStatus(
                projectKey.toUpperCase(),
                status,
                componentId,
                catalogItemId,
                componentUrl,
                List.of(Pair.of(parameter.getName(), parameter.getValue()))
        );
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
