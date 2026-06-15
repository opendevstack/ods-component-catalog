package org.opendevstack.component_catalog.config;

import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.CatalogsCollectionCacheProps;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.ProvisionedComponentsCacheProps;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import java.util.HashMap;
import java.util.Map;

import static org.ehcache.config.units.EntryUnit.ENTRIES;
import static org.ehcache.event.EventType.*;

@Configuration
@Slf4j
public class CachingConfiguration implements CacheEventListener<Object, Object> {

    @Bean
    public CacheManager cacheManager(
            CatalogsCollectionCacheProps bitbucketCacheConfig,
            ProvisionedComponentsCacheProps provisionedComponentsCacheConfig)
    {
        if (!bitbucketCacheConfig.isEnabled() && !provisionedComponentsCacheConfig.isEnabled()) {
            log.info("Catalog collections cache and provisioned components cache are both disabled");
            return new NoOpCacheManager();
        }

        var bitbucketServiceEhCacheConfig = buildCatalogCacheConfig(bitbucketCacheConfig.getMaxSize().toMegabytes());
        var provisionedComponentsEhCacheConfig = buildProjectComponentsCacheConfig(provisionedComponentsCacheConfig.getMaxSize().toMegabytes());

        var ehCaches = new HashMap<String, CacheConfiguration<?, ?>>();
        if (bitbucketCacheConfig.isEnabled()) {
            ehCaches.putAll(bitbucketServiceEhCacheConfig);
        } else {
            log.info("Catalog collections cache is disabled");
        }
        if (provisionedComponentsCacheConfig.isEnabled()) {
            ehCaches.putAll(provisionedComponentsEhCacheConfig);
        } else {
            log.info("Provisioned components cache is disabled");
        }

        var ehCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        var ehDefaultConfig = new DefaultConfiguration(ehCaches, ehCachingProvider.getDefaultClassLoader());

        var cacheManager = ehCachingProvider.getCacheManager(ehCachingProvider.getDefaultURI(), ehDefaultConfig);

        return new JCacheCacheManager(cacheManager);
    }

    private Map<String, CacheConfiguration<?, ?>> buildCatalogCacheConfig(long cacheSize) {
        // NOTE: heap tier is used instead of offheap because cached values (Optional, Pair, etc.)
        // are not Serializable, which is required by EHCache's offheap tier.
        // cacheSize is in MB; we convert to an approximate number of entries (assuming ~10KB per entry on average).
        long maxEntries = Math.max(100, cacheSize * 1024 * 1024 / 10_000);
        return ehCachesConfig(maxEntries, CatalogsCollectionCacheProps.CACHE_NAME);
    }

    private Map<String, CacheConfiguration<?, ?>> buildProjectComponentsCacheConfig(long cacheSize) {
        // NOTE: heap tier is used instead of offheap because cached values (Optional, Pair, etc.)
        // are not Serializable, which is required by EHCache's offheap tier.
        // cacheSize is in MB; we convert to an approximate number of entries (assuming ~10KB per entry on average).
        long maxEntries = Math.max(1500, cacheSize * 1024 * 1024 / 10_000);
        return ehCachesConfig(maxEntries, ProvisionedComponentsCacheProps.CACHE_NAME);
    }

    private Map<String, CacheConfiguration<?, ?>> ehCachesConfig(long maxEntries, String cacheName) {
        var ehPoolsBuilder = ResourcePoolsBuilder
                .newResourcePoolsBuilder()
                .heap(maxEntries, ENTRIES);

        var ehEventListenerConfig = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(this, EXPIRED, REMOVED, EVICTED)
                .unordered()
                .asynchronous();

        var ehCacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ehPoolsBuilder)
                .withService(ehEventListenerConfig)
                .build();

        return Map.of(cacheName, ehCacheConfig);
    }

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.debug("Cache event: {} {} {} {} {}", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(),
                cacheEvent.getNewValue(), cacheEvent.getOldValue());
    }
}