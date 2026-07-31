package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.tika.utils.StringUtils;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.CatalogActivityMapper;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.PaginatedCatalogActivities;
import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.model.SortParameter;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.common.PaginationUtils;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogActivityFacade {
    private final ProjectComponentsFacade projectComponentsFacade;
    private final AuthenticationFacade authenticationFacade;
    private final CatalogItemsApiFacade catalogItemsApiFacade;
    private final CatalogEntitiesService catalogEntitiesService;
    private final ProjectsInfoService projectsInfoService;
    private final CatalogActivityMapper catalogActivityMapper;

    public List<CatalogActivity> getCatalogActivities(String catalogId, SortParameter sort, SortOrder sortOrder, String project, String status, Long startDate, Long endDate) {
        var userIsAdminForCatalog = isUserAdminForCatalogProjects(catalogId);

        if (userIsAdminForCatalog) {
            var notNullSortParameter = Optional.ofNullable(sort).orElse(SortParameter.CREATION_DATE);
            var notNullSortOrder = Optional.ofNullable(sortOrder).orElse(SortOrder.ASC);

            var catalogActivities = getCatalogActivitiesForCatalog(catalogId, notNullSortParameter, notNullSortOrder, project, status, startDate, endDate);

            log.debug("User is admin for catalog owner groups. Returning catalog activities: {}", catalogActivities);

            return catalogActivities;
        } else {
            log.debug("User is not admin for catalog owner groups. Returning empty list.");

            throw new ForbiddenException("User is not admin for catalog owner groups");
        }

    }

    public PaginatedCatalogActivities paginateCatalogActivities(List<CatalogActivity> catalogActivities, int page, int size, String baseUrl) {
        var paginatedEntity = PaginationUtils.buildPagination(page, size, catalogActivities, baseUrl);

        log.debug("Paginated catalog activities: {} with pagination: {}", paginatedEntity.getData(), paginatedEntity.getPagination());

        return PaginatedCatalogActivities.builder()
                .data(paginatedEntity.getData())
                .pagination(paginatedEntity.getPagination())
                .build();
    }

    @SneakyThrows
    private boolean isUserAdminForCatalogProjects(String catalogId) {
        var catalogEntity = catalogEntitiesService.getCatalogEntity(catalogId)
                .orElseThrow(() -> new ElementNotFoundException("Catalog entity not found for catalogId: " + catalogId));

        var catalogOwnerGroups = Optional.ofNullable(catalogEntity.getMetadata())
                .map(CatalogEntityMetadata::getOwners)
                .orElse(Collections.emptyList());

        var userGroups = getProjectGroups();

        var userIsAdminForCatalog = userGroups.stream().anyMatch(catalogOwnerGroups::contains);

        log.debug("User groups {} match catalog owner groups {} for catalogId {}. User is admin for catalog: {}", userGroups, catalogOwnerGroups, catalogId, userIsAdminForCatalog);

        return userIsAdminForCatalog;
    }

    private List<CatalogActivity> getCatalogActivitiesForCatalog(String catalogId, SortParameter sort, SortOrder sortOrder, String project, String status, Long startDate, Long endDate) {
        var allProjectComponents = getAllProjectComponents(catalogId);

        var allProjectComponentsByProjectKey = new HashMap<String, ProjectComponents>();

        // Filter out by projectKey (if present)
        if (project != null && !project.isBlank()) {
            allProjectComponentsByProjectKey.put(project, allProjectComponents.get(project));
        } else {
            allProjectComponentsByProjectKey.putAll(allProjectComponents);
        }

        List<CatalogActivity> catalogActivities = new ArrayList<>();

        for (var projectComponentsByProjectKey : allProjectComponentsByProjectKey.entrySet()) {
            var projectComponents = Optional.ofNullable(projectComponentsByProjectKey.getValue());

            var catalogActivitiesByProject = projectComponents.map(
                    pc -> pc.getComponents().values().stream()
                            .map(projectComponent -> catalogActivityMapper.asCatalogActivity(projectComponentsByProjectKey.getKey(), calculateCatalogItemSlug(projectComponent), projectComponent))
                            .toList()
            ).orElse(Collections.emptyList());

            catalogActivities.addAll(catalogActivitiesByProject);
        }

        var filteredOutCatalogActivities = filterOutCatalogActivities(catalogActivities, status, startDate, endDate);
        var sortedCatalogActivities = sortCatalogActivities(filteredOutCatalogActivities, sort, sortOrder);

        log.debug("Returning catalog activities: {}", sortedCatalogActivities);

        return sortedCatalogActivities;
    }

    private Map<String, ProjectComponents> getAllProjectComponents(String catalogId) {
        var catalogItemIdsForCatalog = getCatalogItemIds(catalogId);

        var allProjectComponents = projectComponentsFacade.getAllProjectComponents();

        Map<String, ProjectComponents> projectComponentsForCatalog = new HashMap<>();

        for (var projectComponentEntry : allProjectComponents.entrySet()) {
            var projectKey = projectComponentEntry.getKey();
            Map<String, ProjectComponent> projectComponentsMap = projectComponentEntry.getValue().getComponents().entrySet().stream()
                    .filter(entry -> catalogItemIdsForCatalog.contains(entry.getValue().getCatalogItemId()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            projectComponentsForCatalog.put(projectKey, ProjectComponents.builder().components(projectComponentsMap).build());
        }

        return projectComponentsForCatalog;
    }

    @SneakyThrows
    private List<String> getCatalogItemIds(String catalogId) {
        var catalogItemRequestParams = CatalogRequestParams.builder()
                .catalogId(catalogId)
                .sortOrder(SortOrder.ASC)
                .accessToken(authenticationFacade.getAccessToken())
                .build();

        var items = catalogItemsApiFacade.fetchCatalogItems(catalogItemRequestParams);

        return items.stream()
                .map(CatalogItem::getId)
                .map(this::removeRefFromId)
                .toList();
    }

    @SneakyThrows
    private String removeRefFromId(String id) {
        log.debug("Removing ref from catalog item id: {}", id);

        var decodedId = IdEncoderDecoder.idDecode(id);
        var idWithoutRef = decodedId.split("\\?at=")[0];

        log.debug("Decoded catalog item id: {}. Id without ref: {}", decodedId, idWithoutRef);

        var encodedIdWithoutRef = IdEncoderDecoder.idEncode(idWithoutRef);

        log.debug("Encoded catalog item id now without ref: {}", encodedIdWithoutRef);

        return encodedIdWithoutRef;
    }

    private List<CatalogActivity> sortCatalogActivities(List<CatalogActivity> catalogActivities, SortParameter sort, SortOrder sortOrder) {
        Comparator<CatalogActivity> comparator = switch (sort) {
            case PROJECT -> Comparator.comparing(
                    CatalogActivity::getProjectKey,
                    Comparator.nullsLast(String::compareTo)
            );
            case STATUS -> Comparator.comparing(
                    CatalogActivity::getStatus,
                    Comparator.nullsLast(Enum::compareTo)
            );
            case CREATION_DATE -> Comparator.comparing(
                    CatalogActivity::getCreatedAt,
                    Comparator.nullsLast(Comparable::compareTo)
            );
        };

        if (sortOrder == SortOrder.DESC) {
            comparator = comparator.reversed();
        }

        return catalogActivities.stream().sorted(comparator).toList();
    }

    private List<CatalogActivity> filterOutCatalogActivities(List<CatalogActivity> catalogActivities, String status, Long startDate, Long endDate) {
        if (status != null && !status.isBlank()) {
            return List.copyOf(filterOutByStatus(catalogActivities, status));
        } else if (startDate != null && endDate != null) {
            return List.copyOf(filterOutByDateRange(catalogActivities, startDate, endDate));
        } else {
            return List.copyOf(catalogActivities);
        }
    }

    private List<CatalogActivity> filterOutByStatus(List<CatalogActivity> catalogActivities, String status) {
        return catalogActivities.stream().filter(activity -> status.equals(activity.getStatus().getValue())).toList();
    }

    private List<CatalogActivity> filterOutByDateRange(List<CatalogActivity> catalogActivities, Long startDate, Long endDate) {
        return catalogActivities.stream()
                .filter(activity ->
                            activity.getCreatedAt() != null &&
                            activity.getCreatedAt().longValue() >= startDate &&
                            activity.getCreatedAt().longValue() <= endDate)
                .toList();
    }

    @SneakyThrows
    private String calculateCatalogItemSlug(ProjectComponent projectComponent) {
        if (!StringUtils.isBlank(projectComponent.getCatalogItemId())) {
            var catalogItemPath = IdEncoderDecoder.idDecode(projectComponent.getCatalogItemId());
            var projectKey = extractProjectName(catalogItemPath);
            var repoName = extractRepoName(catalogItemPath);


            var catalogItemSlug = new CatalogItemSlug(projectKey.toLowerCase(), repoName.toLowerCase());

            return catalogItemSlug.toString();
        } else {
            return Strings.EMPTY;
        }

    }

    private static String extractRepoName(String path) {
        try {
            return path.split("/repos/")[1].split("/raw/")[0];
        } catch (Exception e) {
            log.warn("Invalid catalog item path: {}", path, e);

            return Strings.EMPTY;
        }

    }

    public static String extractProjectName(String path) {
        try {
            return path.split("projects/")[1].split("/repos/")[0];
        } catch (Exception e) {
            log.warn("Invalid catalog item path: {}", path, e);

            return "n/a";
        }
    }

    private List<String> getProjectGroups() {
        var accessToken = authenticationFacade.getAccessToken();

        return projectsInfoService.getProjectGroups(accessToken);
    }
}
