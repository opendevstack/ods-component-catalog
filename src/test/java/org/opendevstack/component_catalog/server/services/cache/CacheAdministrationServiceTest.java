package org.opendevstack.component_catalog.server.services.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.BitbucketServiceCacheProps;
import org.opendevstack.component_catalog.server.controllers.exceptions.CacheNotFoundException;
import org.opendevstack.component_catalog.server.controllers.exceptions.CacheRefreshNotSupportedException;
import org.opendevstack.component_catalog.server.services.CacheWarmupService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CacheAdministrationServiceTest {

    @Mock
    private CacheManager cacheManager;

    @Mock
    private CacheWarmupService cacheWarmupService;

    @Mock
    private Cache cache;

    @InjectMocks
    private CacheAdministrationService service;

    @Test
    void givenCacheDoesNotExist_whenRefreshCache_thenThrowCacheNotFoundException() {
        // Given
        String cacheName = "unknownCache";

        when(cacheManager.getCache(cacheName))
                .thenReturn(null);

        // When / Then
        assertThatThrownBy(() -> service.refreshCache(cacheName))
                .isInstanceOf(CacheNotFoundException.class);

        verify(cacheManager).getCache(cacheName);
        verifyNoInteractions(cacheWarmupService);
    }

    @Test
    void givenNonRefreshableCache_whenRefreshCache_thenThrowCacheRefreshNotSupportedException() {
        // Given
        String cacheName = "nonRefreshableCache";

        when(cacheManager.getCache(cacheName))
                .thenReturn(cache);

        // When / Then
        assertThatThrownBy(() -> service.refreshCache(cacheName))
                .isInstanceOf(CacheRefreshNotSupportedException.class);

        verify(cacheManager).getCache(cacheName);
        verify(cache, never()).clear();
        verifyNoInteractions(cacheWarmupService);
    }

    @Test
    void givenRefreshableBitbucketCache_whenRefreshCache_thenClearCacheAndTriggerWarmup() {
        // Given
        String cacheName = BitbucketServiceCacheProps.CACHE_NAME;

        when(cacheManager.getCache(cacheName))
                .thenReturn(cache);

        // When
        service.refreshCache(cacheName);

        // Then
        verify(cacheManager).getCache(cacheName);
        verify(cache).clear();
        verify(cacheWarmupService)
                .warmupCatalogsBitbucketServiceCacheAsync();
    }
}