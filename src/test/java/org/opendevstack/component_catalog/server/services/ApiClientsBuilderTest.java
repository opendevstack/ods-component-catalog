package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.ApiClient;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.AzureGroupsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.api.ProjectsApi;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.auth.HttpBearerAuth;

import static org.assertj.core.api.Assertions.assertThat;

class ApiClientsBuilderTest {

    private final ApiClientsBuilder builder = new ApiClientsBuilder();

    @Test
    void givenIdTokenAndBaseUrl_whenApiClient_thenClientConfiguredCorrectly() {
        // given
        String idToken = "test-token";
        String baseUrl = "http://example.com";

        // when
        ApiClient client = builder.apiClient(idToken, baseUrl);

        // then
        assertThat(client).isNotNull();
        assertThat(client.getBasePath()).isEqualTo(baseUrl);

        var auth = (HttpBearerAuth) client.getAuthentication("bearerAuth");
        assertThat(auth.getBearerToken()).isEqualTo(idToken);
    }

    @Test
    void givenApiClient_whenProjectsApi_thenReturnProjectsApiInstance() {
        // given
        ApiClient client = new ApiClient();

        // when
        ProjectsApi api = builder.projectsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }

    @Test
    void givenApiClient_whenAzureGroupsApi_thenReturnAzureGroupsApiInstance() {
        // given
        ApiClient client = new ApiClient();

        // when
        AzureGroupsApi api = builder.azureGroupsApi(client);

        // then
        assertThat(api).isNotNull();
        assertThat(api.getApiClient()).isSameAs(client);
    }
}
