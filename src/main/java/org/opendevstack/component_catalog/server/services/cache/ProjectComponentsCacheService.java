package org.opendevstack.component_catalog.server.services.cache;

import lombok.RequiredArgsConstructor;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.ProvisionedComponentsCacheProps;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectComponentsCacheService {

    @CacheEvict(cacheNames = ProvisionedComponentsCacheProps.CACHE_NAME, key = "#projectKey")
    public void evict(String projectKey) {
        // We need to declare this method outside the actual eviction point since that one
        // has protected visibility, so the @CacheEvict annotation wouldn't work
        // properly. Also, if called within the same bean, Spring won't proxy the method
    }

}
