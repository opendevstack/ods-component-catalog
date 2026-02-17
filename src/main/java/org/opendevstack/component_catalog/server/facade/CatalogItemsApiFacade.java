package org.opendevstack.component_catalog.server.facade;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.server.controllers.CatalogApiAdapter;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.model.CatalogItemRestriction;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.UserActionsEntitiesService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.opendevstack.component_catalog.util.FunctionalUtils.fieldSorter;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogItemsApiFacade {
    private final AuthorizationInfo authInfo;
    private final CatalogApiAdapter catalogApiAdapter;
    private final CatalogServiceAdapter catalogServiceAdapter;
    private final ProjectsInfoService projectsInfoService;
    private final CatalogEntitiesService catalogEntitiesService;
    private final UserActionsEntitiesService userActionsEntitiesService;

    public CatalogItem asCatalogItem(CatalogRequestParams catalogRequestParams) {
        var clusters = getClusters(catalogRequestParams);
        var userGroups = getProjectGroups(catalogRequestParams);

        return catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups);
    }

    public List<CatalogItemFilter> catalogItemFiltersFrom(CatalogRequestParams catalogRequestParams) {
        var clusters = getClusters(catalogRequestParams);
        var userGroups = getProjectGroups(catalogRequestParams);

        return catalogApiAdapter.catalogItemFiltersFrom(catalogRequestParams, clusters, userGroups);
    }

    public String getIdToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.debug("Authenticated user '{}'", auth.getName());

        var principal = (UserPrincipal) auth.getPrincipal();

        return principal.getAadIssuedBearerToken();
    }

    private List<String> getProjectGroups(CatalogRequestParams catalogRequestParams) {
        if (catalogRequestParams.getAccessToken() == null) {
            return Collections.emptyList();
        } else {
            return projectsInfoService.getProjectGroups(catalogRequestParams.getIdToken(), catalogRequestParams.getAccessToken());
        }
    }

    private List<String> getClusters(CatalogRequestParams catalogRequestParams) {
        if (catalogRequestParams.getAccessToken() == null) {
            return Collections.emptyList();
        } else {
            var projectInfo = projectsInfoService.getProjectClusters(catalogRequestParams.getProjectKey(), catalogRequestParams.getIdToken(), catalogRequestParams.getAccessToken());
            var clusters = Optional.ofNullable(projectInfo)
                    .map(ProjectInfo::getClusters)
                    .orElse(Collections.emptyList());

            log.debug("Clusters {} for the project {}", clusters, catalogRequestParams.getProjectKey());

            return clusters;
        }
    }

    public List<CatalogItem> fetchCatalogItems(CatalogRequestParams catalogRequestParams)
            throws InvalidIdException, InvalidCatalogEntityException {
        var principalPermissions = currentPrincipalCatalogPermissions(catalogRequestParams.getCatalogId());
        var itemsEntitiesCtxs = catalogEntitiesService.getCatalogItemsEntities(catalogRequestParams.getCatalogId());
        var userActionsEntity = userActionsEntitiesService.getDefaultUserActionsEntity();
        if (filterByContributingFileExists(catalogRequestParams.getCatalogId()))
            return itemsEntitiesCtxs.stream()
                    .map(ctx -> asCatalogItem(
                                    catalogRequestParams.toBuilder()
                                            .catalogItemEntityContext(ctx)
                                            .userActionsEntity(userActionsEntity)
                                            .permissions(principalPermissions)
                                            .build()
                            )
                    )
                    .filter(item -> filterByProject(item, catalogRequestParams.getProjectKey()))
                    .filter(item -> filterByContributingFileExists(item.getId()))
                    .sorted(fieldSorter(CatalogItem::getTitle, catalogRequestParams.getSortOrder()))
                    .toList();
        else {
            return List.of();
        }
    }

    public CatalogItem fetchCatalogItem(CatalogRequestParams catalogRequestParams)
            throws InvalidIdException, InvalidCatalogItemEntityException {
        var principalPermissions = currentPrincipalCatalogPermissions(catalogRequestParams.getCatalogItemId());
        var maybeItemEntityCtx = catalogEntitiesService.getCatalogItemEntity(catalogRequestParams.getCatalogItemId());
        var userActionsEntity = userActionsEntitiesService.getDefaultUserActionsEntity();

        return maybeItemEntityCtx
                .map(catalogItemEntityContext -> asCatalogItem(
                                catalogRequestParams.toBuilder()
                                        .catalogItemEntityContext(catalogItemEntityContext)
                                        .userActionsEntity(userActionsEntity)
                                        .permissions(principalPermissions)
                                        .build()
                        )
                )
                .filter(item -> filterByProject(item, catalogRequestParams.getProjectKey()))
                .filter(item -> filterByContributingFileExists(catalogRequestParams.getCatalogItemId()))
                .orElse(null);
    }

    protected boolean filterByProject(CatalogItem item, String projectKey) {
        var projects = Optional.ofNullable(item.getRestrictions())
                .map(CatalogItemRestriction::getProjects)
                .orElse(Collections.emptySet());
        return projects.isEmpty() || (projectKey != null && projects.contains(projectKey));
    }

    protected boolean filterByContributingFileExists(String id) {
        return catalogServiceAdapter.contributingFileExists(id);
    }

    protected Set<CatalogEntityPermissionEnum> currentPrincipalCatalogPermissions(String id) {
        var principalName = authInfo.getCurrentPrincipalName();
        try {
            return catalogEntitiesService.catalogPrincipalPermissions(id, principalName);
        } catch (InvalidIdException e) {
            log.error("Unable to get permissions for: '{}' and resource with id: {}", principalName, id, e);
            return Set.of();
        }
    }
}
