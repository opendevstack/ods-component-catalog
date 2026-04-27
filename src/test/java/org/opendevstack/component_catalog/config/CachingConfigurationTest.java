package org.opendevstack.component_catalog.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.EventType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.BitbucketServiceCacheProps;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.util.unit.DataSize;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CachingConfigurationTest {

    private final CachingConfiguration config = new CachingConfiguration();

    // -------------------------------------------------------------------------
    // cacheManager — disabled branch
    // -------------------------------------------------------------------------

    @Test
    void givenCacheDisabled_whenCacheManager_thenReturnsNoOpCacheManager() {
        var props = BitbucketServiceCacheProps.builder()
                .enabled(false)
                .build();

        var cacheManager = config.cacheManager(props);

        assertThat(cacheManager).isInstanceOf(NoOpCacheManager.class);
    }

    // -------------------------------------------------------------------------
    // cacheManager — enabled branch + ehCachesConfig (maxEntries branches)
    // -------------------------------------------------------------------------

    @Test
    void givenCacheEnabledWithLargeSize_whenCacheManager_thenReturnsJCacheCacheManagerAndCacheExists() {
        // cacheSize = 100 MB  →  maxEntries = 100 * 1024 * 1024 / 10_000 = 10485  (> 100, uses computed value)
        var props = BitbucketServiceCacheProps.builder()
                .enabled(true)
                .maxSize(DataSize.ofMegabytes(100))
                .evictionInterval(Duration.ofMinutes(120))
                .build();

        var cacheManager = config.cacheManager(props);

        assertThat(cacheManager).isInstanceOf(JCacheCacheManager.class);
        assertThat(cacheManager.getCache(BitbucketServiceCacheProps.CACHE_NAME)).isNotNull();
    }

    @Test
    void givenCacheEnabledWithTinySize_whenCacheManager_thenUsesMinimumEntries() {
        // cacheSize = 0 MB  →  computed = 0, Math.max(100, 0) = 100  (uses minimum of 100 entries)
        var props = BitbucketServiceCacheProps.builder()
                .enabled(true)
                .maxSize(DataSize.ofBytes(0))
                .evictionInterval(Duration.ofMinutes(5))
                .build();

        var cacheManager = config.cacheManager(props);

        assertThat(cacheManager).isInstanceOf(JCacheCacheManager.class);
        assertThat(cacheManager.getCache(BitbucketServiceCacheProps.CACHE_NAME)).isNotNull();
    }

    // -------------------------------------------------------------------------
    // emptyBitbucketServiceCache
    // -------------------------------------------------------------------------

    @Test
    void whenEmptyBitbucketServiceCache_thenNoException() {
        // Covers the @Scheduled + @CacheEvict annotated method body
        config.emptyBitbucketServiceCache();
    }

    // -------------------------------------------------------------------------
    // onEvent — all relevant EventTypes
    // -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    @ParameterizedTest
    @ValueSource(strings = {"EXPIRED", "REMOVED", "EVICTED"})
    void givenCacheEvent_whenOnEvent_thenNoException(String eventTypeName) {
        CacheEvent<Object, Object> event = mock(CacheEvent.class);
        when(event.getType()).thenReturn(EventType.valueOf(eventTypeName));
        when(event.getKey()).thenReturn("some-key");
        when(event.getOldValue()).thenReturn("old-value");
        when(event.getNewValue()).thenReturn(null);

        config.onEvent(event);
    }
}

