package org.opendevstack.component_catalog.server.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApplicationAuthenticationProvider {

    private final AzureAdTokenService azureAdTokenService;
    private final String clientId;
    private final String clientSecret;
    private final String scope;

    public ApplicationAuthenticationProvider(
            AzureAdTokenService azureAdTokenService,
            @Value("${component-catalog.marketplace-api.client-id}") String clientId,
            @Value("${component-catalog.marketplace-api.client-secret}") String clientSecret,
            @Value("${component-catalog.ods-api-service.params.scope}") String scope) {
        this.azureAdTokenService = azureAdTokenService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
    }

    public String getAccessToken() {
        return azureAdTokenService.getAccessToken(clientId, clientSecret, scope);
    }
}