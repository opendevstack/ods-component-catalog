package org.opendevstack.component_catalog.server.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;
import org.springframework.stereotype.Service;

import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idDecode;

@Service
@Slf4j
@AllArgsConstructor
public class CatalogItemSlugResolver {

    private final ProvisionerActionsConfiguration provisionerActionsConfiguration;
    private final BitbucketService bitbucketService;

    public CatalogItemSlug resolve(String catalogItemId) throws InvalidIdException {
        String decodedCatalogItemId = idDecode(catalogItemId);
        try {
            var catalogItemPath = decodedCatalogItemId.contains("?at=")
                ? decodedCatalogItemId
                : decodedCatalogItemId + "?at=" + provisionerActionsConfiguration.getBranchName();

            BitbucketPathAt pathAt = bitbucketService.pathAtBuilder()
                .pathAt(catalogItemPath)
                    .build();

            return new CatalogItemSlug(
                    CatalogItemSlug.normalise(pathAt.getProjectKey()),
                    pathAt.getRepoSlug());
        } catch (RuntimeException e) {
            log.error("Unable to extract catalog item slug from catalogItemId: {}", catalogItemId, e);
            throw new InvalidIdException(catalogItemId, e);
        }

    }
}
