package org.opendevstack.component_catalog.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Warms up the Bitbucket cache on application startup and after each scheduled eviction.
 * <p>
 * Strategy:
 * <ol>
 *   <li>Load the catalog-of-catalogs file → populates cache for the top-level YAML.</li>
 *   <li>For every catalog target, load all its catalog items → populates cache for each item YAML,
 *       last commit info, contributors file, etc.</li>
 * </ol>
 * This way the cache always looks "full" to the first user after a restart or after a scheduled
 * eviction (because the cache configuration scheduler calls {@link #warmup()} right after evicting).
 * </p>
 */
@Service
@AllArgsConstructor
@Slf4j
public class CacheWarmupService implements ApplicationRunner {

    private final CatalogsCollectionService catalogsCollectionService;
    private final CatalogEntitiesService catalogEntitiesService;

    /** Called by Spring Boot after the application context is fully started. */
    @Override
    public void run(ApplicationArguments args) {
        log.info("Cache warmup: starting at application startup...");
        warmup();
    }

    /**
     * Performs a full cache warmup.
     * Safe to call multiple times (idempotent from the cache's point of view).
     */
    public void warmup() {
        try {
            var maybeCatalogsCollection = catalogsCollectionService.getCatalogsCollection();

            if (maybeCatalogsCollection.isEmpty()) {
                log.warn("Cache warmup: catalog-of-catalogs not found, skipping warmup.");
                return;
            }

            var targets = maybeCatalogsCollection
                    .map(e -> e.getMetadata().getSpec().getTargets())
                    .map(Arrays::asList)
                    .orElse(List.of());

            log.info("Cache warmup: found {} catalog(s) to warm up.", targets.size());

            int loaded = 0;
            int errors = 0;

            for (CatalogsCollectionsEntityTarget target : targets) {
                try {
                    var catalogId = target.getSlug();
                    log.debug("Cache warmup: loading catalog '{}'", catalogId);
                    var items = catalogEntitiesService.getCatalogItemsEntities(catalogId);
                    log.debug("Cache warmup: catalog '{}' loaded {} item(s).", catalogId, items.size());
                    loaded++;
                } catch (InvalidIdException | RuntimeException e) {
                    log.warn("Cache warmup: error loading catalog '{}': {}", target.getSlug(), e.getMessage());
                    errors++;
                }
            }

            log.info("Cache warmup: finished. Catalogs loaded: {}, errors: {}.", loaded, errors);

        } catch (Exception e) {
            // Never let warmup failures crash the app or break the eviction scheduler
            log.error("Cache warmup: unexpected error during warmup, cache may be partially populated.", e);
        }
    }
}


