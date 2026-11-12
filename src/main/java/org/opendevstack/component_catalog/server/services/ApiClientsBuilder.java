package org.opendevstack.component_catalog.server.services;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth;
import org.opendevstack.component_catalog.client.feedback_api.v0_0_1.api.FeedbackSubmissionsApi;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiClientsBuilder {
    private final RestTemplate restTemplate;

    public ApiClient apiClient(String accessToken, String baseRestUrl) {
        var apiClient = new ApiClient();

        apiClient.setBasePath(baseRestUrl);

        var auth = (HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(accessToken);

        return apiClient;
    }

    public ProjectsApi projectsApi(ApiClient apiClient) {
        return new ProjectsApi(apiClient);
    }

    public AzureGroupsApi azureGroupsApi(ApiClient apiClient) {
        return new AzureGroupsApi(apiClient);
    }

    public FeedbackSubmissionsApi feedbackSubmissionsApi(String accessToken, String baseRestUrl) {
        var apiClient = new org.opendevstack.component_catalog.client.feedback_api.v0_0_1.ApiClient(restTemplate);
        var baseUrl = baseRestUrl.endsWith("/")
            ? baseRestUrl.substring(0, baseRestUrl.length() - 1)
            : baseRestUrl;
        apiClient.setBasePath(baseUrl + "/api/v1/feedback");
        apiClient.addDefaultHeader("Authorization", "Bearer " + accessToken);

        return new FeedbackSubmissionsApi(apiClient);
    }
}