package org.opendevstack.component_catalog.server.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.services.CacheWarmupService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheWarmupActuatorFilter extends OncePerRequestFilter {

    private static final String ACTUATOR_CACHES_PREFIX = "/actuator/caches/";
    private static final String CATALOGS_CACHE_NAME = ApplicationPropertiesConfiguration.BitbucketServiceCacheProps.CACHE_NAME;

    private final CacheWarmupService cacheWarmupService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        filterChain.doFilter(request, response);

        if (!HttpMethod.DELETE.matches(request.getMethod())) {
            return;
        }

        if (response.getStatus() != HttpServletResponse.SC_NO_CONTENT
                && response.getStatus() != HttpServletResponse.SC_OK) {
            return;
        }

        String uri = request.getRequestURI();

        if (!uri.startsWith(ACTUATOR_CACHES_PREFIX)) {
            return;
        }

        String cacheName = uri.substring(ACTUATOR_CACHES_PREFIX.length());

        if (cacheName.equals(CATALOGS_CACHE_NAME)) {
            log.info(
                    "Actuator cache '{}' cleared successfully. Triggering catalog warmup.",
                    cacheName);

            cacheWarmupService.warmupCatalogsBitbucketServiceCacheAsync();
        } else {
            log.debug(
                    "Actuator cache '{}' cleared. No warmup configured.",
                    cacheName);
        }
    }
}