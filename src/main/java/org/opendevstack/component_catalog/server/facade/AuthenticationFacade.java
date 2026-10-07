package org.opendevstack.component_catalog.server.facade;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuthenticationFacade {

    private final List<String> permittedOids;

    public AuthenticationFacade(@Value("${devstack.marketplace-api.permitted-oids}") List<String> permittedOids) {
        this.permittedOids = permittedOids;
    }

    public String getAccessToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !(auth.getPrincipal() instanceof UserPrincipal principal)) {
            throw new ForbiddenException("User not authenticated");
        }

        log.debug("Authenticated user '{}'", auth.getName());

        return principal.getAadIssuedBearerToken();
    }

    public boolean isAValidApplicationToken(String accessToken) {
        var oid = JwtUtils.extractClaim(accessToken, "oid");

        boolean isAValidApplicationToken = oid.map(permittedOids::contains).orElse(false);

        log.debug("Is a valid application token: {} for oid: {}", isAValidApplicationToken, oid.orElse("unknown"));

        return isAValidApplicationToken;
    }
}
