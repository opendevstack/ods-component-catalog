package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.opendevstack.component_catalog.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @Mock
    private ProvisionerActionsApiFacade provisionerActionsApiFacade;

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
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .parameters(parameters);

        var mappedParameters = List.of(Pair.of(parameter.getName(), parameter.getValues()));

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(eq(projectKey.toUpperCase()), eq(request));
        verify(provisionerActionsService).updateComponentProvisioningStatus(projectKey.toUpperCase(),
                status, componentId, catalogItemId, componentUrl, mappedParameters);
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
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .parameters(parameters);

        var mappedParameters = List.of(Pair.of(parameter.getName(), parameter.getValues()));

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(eq(projectKey.toUpperCase()), eq(request));
        verify(provisionerActionsService).updatePartiallyComponentProvisioningStatus(
                projectKey.toUpperCase(),
                status,
                componentId,
                catalogItemId,
                componentUrl,
                mappedParameters
        );
    }

    @Test
    void givenAProjectKeyAndNoComponentUrl_whenNotifyProvisioningStatusUpdatePartially_thenServiceIsCalledWithEmptyUrl() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = Status.CREATING;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var parameter = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .parameters(parameters);

        var mappedParameters = List.of(Pair.of(parameter.getName(), parameter.getValues()));

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status.name(), request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(eq(projectKey.toUpperCase()), eq(request));
        verify(provisionerActionsService).updatePartiallyComponentProvisioningStatus(projectKey.toUpperCase(),
                status, componentId, catalogItemId, "", mappedParameters);
    }

    @Test
    void givenAProjectKeyAndNoComponentUrl_whenNotifyProvisioningStatusUpdate_thenServiceIsCalledWithEmptyUrl() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = Status.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var parameter = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .parameters(parameters);

        var mappedParameters = List.of(Pair.of(parameter.getName(), parameter.getValues()));

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdate(projectKey, status.name(), request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(eq(projectKey.toUpperCase()), eq(request));
        verify(provisionerActionsService).updateComponentProvisioningStatus(projectKey.toUpperCase(),
                status, componentId, catalogItemId, "", mappedParameters);
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

    @Test
    void givenAProjectKey_whenDeleteProvisioningStatusThrowsException_thenUnprocessableEntityIsReturned() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";

        var request = new ProvisioningDeleteRequest()
                .componentId(componentId);

        org.mockito.Mockito.doThrow(new JsonProcessingException("Error") {})
                .when(provisionerActionsService).deleteComponentProvisioningStatus(projectKey, componentId);

        // when
        var response = provisionerActionsApiController.deleteProvisioningStatus(projectKey, request);

        // then
        org.junit.jupiter.api.Assertions.assertEquals(org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }
}
