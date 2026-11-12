package org.opendevstack.component_catalog.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AzureAdTokenServiceTest {

    private static final String TOKEN_URL = "https://login.example.com/token";

    @Mock
    private RestTemplate restTemplate;

    @Test
    void givenSuccessfulTokenResponse_whenGetAccessToken_thenReturnAccessToken() throws Exception {
        var tokenResponse = tokenResponse("{\"access_token\":\"access-token\"}");
        mockTokenResponse(ResponseEntity.ok(tokenResponse));
        var service = service();

        var accessToken = service.getAccessToken("client-id", "client-secret", "api-scope");

        assertThat(accessToken).isEqualTo("access-token");
        verify(restTemplate).postForEntity(eq(TOKEN_URL), any(HttpEntity.class), eq(AzureTokenResponse.class));
    }

    @Test
    void givenNon2xxResponse_whenGetAccessToken_thenThrow() throws Exception {
        mockTokenResponse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(tokenResponse("{\"access_token\":\"access-token\"}")));

        assertTokenRequestFails();
    }

    @Test
    void givenResponseWithoutBody_whenGetAccessToken_thenThrow() {
        mockTokenResponse(ResponseEntity.ok().build());

        assertTokenRequestFails();
    }

    @Test
    void givenResponseWithoutAccessToken_whenGetAccessToken_thenThrow() throws Exception {
        mockTokenResponse(ResponseEntity.ok(tokenResponse("{\"access_token\":null}")));

        assertTokenRequestFails();
    }

    @Test
    void givenBlankAccessToken_whenGetAccessToken_thenThrow() throws Exception {
        mockTokenResponse(ResponseEntity.ok(tokenResponse("{\"access_token\":\"  \"}")));

        assertTokenRequestFails();
    }

    private AzureAdTokenService service() {
        var properties = ApplicationPropertiesConfiguration.AzureAdTokenServiceProps.builder()
            .url(TOKEN_URL)
            .build();
        return new AzureAdTokenService(restTemplate, properties);
    }

    private AzureTokenResponse tokenResponse(String json) throws Exception {
        return new ObjectMapper().readValue(json, AzureTokenResponse.class);
    }

    private void mockTokenResponse(ResponseEntity<AzureTokenResponse> response) {
        when(restTemplate.postForEntity(eq(TOKEN_URL), any(HttpEntity.class), eq(AzureTokenResponse.class)))
            .thenReturn(response);
    }

    private void assertTokenRequestFails() {
        assertThatThrownBy(() -> service().getAccessToken("client-id", "client-secret", "api-scope"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Failed to obtain Azure AD access token");
    }
}