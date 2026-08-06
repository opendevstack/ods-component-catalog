package org.opendevstack.component_catalog.server.services.cache;

import lombok.RequiredArgsConstructor;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.*;
import org.opendevstack.component_catalog.server.controllers.exceptions.CacheNotFoundException;
import org.opendevstack.component_catalog.server.controllers.exceptions.CacheRefreshNotSupportedException;
import org.opendevstack.component_catalog.server.services.CacheWarmupService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CacheAdministrationService {

    private final CacheManager cacheManager;
    private final CacheWarmupService cacheWarmupService;

    private static final Set<String> REFRESHABLE_CACHES = Set.of(
            BitbucketServiceCacheProps.CACHE_NAME
    );

    public void refreshCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);

        if (cache == null) {
            throw new CacheNotFoundException("Cache '" + cacheName + "' was not found.");
        }
        if (!REFRESHABLE_CACHES.contains(cacheName)) {
            throw new CacheRefreshNotSupportedException("Cache '" + cacheName + "' does not support refresh operation.");
        }

        cache.clear();
        if (BitbucketServiceCacheProps.CACHE_NAME.equals(cacheName)) {
            cacheWarmupService.warmupCatalogsBitbucketServiceCacheAsync();
        }
    }

}
