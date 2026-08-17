package org.opendevstack.component_catalog.server.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.opendevstack.component_catalog.server.facade.ProvisionerActionsApiFacade;
import org.opendevstack.component_catalog.server.model.ProvisioningDeleteRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequest;
import org.opendevstack.component_catalog.server.model.ProvisioningStatusUpdateRequestParametersInner;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponentRequest;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ProvisionerActionsApiControllerTest {

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @Mock
    private ProvisionerActionsApiFacade provisionerActionsApiFacade;

    @InjectMocks
    private ProvisionerActionsApiController provisionerActionsApiController;

    // helper
    private ProjectComponentRequest request(String componentId,
                                            String catalogItemId,
                                            Status status,
                                            String url,
                                            String workflowJobId,
                                            List<Parameter> params) {
        return ProjectComponentRequest.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .status(status)
                .componentUrl(url)
                .workflowJobId(workflowJobId)
                .createdAt(null)
                .updatedAt(null)
                .parameters(params)
                .build();
    }

    @Test
    void givenAProjectKey_whenNotifyProvisioningCompleted_thenServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = ProvisioningStatus.CREATED;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var workflowJobId = "123456789";
        var parameterInner = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parametersInner = List.of(parameterInner);
        var parameter = Parameter.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .workflowJobId(workflowJobId)
                .parameters(parametersInner);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdate(projectKey, status, request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(projectKey.toUpperCase());
        verify(provisionerActionsService).updateComponentProvisioningStatus(projectKey.toUpperCase(),
                request(componentId, catalogItemId, Status.CREATED, componentUrl, workflowJobId, parameters)
        );
    }

    @Test
    void givenAProjectKey_whenNotifyProvisioningStatusUpdatePartially_thenServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = ProvisioningStatus.CREATING; // any valid Status works, CREATING is an example
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var componentUrl = "componentUrl";
        var workflowJobId = "123456789";
        var parameterInner = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parametersInner = List.of(parameterInner);
        var parameter = Parameter.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .componentUrl(componentUrl)
                .workflowJobId(workflowJobId)
                .parameters(parametersInner);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status, request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(projectKey.toUpperCase());
        verify(provisionerActionsService).updatePartiallyComponentProvisioningStatus(
                projectKey.toUpperCase(),
                request(componentId, catalogItemId, Status.CREATING, componentUrl, workflowJobId, parameters)
        );
    }

    @Test
    void givenAProjectKeyAndNoComponentUrl_whenNotifyProvisioningStatusUpdatePartially_thenServiceIsCalledWithEmptyUrl() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = ProvisioningStatus.CREATING;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var workflowJobId = "123456789";
        var parameterInner = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parametersInner = List.of(parameterInner);
        var parameter = Parameter.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .workflowJobId(workflowJobId)
                .parameters(parametersInner);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdatePartially(projectKey, status, request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(eq(projectKey.toUpperCase()));
        verify(provisionerActionsService).updatePartiallyComponentProvisioningStatus(projectKey.toUpperCase(),
                request(componentId, catalogItemId, Status.CREATING, "", workflowJobId, parameters)
        );
    }

    @Test
    void givenAProjectKeyAndNoComponentUrl_whenNotifyProvisioningStatusUpdate_thenServiceIsCalledWithEmptyUrl() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var status = ProvisioningStatus.CREATING;
        var componentId = "componentId";
        var catalogItemId = "catalogItemId";
        var workflowJobId = "123456789";
        var parameterInner = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parametersInner = List.of(parameterInner);
        var parameter = Parameter.builder()
                .name("parameterName")
                .values(List.of("parameterValue"))
                .build();
        var parameters = List.of(parameter);

        var request = new ProvisioningStatusUpdateRequest()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .workflowJobId(workflowJobId)
                .parameters(parametersInner);

        // when
        provisionerActionsApiController.notifyProvisioningStatusUpdate(projectKey, status, request);

        // then
        verify(provisionerActionsApiFacade).validateGroupRestrictions(eq(projectKey.toUpperCase()));
        verify(provisionerActionsService).updateComponentProvisioningStatus(projectKey.toUpperCase(),
                request(componentId, catalogItemId, Status.CREATING, "", workflowJobId, parameters)
        );
    }

    @Test
    void givenAProjectKey_whenDeleteProvisioningStatus_thenServiceIsCalled() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var requester = "test.user";
        var requesterParam = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name(ProvisionerActionsApiController.DELETION_REQUESTER)
                .values(List.of(requester))
                .build();

        var request = new ProvisioningDeleteRequest()
                .componentId(componentId)
                .parameters(List.of(requesterParam));

        // when
        provisionerActionsApiController.deleteProvisioningStatus(projectKey, request);

        // then
        verify(provisionerActionsService).deleteComponentProvisioningStatus(projectKey, componentId, requester);
    }

    @Test
    void givenAProjectKey_whenDeleteProvisioningStatusThrowsException_thenUnprocessableEntityIsReturned() throws JsonProcessingException {
        // given
        var projectKey = "projectKey";
        var componentId = "componentId";
        var requester = "test.user";
        var requesterParam = ProvisioningStatusUpdateRequestParametersInner.builder()
                .name(ProvisionerActionsApiController.DELETION_REQUESTER)
                .values(List.of(requester))
                .build();

        var request = new ProvisioningDeleteRequest()
                .componentId(componentId)
                .parameters(List.of(requesterParam));

        org.mockito.Mockito.doThrow(new JsonProcessingException("Error") {
                })
                .when(provisionerActionsService).deleteComponentProvisioningStatus(projectKey, componentId, requester);

        // when
        var response = provisionerActionsApiController.deleteProvisioningStatus(projectKey, request);

        // then
        org.junit.jupiter.api.Assertions.assertEquals(org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }
}
