package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames={ApplicationPropertiesConfiguration.BitbucketServiceCacheProps.CACHE_NAME})
@AllArgsConstructor
@Slf4j
public class ProjectsInfoService {

    @Qualifier("projectsInfoServiceConfig")
    private final ApplicationPropertiesConfiguration.ExternalServiceProps projectsInfoServiceProps;

    private final ApiClient apiClient;

    private final ProjectsApi projectsApi;

    private final AzureGroupsApi azureGroupsApi;

    @PostConstruct
    public void configureApiClient() {
        this.apiClient.setBasePath(projectsInfoServiceProps.getBaseRestUrl().toString());
    }

    @Cacheable
    public ProjectInfo getProjectClusters(String projectKey, String idToken, String accessToken) {
        var auth = (HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return projectsApi.getProjectClusters(accessToken, projectKey);
    }

    @Cacheable
    public List<String> getProjectGroups(String idToken, String accessToken) {
        var auth = (HttpBearerAuth) apiClient.getAuthentication("bearerAuth");
        auth.setBearerToken(idToken);

        return azureGroupsApi.getAzureGroups(accessToken);
    }
}
