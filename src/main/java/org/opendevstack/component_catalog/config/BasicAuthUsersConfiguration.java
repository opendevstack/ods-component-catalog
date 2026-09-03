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
            ApplicationPropertiesConfiguration.BasicAuthSecurityProps basicAuthSecurityProps
    ) {

        UserDetails provisionerUser = User
                .withUsername(basicAuthSecurityProps.getProvisioner().getUsername())
                .password("{noop}" + basicAuthSecurityProps.getProvisioner().getPassword())
                .roles(basicAuthSecurityProps.getProvisioner().getRoles().toArray(String[]::new))
                .build();

        UserDetails cacheAdminUser = User
                .withUsername(basicAuthSecurityProps.getCacheAdmin().getUsername())
                .password("{noop}" + basicAuthSecurityProps.getCacheAdmin().getPassword())
                .roles(basicAuthSecurityProps.getCacheAdmin().getRoles().toArray(String[]::new))
                .build();

        return new InMemoryUserDetailsManager(
                provisionerUser,
                cacheAdminUser
        );
    }
}