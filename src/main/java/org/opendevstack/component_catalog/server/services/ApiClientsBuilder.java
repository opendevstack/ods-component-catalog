package org.opendevstack.component_catalog.server.services;

import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApiClientsBuilder {
    public ApiClient apiClient(String idToken, String baseRestUrl) {
        var apiClient = new ApiClient();

        apiClient.setBasePath(baseRestUrl);

        var auth = (HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return apiClient;
    }

    public ProjectsApi projectsApi(ApiClient apiClient) {
        return new ProjectsApi(apiClient);
    }

    public AzureGroupsApi azureGroupsApi(ApiClient apiClient) {
        return new AzureGroupsApi(apiClient);
    }
}