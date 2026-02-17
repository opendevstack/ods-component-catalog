package org.opendevstack.component_catalog.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Configuration
public class ApplicationPropertiesConfiguration {

    @Bean("bitbucketServiceConfig")
    @ConfigurationProperties(prefix = "component-catalog.bitbucket.service")
    public BitbucketServiceProps bitbucketServiceProps() {
        return BitbucketServiceProps.builder().build();
    }

    @Bean("bitbucketServiceCacheConfig")
    @ConfigurationProperties(prefix = "component-catalog.caching.bitbucket-service-cache")
    public BitbucketServiceCacheProps bitbucketServiceCacheProps() {
        return BitbucketServiceCacheProps.builder().build();
    }

    @Bean("projectsInfoServiceConfig")
    @ConfigurationProperties(prefix = "component-catalog.projects-info-service.service")
    public ExternalServiceProps projectsInfoServiceServiceProps() {
        return ExternalServiceProps.builder().build();
    }

    @Bean("catalogItemGroupsRestrictionConfig")
    @ConfigurationProperties(prefix = "catalog.user-action.groups-restriction")
    public CatalogItemUserActionGroupsRestrictionProps catalogItemGroupsRestrictionConfig() {
        return CatalogItemUserActionGroupsRestrictionProps.builder().build();
    }

    @Bean("catalogItemDefaultConfig")
    @ConfigurationProperties(prefix = "catalog-item")
    public CatalogItemDefaultProps  catalogItemDefaultConfig() {
        return CatalogItemDefaultProps.builder().build();
    }

    @Builder // useful for unit testing
    @Data
    public static class ExternalServiceProps {
        private URL baseRestUrl;
    }

    @Builder // useful for unit testing
    @Data
    public static class BitbucketServiceProps {
        private String bearerToken;
        private URL baseRestUrl;
        private URL baseRawUrl;
    }

    @Builder // useful for unit testing
    @Data
    public static class BitbucketServiceCacheProps {
        public static final String CACHE_NAME = "bitbucket-service-cache";

        @Builder.Default
        private boolean enabled = true;
        private DataSize maxSize;
        @DurationUnit(ChronoUnit.MINUTES) // default units, e.g. 5 -> 5m (minutes)
        private Duration evictionInterval;
    }

    @Builder
    @Data
    public static class CatalogItemUserActionGroupsRestrictionProps {
        private List<String> prefix;
        private List<String> suffix;
    }

    @Builder
    @Data
    public static class CatalogItemDefaultProps {
        private Reference reference;

        @Builder
        @Data
        public static class Reference {
            private String encoded;

        }
    }
}
