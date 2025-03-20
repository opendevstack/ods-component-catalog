package com.boehringer.componentcatalog.config;

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
}
