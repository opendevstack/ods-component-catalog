package org.opendevstack.component_catalog.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.BitbucketServiceCacheProps;
import org.opendevstack.component_catalog.server.services.CacheWarmupService;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Scheduling configuration responsible for periodically evicting and re-populating the Bitbucket
 * service cache.
 * <p>
 * This class intentionally lives in the infrastructure layer (config package) and depends on
 * {@link CacheWarmupService} (business layer) only through this orchestration entry point.
 * Keeping this concern separate from {@link CachingConfiguration} avoids mixing cache
 * infrastructure setup with eviction scheduling, and removes the circular dependency that would
 * arise if {@link CachingConfiguration} — which defines the {@link CacheManager} bean — also
 * depended on services that consume it.
 * </p>
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class CacheScheduling {

    private final CacheManager cacheManager;
    private final CacheWarmupService cacheWarmupService;

    /**
     * Evicts all entries from the Bitbucket service cache and immediately re-populates it so the
     * cache always appears warm to end users.
     * <p>
     * The eviction is done programmatically <em>before</em> calling warmup, because using
     * {@code @CacheEvict} on this method would apply the eviction <em>after</em> the method body
     * returns — wiping the freshly populated cache.
     * </p>
     * <p>
     * {@code initialDelay} is set equal to {@code fixedDelay} so the first scheduled execution
     * does not fire at application startup (time 0), avoiding a race with the startup warmup
     * performed by {@link CacheWarmupService} as an {@link org.springframework.boot.ApplicationRunner}.
     * </p>
     */
    @Scheduled(
            initialDelayString = "#{bitbucketServiceCacheConfig.getEvictionInterval().toMillis()}",
            fixedDelayString   = "#{bitbucketServiceCacheConfig.getEvictionInterval().toMillis()}"
    )
    public void scheduledCacheEvictionAndWarmup() {
        var cache = cacheManager.getCache(BitbucketServiceCacheProps.CACHE_NAME);
        if (cache != null) {
            cache.clear();
            log.debug("Bitbucket Service cache evicted.");
        }
        cacheWarmupService.warmup();
    }
}

