package org.opendevstack.component_catalog.server.services;

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

    /**
     * Resolves a {@link CatalogItemSlug} to the matching {@link CatalogItemEntityContext}.
     *
     * @param slug the parsed composite slug
     * @return an {@link Optional} with the matching context, or empty if not found
     * @throws InvalidIdException              if any catalog id is structurally invalid
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
            var itemContexts = catalogEntitiesService.getCatalogItemsEntities(catalogId);
            var match = itemContexts.stream()
                    .filter(ctx -> itemMatchesSlug(ctx, slug))
                    .findFirst();
            if (match.isPresent()) {
                log.debug("Resolved slug '{}' to item in catalog target '{}'", slug, target.getUrl());
                return match;
            }
        }

        log.debug("No catalog item matched slug '{}'", slug);
        return Optional.empty();
    }

    // --- private helpers ---

    private boolean itemMatchesSlug(CatalogItemEntityContext ctx, CatalogItemSlug slug) {
        var normalisedProjectKey = CatalogItemSlug.normalise(ctx.getRepoCatalogItemPathAt().getProjectKey());
        return normalisedProjectKey.equals(slug.getProjectKey())
                && slug.getRepoName().equalsIgnoreCase(ctx.getRepoCatalogItemPathAt().getRepoSlug());
    }

}

