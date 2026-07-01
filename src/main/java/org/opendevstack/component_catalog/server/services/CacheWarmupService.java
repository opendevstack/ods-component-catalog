package org.opendevstack.component_catalog.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;

/**
 * Warms up the Bitbucket caches on application startup and after each scheduled eviction.
 * <p>
 * Strategy:
 * <ol>
 *   <li>Load the catalog-of-catalogs file → populates cache for the top-level YAML.</li>
 *   <li>For every catalog target, load all its catalog items → populates cache for each item YAML,
 *       last commit info, contributors file, etc.</li>
 * </ol>
 * This way the cache always looks "full" to the first user after a restart or after a scheduled
 * eviction (because the cache configuration scheduler calls {@link #warmupCatalogsBitbucketServiceCache()} right after evicting).
 * </p>
 */
@Service
@AllArgsConstructor
@Slf4j
public class CacheWarmupService implements ApplicationRunner {

    private final CatalogsCollectionService catalogsCollectionService;
    private final CatalogEntitiesService catalogEntitiesService;
    private final ProvisionerActionsService provisionerActionsService;

    /**
     * Called by Spring Boot after the application context is fully started.
     * Runs asynchronously so the app is ready to serve HTTP traffic immediately
     * while the cache is populated in the background.
     */
    @Override
    @Async
    public void run(ApplicationArguments args) {
        log.info("Cache warmup: starting asynchronously after application startup...");
        warmupCatalogsBitbucketServiceCache();
        warmupProjectComponentsCache();
    }

    /**
     * Performs a full cache warmup of the catalogs collection in the Bitbucket service cache.
     * Safe to call multiple times (idempotent from the cache's point of view).
     */
    public void warmupCatalogsBitbucketServiceCache() {
        try {
            log.info("Starting catalogs collection warmup in the Bitbucket service cache...");
            long initWarmup = System.currentTimeMillis();

            var maybeCatalogsCollection = catalogsCollectionService.getCatalogsCollection();

            if (maybeCatalogsCollection.isEmpty()) {
                log.warn("Catalogs collection cache warmup: catalog-of-catalogs not found, skipping warmup.");
                return;
            }

            var targets = maybeCatalogsCollection
                    .map(e -> e.getMetadata().getSpec().getTargets())
                    .map(Arrays::asList)
                    .orElse(List.of());

            log.info("Catalogs collection cache warmup: found {} catalog(s) to warm up.", targets.size());

            int loaded = 0;
            int errors = 0;

            for (CatalogsCollectionsEntityTarget target : targets) {
                if (warmupCatalog(target)) {
                    loaded++;
                } else {
                    errors++;
                }
            }

            log.info("Catalogs collection cache warmup: finished. Catalogs loaded: {}, errors: {}.", loaded, errors);
            log.info("Catalog collection warmup took {} seconds.", (System.currentTimeMillis() - initWarmup)/1000);

        } catch (Exception e) {
            // Never let warmup failures crash the app or break the eviction scheduler
            log.error("Catalogs collection cache warmup: unexpected error during warmup, cache may be partially populated.", e);
        }
    }

    /**
     * Attempts to load all items of a single catalog into the cache.
     * <p>
     * The catalog ID is derived exactly as the REST layer does it:
     * {@code idEncode(target.getUrl())} — see {@code EntitiesMapper.asCatalogDescriptor}.
     * </p>
     *
     * @return {@code true} if loaded successfully, {@code false} on error
     */
    private boolean warmupCatalog(CatalogsCollectionsEntityTarget target) {
        try {
            if (target.getUrl() == null) {
                log.warn("Catalogs collection cache warmup: skipping catalog '{}' — url is null, cannot derive catalog ID.", target.getSlug());
                return false;
            }

            // Same ID derivation as EntitiesMapper.asCatalogDescriptor: idEncode(target.getUrl())
            var catalogId = idEncode(target.getUrl());

            log.debug("Catalogs collection cache warmup: loading catalog '{}' (id: '{}')", target.getSlug(), catalogId);
            var items = catalogEntitiesService.getCatalogItemsEntities(catalogId);
            log.debug("Catalogs collection cache warmup: catalog '{}' loaded {} item(s).", target.getSlug(), items.size());
            return true;
        } catch (InvalidIdException | RuntimeException e) {
            log.warn("Catalogs collection cache warmup: error loading catalog '{}': {}", target.getSlug(), e.getMessage());
            return false;
        }
    }

    /**
     * Performs a full cache warmup of the project components cache.
     * Safe to call multiple times (idempotent from the cache's point of view).
     */
    public void warmupProjectComponentsCache() {
        try {
            log.info("Starting project components cache warmup...");
            var initWarmup = System.currentTimeMillis();

            var projectJsonList = provisionerActionsService.listAllProjectsJsons();

            if (projectJsonList == null || projectJsonList.isEmpty()) {
                log.info("Project components cache warmup: retrieved no information to warm up the cache, skipping warmup.");
                return;
            }
            log.info("Project components cache warmup: found {} projects to fully warm up.", projectJsonList.size());


            var projectKeys = projectJsonList.stream()
                    .filter(filename -> filename.endsWith(".json"))
                    .map(filename -> filename.replace(".json", ""))
                    .toList();

            Pair<Integer, Integer> result = warmupProjects(projectKeys);
            int loaded = result.getLeft();
            int errors = result.getRight();

            log.info("Project components cache warmup: finished. Project components loaded: {}. Projects not loaded: {}. Number of projects: {}", loaded, errors, projectJsonList.size());
            log.info("Project components warmup took {} seconds.", (System.currentTimeMillis() - initWarmup)/1000);

        } catch (Exception e) {
            // Never let warmup failures crash the app or break the eviction scheduler
            log.error("Project components cache warmup: unexpected error during warmup, cache may be partially populated.", e);
        }
    }

    private Pair<Integer, Integer> warmupProjects(List<String> projectKeys) {
        Integer nComponents;
        int loaded = 0;
        int errors = 0;
        for (String projectKey : projectKeys) {
            try {
                if ((nComponents = warmupProvisionedComponentsFromProject(projectKey)) != null) {
                    loaded += nComponents;
                } else {
                    log.info("Couldn't load project components from project key {}", projectKey);
                    ++errors;
                }
            } catch (Exception e) {
                log.warn("Project components cache warmup: error loading project '{}': {}", projectKey, e.getMessage());
                ++errors;
            }
        }
        return Pair.of(loaded, errors);
    }

    private Integer warmupProvisionedComponentsFromProject(String projectKey) {
        log.debug("Loading components from project {} into the project components cache...", projectKey);
        ProjectComponents projectComponents = provisionerActionsService.getProjectComponents(projectKey);
        return Optional.ofNullable(projectComponents)
                .map(ProjectComponents::getComponents)
                .map(Map::size)
                .orElse(null);
    }
}


