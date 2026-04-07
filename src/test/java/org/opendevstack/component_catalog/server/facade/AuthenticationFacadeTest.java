package org.opendevstack.component_catalog.server.facade;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationFacadeTest {

    private final AuthenticationFacade authenticationFacade = new AuthenticationFacade();

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void getIdToken_whenAuthIsNull_throwsForbiddenException() {
        // given
        var securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        // when / then
        assertThatThrownBy(authenticationFacade::getIdToken)
                .isInstanceOf(ForbiddenException.class)
                .hasMessage("User not authenticated");
    }

    @Test
    void getIdToken_whenPrincipalIsNotUserPrincipal_throwsForbiddenException() {
        // given
        var authentication = mock(Authentication.class);
        var securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("not-a-user-principal");
        SecurityContextHolder.setContext(securityContext);

        // when / then
        assertThatThrownBy(authenticationFacade::getIdToken)
                .isInstanceOf(ForbiddenException.class)
                .hasMessage("User not authenticated");
    }

    @Test
    void getIdToken_whenAuthenticated_returnsToken() {
        // given
        var idToken = "token";
        var authentication = mock(Authentication.class);
        var securityContext = mock(SecurityContext.class);
        var principal = mock(UserPrincipal.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(principal);
        when(principal.getAadIssuedBearerToken()).thenReturn(idToken);
        when(authentication.getName()).thenReturn("userName");

        SecurityContextHolder.setContext(securityContext);

        // when
        var result = authenticationFacade.getIdToken();

        // then
        assertThat(result).isEqualTo(idToken);
    }
}
