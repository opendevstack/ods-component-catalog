package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.OdsApiServerServiceProps;
import org.opendevstack.component_catalog.server.controllers.CatalogApiAdapter;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.model.CatalogDescriptor;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.CatalogItemBySlugService;
import org.opendevstack.component_catalog.server.services.CatalogsCollectionService;
import org.opendevstack.component_catalog.server.services.ProjectComponentsService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.UserActionsEntitiesService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.filters.CatalogItemsFilter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;
import org.opendevstack.component_catalog.util.JwtUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final CatalogItemBySlugService catalogItemBySlugService;
    private final CatalogsCollectionService catalogsCollectionService;
    private final OdsApiServerServiceProps odsApiServerServiceProps;
    private final ProjectComponentsService projectComponentsService;

    private final ProvisionerActionsService provisionerActionsService;
    private final AuthenticationFacade authenticationFacade;

    private final List<CatalogItemsFilter> catalogItemFilters;

    public CatalogItem asCatalogItem(CatalogRequestParams catalogRequestParams) {
        var tokenizedCatalogRequestParams = tokenize(catalogRequestParams);
        return asCatalogItemWithoutMandatoryToken(tokenizedCatalogRequestParams);
    }

    private CatalogItem asCatalogItemWithoutMandatoryToken(CatalogRequestParams catalogRequestParams) {
        var clusters = getClusters(catalogRequestParams);
        var userGroups = getProjectGroups(catalogRequestParams);

        var componentCount = calculateComponentCountForCatalogOwners(catalogRequestParams, userGroups);

        return catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);
    }

    public List<CatalogItemFilter> catalogItemFiltersFrom(CatalogRequestParams catalogRequestParams) {
        var tokenizedCatalogRequestParams = tokenize(catalogRequestParams);

        var clusters = getClusters(tokenizedCatalogRequestParams);
        var userGroups = getProjectGroups(tokenizedCatalogRequestParams);

        return catalogApiAdapter.catalogItemFiltersFrom(tokenizedCatalogRequestParams, clusters, userGroups, null);
    }

    public List<CatalogItem> fetchCatalogItems(CatalogRequestParams catalogRequestParams)
            throws InvalidIdException, InvalidCatalogEntityException {
        if (catalogRequestParams.getCatalogId() != null) {
            validateTokenFromHumanUser(catalogRequestParams.getAccessToken());
            return fetchCatalogItemsByCatalogId(catalogRequestParams);
        }

        validateTokenFromOds(catalogRequestParams.getAccessToken());

        var catalogsCollection = catalogsCollectionService.getCatalogsCollection().orElseThrow(() -> new InvalidCatalogEntityException("No catalogs were found."));
        var allCatalogsIds = catalogApiAdapter.asCatalogDescriptors(catalogsCollection).stream()
                .map(CatalogDescriptor::getId)
                .toList();

        List<CatalogItem> allCatalogItems = new ArrayList<>();
        for (String catalogId : allCatalogsIds) {
            allCatalogItems.addAll(fetchCatalogItemsByCatalogId(
                    CatalogRequestParams.builder()
                        .accessToken(null) // For better readability, but no accessToken is expected for this use case
                        .catalogId(catalogId)
                        .sortOrder(catalogRequestParams.getSortOrder())
                        .permissions(Set.of(CatalogEntityPermissionEnum.REPO_READ))
                        .build())
            );
        }

        return allCatalogItems.stream()
                .sorted(fieldSorter(CatalogItem::getTitle, catalogRequestParams.getSortOrder()))
                .toList();
    }

    private CatalogRequestParams tokenize(CatalogRequestParams catalogRequestParams) {
        CatalogRequestParams tokenizedCatalogRequestParams;
        if (catalogRequestParams.getAccessToken() == null) {
            tokenizedCatalogRequestParams = catalogRequestParams.toBuilder().accessToken(authenticationFacade.getAccessToken()).build();
        } else {
            tokenizedCatalogRequestParams = catalogRequestParams;
        }
        return tokenizedCatalogRequestParams;
    }

    private void validateTokenFromOds(String accessToken) throws ForbiddenException {
        var oid = JwtUtils.extractClaim(accessToken, "oid");
        if (!oid.orElse("").equals(odsApiServerServiceProps.getOid())) {
            throw new ForbiddenException("Invalid caller. Please, provide a valid token within the request.");
        }
    }

    private void validateTokenFromHumanUser(String accessToken) throws ForbiddenException {
        if (JwtUtils.extractClaim(accessToken, "scp").isEmpty()) {
            throw new ForbiddenException("Invalid caller. Please, provide a valid token within the request.");
        }
    }

    private List<CatalogItem> fetchCatalogItemsByCatalogId(CatalogRequestParams catalogRequestParams)
            throws InvalidIdException, InvalidCatalogEntityException {
        var principalPermissions = currentPrincipalCatalogPermissions(catalogRequestParams.getCatalogId());
        // We allow adding permissions via catalogRequestParams. Intended for client credentials flow, where there is no principal claim within the token
        var mergedPermissions =
                Stream.concat(principalPermissions.stream(), Optional.ofNullable(catalogRequestParams.getPermissions()).orElse(Set.of()).stream()).collect(Collectors.toSet());
        var itemsEntitiesCtxs = catalogEntitiesService.getCatalogItemsEntities(catalogRequestParams.getCatalogId());
        var userActionsEntity = userActionsEntitiesService.getDefaultUserActionsEntity();
        if (filterByContributingFileExists(catalogRequestParams.getCatalogId()))
            return itemsEntitiesCtxs.stream()
                    .map(ctx -> asCatalogItemWithoutMandatoryToken(
                                    catalogRequestParams.toBuilder()
                                            .catalogItemEntityContext(ctx)
                                            .userActionsEntity(userActionsEntity)
                                            .permissions(mergedPermissions)
                                            .accessToken(catalogRequestParams.getAccessToken()) // Leave the same token within the request (valid or not)
                                            .build()
                            )
                    )
                    .filter(item -> applyFilters(item, catalogRequestParams.getProjectKey()))
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
                .filter(item -> applyFilters(item, catalogRequestParams.getProjectKey()))
                .orElse(null);
    }

    public CatalogItem fetchCatalogItemBySlug(CatalogItemSlug slug)
            throws InvalidIdException, InvalidCatalogEntityException {
        var maybeItemEntityCtx = catalogItemBySlugService.findByCatalogItemSlug(slug);

        if (maybeItemEntityCtx.isEmpty()) {
            return null;
        }

        var itemEntityCtx = maybeItemEntityCtx.get();
        var principalPermissions = currentPrincipalCatalogPermissions(itemEntityCtx.getId());
        var userActionsEntity = userActionsEntitiesService.getDefaultUserActionsEntity();

        return asCatalogItem(
                CatalogRequestParams.builder()
                        .catalogItemEntityContext(itemEntityCtx)
                        .catalogItemId(itemEntityCtx.getId())
                        .userActionsEntity(userActionsEntity)
                        .permissions(principalPermissions)
                        .build()
        );
    }

    // We can not do workflowsFilter in here, because the parameters merge was already done
    protected boolean applyFilters(CatalogItem item, String projectKey) {
        var params = Collections.singletonList(projectKey);

        return catalogItemFilters.stream()
                .allMatch(filter -> filter.filter(item, params));
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

    private Integer calculateComponentCountForCatalogOwners(CatalogRequestParams catalogRequestParams, List<String> userGroups) {
        var catalogEntity = catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(catalogRequestParams.getCatalogItemEntityContext());
        var catalogOwnerGroups = catalogEntity
                .map(CatalogEntity::getMetadata)
                .map(CatalogEntityMetadata::getOwners)
                .orElse(Collections.emptyList());

        if (userGroups.stream().noneMatch(catalogOwnerGroups::contains)) {
            log.debug("User groups {} do not match any catalog owner groups {} for catalog item {}", userGroups, catalogOwnerGroups, catalogRequestParams.getCatalogItemEntityContext().getId());

            return null;
        } else {
            return calculateComponentCount(catalogRequestParams);
        }
    }

    private Integer calculateComponentCount(CatalogRequestParams catalogRequestParams) {
        var projectComponentsList = getAllProjectComponents();

        log.debug("Calculating component count for catalog item {} and projectComponents: {}", catalogRequestParams.getCatalogItemEntityContext().getId(), projectComponentsList);

        var componentCount = 0;
        var catalogItemId = catalogRequestParams.getCatalogItemEntityContext().getId();
        String catalogItemIdWithoutRef = null;

        try {
            catalogItemIdWithoutRef = projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId);
        } catch (InvalidEntityException e) {
            log.error("Error decoding catalogItemId {}: {}", catalogItemId, e.getMessage());
        }

        if (catalogItemIdWithoutRef != null) {
            for (ProjectComponents projectComponents : projectComponentsList) {

                for (var component : projectComponents.getComponents().values()) {

                    log.debug("Checking if Component {} with catalogItemId {} relates to catalog item {}", component, catalogItemIdWithoutRef, catalogItemId);

                    if (catalogItemIdWithoutRef.equals(component.getCatalogItemId())) {
                        log.debug("Component {} relates to catalog item {}", component, catalogItemId);

                        componentCount++;
                    }
                }
            }
        }

        log.debug("Component count {} for the project {} and catalog item {}", componentCount, catalogRequestParams.getProjectKey(), catalogRequestParams.getCatalogItemEntityContext().getId());

        return componentCount;
    }

    private List<String> getProjectGroups(CatalogRequestParams catalogRequestParams) {
        if (catalogRequestParams.getAccessToken() == null) {
            log.debug("No access token provided. Returning empty list of user groups");

            return Collections.emptyList();
        } else {
            return projectsInfoService.getProjectGroups(catalogRequestParams.getAccessToken());
        }
    }

    private List<String> getClusters(CatalogRequestParams catalogRequestParams) {
        if (catalogRequestParams.getAccessToken() == null ||
                StringUtils.isBlank(catalogRequestParams.getProjectKey())) {
            return Collections.emptyList();
        } else {
            var projectInfo = projectsInfoService.getProjectClusters(catalogRequestParams.getProjectKey(), catalogRequestParams.getAccessToken());
            var clusters = Optional.ofNullable(projectInfo)
                    .map(ProjectInfo::getClusters)
                    .orElse(Collections.emptyList());

            log.debug("Clusters {} for the project {}", clusters, catalogRequestParams.getProjectKey());

            return clusters;
        }
    }

    private List<ProjectComponents> getAllProjectComponents() {
        log.debug("Retrieving all project components");
        var projectComponentsProjectKeys = provisionerActionsService.getAllProjectComponentsProjectKeys();

        var projectComponentsList = projectComponentsProjectKeys.stream()
                .map(provisionerActionsService::getProjectComponents)
                .toList();

        log.debug("Project components retrieved: {}", projectComponentsList);

        return projectComponentsList;
    }

}
