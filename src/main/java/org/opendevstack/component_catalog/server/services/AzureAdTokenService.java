package org.opendevstack.component_catalog.server.services;

import lombok.AllArgsConstructor;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AzureAdTokenService {

    private final RestTemplate restTemplate;
    private final ApplicationPropertiesConfiguration.AzureAdTokenServiceProps azureAdTokenServiceProps;

    public String getAccessToken(String clientId, String clientSecret, String scope) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("scope", scope);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<AzureTokenResponse> response = restTemplate.postForEntity(
                azureAdTokenServiceProps.getUrl(), request, AzureTokenResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()
                || response.getBody() == null
                || response.getBody().getAccessToken() == null
                || response.getBody().getAccessToken().isBlank()) {
            throw new IllegalStateException("Failed to obtain Azure AD access token");
        }

        return response.getBody().getAccessToken();
    }
}