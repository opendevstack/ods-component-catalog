package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.mappers.CatalogActivityMapper;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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

    @SneakyThrows
    public List<CatalogActivity> getCatalogActivities(String catalogId) {
        var catalogEntity = catalogEntitiesService.getCatalogEntity(catalogId)
                .orElseThrow(() -> new ElementNotFoundException("Catalog entity not found for catalogId: " + catalogId));

        var catalogOwnerGroups = Optional.ofNullable(catalogEntity.getMetadata())
                .map(CatalogEntityMetadata::getOwners)
                .orElse(Collections.emptyList());

        var userGroups = getProjectGroups();

        var userIsAdminForCatalog = userGroups.stream().anyMatch(catalogOwnerGroups::contains);

        if (userIsAdminForCatalog) {
            var catalogActivities = getCatalogActivities();

            log.debug("User groups {} match catalog owner groups {} for catalog catalogId {}. Returning catalog activities: {}", userGroups, catalogOwnerGroups, catalogId, catalogActivities);

            return catalogActivities;
        } else {
            log.debug("User groups {} do not match any catalog owner groups {} for catalog catalogId {}", userGroups, catalogOwnerGroups, catalogId);

            return Collections.emptyList();
        }

    }

    private List<CatalogActivity> getCatalogActivities() {
        var allProjectComponents = projectComponentsFacade.getAllProjectComponents();

        List<CatalogActivity> catalogActivities = new ArrayList<>();

        for (var projectComponentsByProjectKey : allProjectComponents.entrySet()) {

            var catalogActivitiesByProject = projectComponentsByProjectKey.getValue().getComponents().entrySet().stream()
                    // FIXME: Calculate proper slug
                    .map(entry -> catalogActivityMapper.asCatalogActivity(projectComponentsByProjectKey.getKey(), "anySlugToBeCalculatedLater", entry.getValue()))
                    .toList();

            catalogActivities.addAll(catalogActivitiesByProject);
        }

        var immutableCatalogActivities = List.copyOf(catalogActivities);

        log.debug("Returning catalog activities: {}", immutableCatalogActivities);

        return immutableCatalogActivities;
    }

    private List<String> getProjectGroups() {
        var accessToken = authenticationFacade.getAccessToken();

        return projectsInfoService.getProjectGroups(accessToken);
    }
}
