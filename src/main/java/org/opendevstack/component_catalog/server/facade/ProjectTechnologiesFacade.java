package org.opendevstack.component_catalog.server.facade;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.ProjectTechnologiesMapper;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
@CacheConfig(cacheNames={ApplicationPropertiesConfiguration.ProjectTechnologiesCacheProps.CACHE_NAME})
public class ProjectTechnologiesFacade {

    private final ProjectComponentsFacade projectComponentsFacade;
    private final AuthenticationFacade authenticationFacade;
    private final ProjectTechnologiesMapper projectTechnologiesMapper;

    @Cacheable(key = "#root.methodName + #projectKey", condition = "@projectTechnologiesCacheConfig.enabled")
    public List<ProjectTechnology> getProjectTechnologies(String projectKey) {
        var accessToken = authenticationFacade.getAccessToken();

        boolean isAValidApplicationToken = authenticationFacade.isAValidApplicationToken(accessToken);

        if (isAValidApplicationToken) {
            var projectComponents = Optional.ofNullable(projectComponentsFacade.getAllProjectComponents(projectKey));

            var projectTechnologies = projectComponents.map(projectTechnologiesMapper::asProjectTechnologies)
                    .orElse(Collections.emptySet());

            log.debug("Retrieved {} technologies for project '{}'", projectTechnologies.size(), projectKey);
            return projectTechnologies.stream().toList();
        } else {
            log.warn("Access denied for user to get technologies for project '{}'. Invalid application token.", projectKey);

            throw new ForbiddenException("Access denied. Invalid application token.");
        }


    }
}
