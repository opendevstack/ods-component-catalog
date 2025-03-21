package com.boehringer.componentcatalog.server.security;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorizationInfo {

    public String getCurrentName() {
        return currentPrincipal().getName();
    }

    public String getCurrentPrincipalName() {
        return currentPrincipal().getUserPrincipalName();
    }

    public List<String> getCurrentRoles() {
        return getCurrentAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    private List<? extends GrantedAuthority> getCurrentAuthorities() {
        return List.copyOf(currentAuthentication().getAuthorities());
    }

    private UserPrincipal currentPrincipal() {
        return (UserPrincipal) currentAuthentication().getPrincipal();
    }

    private Authentication currentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
