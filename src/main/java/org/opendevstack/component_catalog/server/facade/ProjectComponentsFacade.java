package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.CatalogProjectComponentsGroupsRestrictionProps;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentExtendedInfoMapper;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentMetricsMapper;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentsInfoMapper;
import org.opendevstack.component_catalog.server.model.*;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.common.PaginationUtils;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidComponentStateException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class ProjectComponentsFacade {

    private final ProvisionerActionsService provisionerActionsService;
    private final ProjectComponentsInfoMapper projectComponentsInfoMapper;
    private final ProjectsInfoService projectsInfoService;
    private final ProjectComponentExtendedInfoMapper projectComponentExtendedInfoMapper;
    private final CatalogProjectComponentsGroupsRestrictionProps  catalogProjectComponentsGroupsRestrictionProps;
    private final ProjectComponentMetricsMapper projectComponentListItemMapper;

    @Value("${devstack.marketplace-api.permitted-oids}")
    private final List<String> permittedOids;

    public List<ProjectComponentInfo> getProjectComponentsInfo(String projectKey, String accessToken) {
        var projectComponents = provisionerActionsService.getProjectComponents(projectKey);

        if (notValid(projectComponents, projectKey, accessToken)) {
            return Collections.emptyList();
        }

        List<String> userGroups = projectsInfoService.getProjectGroups(accessToken);

        if (!userBelongsToProjectGroups(userGroups, projectKey)) {
            throw new ForbiddenException("User must belong to the project to get its components");
        }

        return projectComponents.getComponents()
                .values()
                .stream()
                .map(component -> {
                    try {
                        return projectComponentsInfoMapper.mapToProjectComponentInfo(component, accessToken, projectKey, userGroups);
                    } catch (InvalidIdException | InvalidCatalogItemEntityException | NullPointerException e) {
                        log.error("Unable to map component: {}", component, e);
                        return Optional.<ProjectComponentInfo>empty();
                    }
                })
                .flatMap(Optional::stream)
                .toList();
    }

    public ProjectComponentExtendedInfo getProjectComponentExtendedInfo(String projectKey, String componentId, String accessToken) {
        var projectComponents = provisionerActionsService.getProjectComponents(projectKey);

        if (notValid(projectComponents, projectKey, accessToken)) {
            throw new IllegalArgumentException("Valid projectKey, componentId and accessToken are mandatory.");
        }

        List<String> userGroups = projectsInfoService.getProjectGroups(accessToken);
        if (!userBelongsToProjectGroups(userGroups, projectKey)) {
            throw new ForbiddenException("User must belong to the project to get its components");
        }

        return Optional.ofNullable(projectComponents.getComponents())
                .orElse(Map.of())
                .values()
                .stream()
                .filter(component -> component.getComponentId().equals(componentId))
                .findFirst()
                .flatMap(projectComponentExtendedInfoMapper::mapToProjectComponentExtendedInfo)
                .orElseThrow(() ->
                    new ComponentNotFoundException("Component with ID " + componentId + " not found in project " + projectKey)
                );
    }

    public ProjectComponentsMetrics getAllProjectComponents(String accessToken, int page, int size, String paginationBaseUrl) {
        validateTokenPermittedOids(accessToken);
        PaginationUtils.validatePagination(page, size, 100);

        var allProjectsJsons = provisionerActionsService.listAllProjectsJsons().stream()
                .map(projectKeyJson -> projectKeyJson.replaceAll(".json", ""))
                .sorted()
                .toList();

        int index = 0;
        int fromIndex = page * size;
        int toIndex = fromIndex + size;
        Collection<ProjectComponent> projectComponents;
        List<ProjectComponentMetrics> data = new ArrayList<>();
        for (String projectKey : allProjectsJsons) {
            projectComponents = provisionerActionsService.getProjectComponents(projectKey).getComponents().values();
            if (index >= toIndex) {
                index += projectComponents.size();
                continue;
            }
            List<ProjectComponent> sortedComponents = projectComponents
                    .stream()
                    .sorted(this::compareProjectComponent)
                    .toList();

            for (ProjectComponent component : sortedComponents) {
                if (index >= fromIndex && index < toIndex) {
                    Optional<ProjectComponentMetrics> p =  projectComponentListItemMapper.mapToProjectComponentMetrics(component, projectKey);
                    if (p.isPresent()) {
                        // Badly formed project components shouldn't be returned
                        // in the response
                        data.add(p.orElseThrow(() -> new InvalidComponentStateException(
                                "The project component" + component.getComponentId() + " provisioned in project" + projectKey + " couldn't be correctly processed.")
                        ));
                        index++;
                    }
                } else {
                    index++;
                }
            }
        }
        int totalElements = index;
        Pagination pagination = PaginationUtils.buildPagination(page, size, totalElements, paginationBaseUrl);

        return ProjectComponentsMetrics.builder()
                .data(data)
                .pagination(pagination)
                .build();
    }

    private boolean notValid(ProjectComponents projectComponents, String projectKey, String accessToken) {
        return (projectComponents == null || projectComponents.getComponents() == null ||
                projectComponents.getComponents().isEmpty() || StringUtils.isBlank(accessToken) ||
                StringUtils.isBlank(projectKey));
    }

    private boolean userBelongsToProjectGroups(List<String> groups, String projectKey) {
        if (groups == null) return false;
        return groups.stream()
                .filter(Objects::nonNull)
                .anyMatch(g -> catalogProjectComponentsGroupsRestrictionProps.getPrefix().stream().anyMatch(g.toUpperCase()::startsWith) &&
                        g.toUpperCase().contains(projectKey.toUpperCase()));
    }

    private void validateTokenPermittedOids(String accessToken) {
        var oid = JwtUtils.extractClaim(accessToken, "oid");
        if (!oid.map(permittedOids::contains).orElse(false)) {
            throw new ForbiddenException("Invalid caller. Please, provide a valid token within the request.");
        }
    }

    private boolean hasNoDates(ProjectComponent c) {
        return (c.getCreatedAt() == null || c.getCreatedAt().isBlank())
                && (c.getUpdatedAt() == null || c.getUpdatedAt().isBlank());
    }

    private Long extractTimestamp(ProjectComponent c) {
        if (c.getCreatedAt() != null && !c.getCreatedAt().isBlank()) {
            return Long.parseLong(c.getCreatedAt());
        }

        return Long.parseLong(c.getUpdatedAt());
    }

    private int compareProjectComponent(ProjectComponent a, ProjectComponent b) {
        // The overall sorting order is from creation date ASC
        // If not createdAt/updatedAt are present, we treat the item
        // as one of the oldest, without further comparing

        int cmp = Boolean.compare(!hasNoDates(a), !hasNoDates(b));
        if (cmp != 0) return cmp;

        long t1 = hasNoDates(a) ? 0 : extractTimestamp(a);
        long t2 = hasNoDates(b) ? 0 : extractTimestamp(b);

        return Long.compare(t1, t2);
    }

}
