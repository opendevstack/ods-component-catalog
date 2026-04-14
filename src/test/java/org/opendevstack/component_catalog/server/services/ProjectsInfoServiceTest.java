package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProjectsInfoServiceTest {

    private ProjectsInfoService service;

    private ApiClientsBuilder apiClientsBuilder;

    private ProjectsApi projectsApi;
    private AzureGroupsApi azureGroupsApi;

    @BeforeEach
    void setUp() throws MalformedURLException {
        ApplicationPropertiesConfiguration.ExternalServiceProps props = mock(ApplicationPropertiesConfiguration.ExternalServiceProps.class);
        apiClientsBuilder = mock(ApiClientsBuilder.class);
        projectsApi = mock(ProjectsApi.class);
        azureGroupsApi = mock(AzureGroupsApi.class);

        when(props.getBaseRestUrl()).thenReturn(URI.create("http://example.com").toURL());

        service = new ProjectsInfoService(props, apiClientsBuilder);
    }

    @Test
    void givenValidParams_whenGetProjectClusters_thenReturnProjectInfo() {
        // given
        String projectKey = "PROJ";
        String accessToken = "access-token";

        ApiClient apiClient = mock(ApiClient.class);
        ProjectInfo projectInfo = new ProjectInfo();

        when(apiClientsBuilder.apiClient(accessToken, "http://example.com")).thenReturn(apiClient);
        when(apiClientsBuilder.projectsApi(apiClient)).thenReturn(projectsApi);
        when(projectsApi.getProjectClusters(accessToken, projectKey)).thenReturn(projectInfo);

        // when
        var result = service.getProjectClusters(projectKey, accessToken);

        // then
        assertThat(result).isSameAs(projectInfo);
        verify(apiClientsBuilder).apiClient(accessToken, "http://example.com");
        verify(projectsApi).getProjectClusters(accessToken, projectKey);
    }

    @Test
    void givenValidParams_whenGetProjectGroups_thenReturnGroupList() {
        // given
        String accessToken = "access-token";

        ApiClient apiClient = mock(ApiClient.class);
        List<String> groups = List.of("group1", "group2");

        when(apiClientsBuilder.apiClient(accessToken, "http://example.com")).thenReturn(apiClient);
        when(apiClientsBuilder.azureGroupsApi(apiClient)).thenReturn(azureGroupsApi);
        when(azureGroupsApi.getAzureGroups(accessToken)).thenReturn(groups);

        // when
        var result = service.getProjectGroups(accessToken);

        // then
        assertThat(result).containsExactly("group1", "group2");
        verify(apiClientsBuilder).apiClient(accessToken, "http://example.com");
        verify(azureGroupsApi).getAzureGroups(accessToken);
    }
}
