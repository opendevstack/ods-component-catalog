package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;

/**
 * Domain service responsible for resolving a {@link CatalogItemSlug} to a {@link CatalogItemEntityContext}.
 * <p>
 * Resolution steps:
 * <ol>
 *   <li>Load all catalogs from the marketplace (catalog-of-catalogs).</li>
 *   <li>For each catalog, load its items and find the one whose normalised Bitbucket project key
 *       matches the slug's {@code projectKey} component and whose repository slug matches
 *       the slug's {@code repoName} component.</li>
 * </ol>
 */
@Service
@AllArgsConstructor
@Slf4j
public class CatalogItemBySlugService {

    private final CatalogsCollectionService catalogsCollectionService;
    private final CatalogEntitiesService catalogEntitiesService;
    private final CatalogServiceAdapter catalogServiceAdapter;
    private final BitbucketService bitbucketService;

    /**
     * Resolves a {@link CatalogItemSlug} to the matching {@link CatalogItemEntityContext}.
     *
     * @param slug the parsed composite slug
     * @return an {@link Optional} with the matching context, or empty if not found
     * @throws InvalidIdException              if any catalog or item id is structurally invalid
     * @throws InvalidCatalogEntityException   if a catalog entity cannot be parsed
     */
    public Optional<CatalogItemEntityContext> findByCatalogItemSlug(CatalogItemSlug slug)
            throws InvalidIdException, InvalidCatalogEntityException {

        log.debug("Resolving catalog item by slug: '{}'", slug);

        var catalogsCollection = catalogsCollectionService.getCatalogsCollection();

        if (catalogsCollection.isEmpty()) {
            log.warn("No catalogs collection found while resolving slug '{}'", slug);
            return Optional.empty();
        }

        var targets = catalogsCollection.get().getMetadata().getSpec().getTargets();
        if (targets == null || targets.length == 0) {
            log.warn("Catalogs collection has no targets while resolving slug '{}'", slug);
            return Optional.empty();
        }

        for (var target : targets) {
            var catalogId = idEncode(target.getUrl());
            var catalogIdPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogId);

            // Optimisation: skip catalogs whose Bitbucket project key does not match the slug's project key,
            // avoiding unnecessary item loading. This relies on the assumption that catalog items live in the
            // same Bitbucket project as their catalog definition.
            if (!CatalogItemSlug.normalise(catalogIdPathAt.getProjectKey()).equals(slug.getProjectKey())) {
                continue;
            }

            log.debug("Catalog target '{}' matches slug project key '{}', checking items...",
                    target.getUrl(), slug.getProjectKey());

            var match = findMatchingItemInCatalog(catalogId, slug, target.getUrl());
            if (match.isPresent()) {
                return match;
            }
        }

        log.debug("No catalog item matched slug '{}'", slug);
        return Optional.empty();
    }

    /**
     * Loads the catalog entity for {@code catalogId} and searches its targets for the item
     * matching {@code slug}. Returns the first match, or empty if none is found.
     */
    private Optional<CatalogItemEntityContext> findMatchingItemInCatalog(String catalogId, CatalogItemSlug slug, String catalogUrl)
            throws InvalidIdException, InvalidCatalogEntityException {
        var slugCatalogEntity = catalogEntitiesService.getCatalogEntity(catalogId);
        if (slugCatalogEntity.isEmpty()) {
            return Optional.empty();
        }

        // A slug uniquely identifies one item. Multiple matches (e.g. the same repo referenced
        // in more than one catalog entry) are not expected; we return the first one found.
        for (var itemTarget : slugCatalogEntity.get().getMetadata().getSpec().getTargets()) {
            var itemPathAt = bitbucketService.pathAtBuilder()
                    .rawUrl(itemTarget.getUrl())
                    .build();
            if (CatalogItemSlug.normalise(itemPathAt.getProjectKey()).equals(slug.getProjectKey())
                    && itemPathAt.getRepoSlug().equalsIgnoreCase(slug.getRepoName())) {
                var itemId = idEncode(itemPathAt.getPathAt());
                log.debug("Resolved slug '{}' to item id '{}' in catalog target '{}'", slug, itemId, catalogUrl);
                return catalogEntitiesService.getCatalogItemEntity(itemId);
            }
        }
        return Optional.empty();
    }

}

