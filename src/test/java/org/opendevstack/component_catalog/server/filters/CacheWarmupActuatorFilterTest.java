package org.opendevstack.component_catalog.server.filters;

import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.services.CacheWarmupService;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.mockito.Mockito.*;

class CacheWarmupActuatorFilterTest {

    private CacheWarmupService cacheWarmupService;
    private FilterChain filterChain;
    private CacheWarmupActuatorFilter filter;

    @BeforeEach
    void setUp() {
        cacheWarmupService = mock(CacheWarmupService.class);
        filterChain = mock(FilterChain.class);

        filter = new CacheWarmupActuatorFilter(cacheWarmupService);
    }

    @Test
    void givenCatalogCacheDeletedSuccessfully_thenWarmupIsTriggered() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.DELETE.name());
        request.setRequestURI(
                "/actuator/caches/"
                        + ApplicationPropertiesConfiguration.BitbucketServiceCacheProps.CACHE_NAME);

        MockHttpServletResponse response = new MockHttpServletResponse();

        doAnswer(invocation -> {
            ((MockHttpServletResponse) invocation.getArgument(1)).setStatus(204);
            return null;
        }).when(filterChain).doFilter(any(), any());

        filter.doFilter(request, response, filterChain);

        verify(cacheWarmupService)
                .warmupCatalogsBitbucketServiceCacheAsync();
    }

    @Test
    void givenUnknownCacheDeletedSuccessfully_thenWarmupIsNotTriggered() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.DELETE.name());
        request.setRequestURI("/actuator/caches/someOtherCache");

        MockHttpServletResponse response = new MockHttpServletResponse();

        doAnswer(invocation -> {
            ((MockHttpServletResponse) invocation.getArgument(1)).setStatus(204);
            return null;
        }).when(filterChain).doFilter(any(), any());

        filter.doFilter(request, response, filterChain);

        verifyNoInteractions(cacheWarmupService);
    }

    @Test
    void givenDeleteFails_thenWarmupIsNotTriggered() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.DELETE.name());
        request.setRequestURI(
                "/actuator/caches/"
                        + ApplicationPropertiesConfiguration.BitbucketServiceCacheProps.CACHE_NAME);

        MockHttpServletResponse response = new MockHttpServletResponse();

        doAnswer(invocation -> {
            ((MockHttpServletResponse) invocation.getArgument(1)).setStatus(500);
            return null;
        }).when(filterChain).doFilter(any(), any());

        filter.doFilter(request, response, filterChain);

        verifyNoInteractions(cacheWarmupService);
    }

    @Test
    void givenNonDeleteRequest_thenWarmupIsNotTriggered() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.GET.name());
        request.setRequestURI(
                "/actuator/caches/"
                        + ApplicationPropertiesConfiguration.BitbucketServiceCacheProps.CACHE_NAME);

        MockHttpServletResponse response = new MockHttpServletResponse();

        doAnswer(invocation -> {
            ((MockHttpServletResponse) invocation.getArgument(1)).setStatus(200);
            return null;
        }).when(filterChain).doFilter(any(), any());

        filter.doFilter(request, response, filterChain);

        verifyNoInteractions(cacheWarmupService);
    }

    @Test
    void givenValidRequest_thenDelegatesToFilterChain() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod(HttpMethod.DELETE.name());
        request.setRequestURI(
                "/actuator/caches/"
                        + ApplicationPropertiesConfiguration.BitbucketServiceCacheProps.CACHE_NAME);

        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

}