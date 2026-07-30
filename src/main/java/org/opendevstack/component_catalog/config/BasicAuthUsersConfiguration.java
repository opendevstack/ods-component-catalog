package org.opendevstack.component_catalog.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class BasicAuthUsersConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(
            @Qualifier("basicAuthUsersConfig")
            ApplicationPropertiesConfiguration.SecurityProps securityProps
    ) {

        UserDetails provisionerUser = User
                .withUsername(securityProps.getProvisioner().getUsername())
                .password("{noop}" + securityProps.getProvisioner().getPassword())
                .roles(securityProps.getProvisioner().getRoles().toArray(String[]::new))
                .build();

        UserDetails cacheAdminUser = User
                .withUsername(securityProps.getCacheAdmin().getUsername())
                .password("{noop}" + securityProps.getCacheAdmin().getPassword())
                .roles(securityProps.getCacheAdmin().getRoles().toArray(String[]::new))
                .build();

        return new InMemoryUserDetailsManager(
                provisionerUser,
                cacheAdminUser
        );
    }
}