package org.opendevstack.component_catalog.server.services;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AzureTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}