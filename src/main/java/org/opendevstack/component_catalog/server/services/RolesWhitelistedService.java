package org.opendevstack.component_catalog.server.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.entity.RolesWhitelisted;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolesWhitelistedService {

    private final CatalogServiceAdapter catalogServiceAdapter;
    private final BitbucketService bitbucketService;
    private final ProvisionerActionsConfiguration provisionerActionsConfiguration;

    public List<String> resolveWhitelistedRolesForCatalogItemId(String catalogItemId) {
        BitbucketPathAt catalogItemPathAt;
        try {
            catalogItemPathAt = catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId);
        } catch (InvalidIdException e) {
            throw new InvalidEntityException("Invalid catalogItemId: %s".formatted(catalogItemId));
        }

        var itemSlug = new CatalogItemSlug(catalogItemPathAt.getProjectKey().toLowerCase(), catalogItemPathAt.getRepoSlug());
        BitbucketPathAt pathAt = buildRolesWhitelistedBitbucketPathAt();

        Optional<RolesWhitelisted> roles = catalogServiceAdapter.getYamlEntity(pathAt, RolesWhitelisted.class);

        return roles.orElseThrow(() -> new InvalidEntityException("Invalid RolesWhitelisted.yaml file, path: %s".formatted(pathAt.getPathAt())))
                .getRoles().entrySet().stream()
                .filter(role -> role.getValue().contains(itemSlug.toString()))
                .map(Map.Entry::getKey)
                .toList();
    }

    private BitbucketPathAt buildRolesWhitelistedBitbucketPathAt() {
        return bitbucketService.pathAtBuilder()
                .projectKey(provisionerActionsConfiguration.getProjectKey())
                .repoSlug(provisionerActionsConfiguration.getProjectConfigurationsRepositorySlug())
                .subPath(provisionerActionsConfiguration.getProjectConfigurationsRolesWhitelistedPath())
                .at(provisionerActionsConfiguration.getBranchName())
                .build();
    }

}
