package org.opendevstack.component_catalog.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.facade.AuthenticationFacade;
import org.opendevstack.component_catalog.server.facade.ProjectComponentsFacade;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentsMetrics;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectComponentsControllerTest {

    private final String projectKey = "PRJ-123";
    private final String accessToken = "token";

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private ProjectComponentsFacade projectComponentsFacade;

    @InjectMocks
    private ProjectComponentsController projectComponentsController;

    @Test
    void givenValidProjectKey_whenGetProjectComponents_thenReturnOkWithItems() {
        // given
        var pci1 = ProjectComponentInfo.builder().componentId("C1").componentUrl("url1").status(ProvisioningStatus.CREATED).logoUrl("logo1").build();
        var pci2 = ProjectComponentInfo.builder().componentId("C2").componentUrl("url2").status(ProvisioningStatus.CREATING).logoUrl("logo2").build();

        List<ProjectComponentInfo> components = List.of(pci1, pci2);

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken)).thenReturn(components);

        // when
        var response = projectComponentsController.getProjectComponents(projectKey);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getComponentId()).isEqualTo("C1");
        assertThat(response.getBody().get(1).getComponentId()).isEqualTo("C2");

        verify(projectComponentsFacade, times(1)).getProjectComponentsInfo(projectKey, accessToken);
    }

    @Test
    void givenValidProjectKey_whenFacadeReturnsEmptyList_thenReturnOkWithEmptyBody() {
        // given
        when(projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken)).thenReturn(List.of());

        // when
        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        var response = projectComponentsController.getProjectComponents(projectKey);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEmpty();

        verify(projectComponentsFacade, times(1)).getProjectComponentsInfo(projectKey, accessToken);
    }

    @Test
    void givenFacadeThrowsRuntimeException_whenGetProjectComponents_thenPropagateException() {
        // given
        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken)).thenThrow(new RuntimeException("Unexpected error"));

        // when / then
        assertThatThrownBy(() -> projectComponentsController.getProjectComponents(projectKey)).isInstanceOf(RuntimeException.class).hasMessageContaining("Unexpected error");

        verify(projectComponentsFacade, times(1)).getProjectComponentsInfo(projectKey, accessToken);
    }

    @Test
    void givenValidProjectAndComponentId_whenGetProjectComponentById_thenReturnOkWithBody() {
        // given
        var componentId = "C1";
        var extendedInfo = ProjectComponentExtendedInfo.builder()
                .componentId(componentId)
                .build();

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, componentId, accessToken))
                .thenReturn(extendedInfo);

        // when
        var response = projectComponentsController.getProjectComponentById(projectKey, componentId);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getComponentId()).isEqualTo(componentId);

        verify(authenticationFacade, times(1)).getAccessToken();
        verify(projectComponentsFacade, times(1))
                .getProjectComponentExtendedInfo(projectKey, componentId, accessToken);
    }

    @Test
    void givenComponentDoesNotExist_whenGetProjectComponentById_thenPropagateComponentNotFound() {
        // given
        var componentId = "C404";

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, componentId, accessToken))
                .thenThrow(new ComponentNotFoundException("Not found"));

        // when / then
        assertThatThrownBy(() ->
                projectComponentsController.getProjectComponentById(projectKey, componentId)
        ).isInstanceOf(ComponentNotFoundException.class)
                .hasMessageContaining("Not found");

        verify(projectComponentsFacade, times(1))
                .getProjectComponentExtendedInfo(projectKey, componentId, accessToken);
    }

    @Test
    void givenInvalidArguments_whenGetProjectComponentById_thenPropagateIllegalArgumentException() {
        // given
        var componentId = "C1";

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, componentId, accessToken))
                .thenThrow(new IllegalArgumentException("Invalid arguments"));

        // when / then
        assertThatThrownBy(() ->
                projectComponentsController.getProjectComponentById(projectKey, componentId)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid arguments");
    }

    @Test
    void givenValidRequest_whenGetAllProjectComponents_thenReturnOkWithResponse() {
        // given
        Integer page = 0;
        Integer size = 20;

        var responseBody = mock(ProjectComponentsMetrics.class);

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/project/components");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(projectComponentsFacade.getAllProjectComponentsMetrics(
                eq(accessToken),
                eq(page),
                eq(size),
                anyString()
        )).thenReturn(responseBody);

        // when
        var response = projectComponentsController.getAllProjectComponents(page, size);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseBody);

        verify(authenticationFacade).getAccessToken();
        verify(projectComponentsFacade).getAllProjectComponentsMetrics(
                eq(accessToken),
                eq(page),
                eq(size),
                anyString()
        );
    }

    @Test
    void givenFacadeThrowsException_whenGetAllProjectComponents_thenPropagateException() {
        // given
        Integer page = 0;
        Integer size = 20;

        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/project/components");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(projectComponentsFacade.getAllProjectComponentsMetrics(
                eq(accessToken),
                eq(page),
                eq(size),
                anyString()
        )).thenThrow(new RuntimeException("Error"));

        // when / then
        assertThatThrownBy(() ->
                projectComponentsController.getAllProjectComponents(page, size)
        ).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Error");
    }

}
