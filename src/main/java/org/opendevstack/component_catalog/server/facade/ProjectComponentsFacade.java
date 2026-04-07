package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentsInfoMapper;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ProjectComponentsFacade {

    private final ProvisionerActionsService provisionerActionsService;
    private final ProjectComponentsInfoMapper projectComponentsInfoMapper;
    private final ProjectsInfoService projectsInfoService;
    private final AuthenticationFacade authenticationFacade;

    public List<ProjectComponentInfo> getProjectComponentsInfo(String projectKey, String accessToken) {
        var projectComponents = provisionerActionsService.getProjectComponents(projectKey);

        if (notValid(projectComponents, projectKey, accessToken)) {
            return Collections.emptyList();
        }

        String idToken = authenticationFacade.getIdToken();
        List<String> userGroups = projectsInfoService.getProjectGroups(idToken, accessToken);

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

    private boolean notValid(ProjectComponents projectComponents, String projectKey, String accessToken) {
        return (projectComponents == null || projectComponents.getComponents() == null ||
                projectComponents.getComponents().isEmpty() || StringUtils.isBlank(accessToken) ||
                StringUtils.isBlank(projectKey));
    }
}
