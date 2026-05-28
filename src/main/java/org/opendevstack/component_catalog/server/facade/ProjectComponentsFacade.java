package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentExtendedInfoMapper;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentsInfoMapper;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
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

    private boolean notValid(ProjectComponents projectComponents, String projectKey, String accessToken) {
        return (projectComponents == null || projectComponents.getComponents() == null ||
                projectComponents.getComponents().isEmpty() || StringUtils.isBlank(accessToken) ||
                StringUtils.isBlank(projectKey));
    }

    private boolean userBelongsToProjectGroups(List<String> groups, String projectKey) {
        if (groups == null) return false;
        return groups.stream()
                .filter(Objects::nonNull)
                .anyMatch(g -> g.toUpperCase().contains("BI-AS-ATLASSIAN-P-" + projectKey.toUpperCase()));
    }
}
