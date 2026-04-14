package org.opendevstack.component_catalog.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
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

    private ApiClientsBuilder apiClientsBuilder;

    @Cacheable
    public ProjectInfo getProjectClusters(String projectKey, String accessToken) {
        var apiClient = apiClientsBuilder.apiClient(accessToken, projectsInfoServiceProps.getBaseRestUrl().toString());
        var projectsApi = apiClientsBuilder.projectsApi(apiClient);

        return projectsApi.getProjectClusters(accessToken, projectKey);
    }

    @Cacheable
    public List<String> getProjectGroups(String accessToken) {
        var apiClient = apiClientsBuilder.apiClient(accessToken, projectsInfoServiceProps.getBaseRestUrl().toString());
        var azureGroupsApi = apiClientsBuilder.azureGroupsApi(apiClient);

        return azureGroupsApi.getAzureGroups(accessToken);
    }

}
