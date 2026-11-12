package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationAuthenticationProviderTest {

    @Mock
    private AzureAdTokenService azureAdTokenService;

    @Test
    void givenComponentCatalogCredentialsAndOdsScope_whenGetAccessToken_thenRequestMarketplaceToken() {
        // given
        var clientId = "component-catalog-client-id";
        var clientSecret = "component-catalog-client-secret";
        var odsScope = "api://ods-api/.default";
        when(azureAdTokenService.getAccessToken(clientId, clientSecret, odsScope))
                .thenReturn("component-catalog-access-token");
        var authenticationProvider = new ApplicationAuthenticationProvider(
                azureAdTokenService, clientId, clientSecret, odsScope);

        // when
        var accessToken = authenticationProvider.getAccessToken();

        // then
        assertThat(accessToken).isEqualTo("component-catalog-access-token");
        verify(azureAdTokenService).getAccessToken(clientId, clientSecret, odsScope);
    }
}