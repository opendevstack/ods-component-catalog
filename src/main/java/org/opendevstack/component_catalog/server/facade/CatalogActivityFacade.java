package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.utils.StringUtils;
import org.opendevstack.component_catalog.server.mappers.CatalogActivityMapper;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.model.PaginatedCatalogActivities;
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
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogActivityFacade {
    private final ProjectComponentsFacade projectComponentsFacade;
    private final CatalogEntitiesService catalogEntitiesService;
    private final ProjectsInfoService projectsInfoService;
    private final AuthenticationFacade authenticationFacade;
    private final CatalogActivityMapper catalogActivityMapper;

    public List<CatalogActivity> getCatalogActivities(String catalogId, String sort, String project, String status, Long startDate, Long endDate) {
        var userIsAdminForCatalog = isUserAdminForCatalogProjects(catalogId);

        if (userIsAdminForCatalog) {
            var catalogActivities = getCatalogActivities(sort, project, status, startDate, endDate);

            log.debug("User is admin for catalog owner groups. Returning catalog activities: {}", catalogActivities);

            return catalogActivities;
        } else {
            log.debug("User is not admin for catalog owner groups. Returning empty list.");

            return Collections.emptyList();
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

    private List<CatalogActivity> getCatalogActivities(String sort, String project, String status, Long startDate, Long endDate) {
        var allProjectComponents = projectComponentsFacade.getAllProjectComponents();

        var allProjectComponentsByProjectKey = new HashMap<String, ProjectComponents>();

        // Filter out by projectKey (if present)
        if (project != null && !project.isBlank()) {
            allProjectComponentsByProjectKey.put(project, allProjectComponents.get(project));
        } else {
            allProjectComponentsByProjectKey.putAll(allProjectComponents);
        }

        List<CatalogActivity> catalogActivities = new ArrayList<>();

        for (var projectComponentsByProjectKey : allProjectComponentsByProjectKey.entrySet()) {
            var catalogActivitiesByProject = projectComponentsByProjectKey.getValue().getComponents().values().stream()
                    .map(projectComponent -> catalogActivityMapper.asCatalogActivity(projectComponentsByProjectKey.getKey(), calculateCatalogItemSlug(projectComponent), projectComponent))
                    .toList();

            catalogActivities.addAll(catalogActivitiesByProject);
        }

        var filteredOutCatalogActivities = filterOutCatalogActivities(catalogActivities, status, startDate, endDate);
        var sortedCatalogActivities = sortCatalogActivities(filteredOutCatalogActivities, sort);

        log.debug("Returning catalog activities: {}", sortedCatalogActivities);

        return sortedCatalogActivities;
    }

    private List<CatalogActivity> sortCatalogActivities(List<CatalogActivity> catalogActivities, String sort) {
        Comparator<CatalogActivity> comparator = switch (sort) {
            case "project" -> Comparator.comparing(
                    CatalogActivity::getProjectKey,
                    Comparator.nullsLast(String::compareTo)
            );
            case "status" -> Comparator.comparing(
                    CatalogActivity::getStatus,
                    Comparator.nullsLast(Enum::compareTo)
            );
            case "creationDate" -> Comparator.comparing(
                    CatalogActivity::getCreatedAt,
                    Comparator.nullsLast(Comparable::compareTo)
            );
            default -> null;
        };

        return comparator == null
                ? catalogActivities
                : catalogActivities.stream().sorted(comparator).toList();
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
                .filter(activity -> activity.getCreatedAt().longValue() >= startDate
                        && activity.getCreatedAt().longValue() <= endDate).toList();
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
            return "n/a";
        }

    }

    private static String extractRepoName(String path) {
        try {
            return path.split("/repos/")[1].split("/raw/")[0];
        } catch (Exception e) {
            log.warn("Invalid catalog item path: {}", path, e);

            return "n/a";
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
