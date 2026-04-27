package org.opendevstack.component_catalog.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.BitbucketServiceCacheProps;
import org.opendevstack.component_catalog.server.services.CacheWarmupService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.mockito.Mockito.*;

class CacheSchedulingTest {

    private CacheManager cacheManager;
    private CacheWarmupService cacheWarmupService;
    private CacheScheduling cacheScheduling;

    @BeforeEach
    void setUp() {
        cacheManager = mock(CacheManager.class);
        cacheWarmupService = mock(CacheWarmupService.class);
        cacheScheduling = new CacheScheduling(cacheManager, cacheWarmupService);
    }

    @Test
    void whenScheduledCacheEvictionAndWarmup_thenCacheClearedBeforeWarmup() {
        Cache cache = mock(Cache.class);
        when(cacheManager.getCache(BitbucketServiceCacheProps.CACHE_NAME)).thenReturn(cache);

        cacheScheduling.scheduledCacheEvictionAndWarmup();

        var inOrder = inOrder(cache, cacheWarmupService);
        inOrder.verify(cache).clear();
        inOrder.verify(cacheWarmupService).warmup();
    }

    @Test
    void whenScheduledCacheEvictionAndWarmup_andCacheNotFound_thenWarmupStillCalled() {
        when(cacheManager.getCache(BitbucketServiceCacheProps.CACHE_NAME)).thenReturn(null);

        cacheScheduling.scheduledCacheEvictionAndWarmup();

        verify(cacheWarmupService).warmup();
    }
}

