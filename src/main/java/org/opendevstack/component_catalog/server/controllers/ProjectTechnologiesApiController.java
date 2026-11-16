package org.opendevstack.component_catalog.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.ProjectTechnologiesApi;
import org.opendevstack.component_catalog.server.facade.ProjectTechnologiesFacade;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class ProjectTechnologiesApiController implements ProjectTechnologiesApi {

    private final ProjectTechnologiesFacade projectTechnologiesFacade;

    @Override
    public ResponseEntity<List<ProjectTechnology>> getProjectTechnologies(String projectKey) {
        var projectTechnologies = projectTechnologiesFacade.getProjectTechnologies(projectKey);

        log.debug("User requested technologies for project '{}', returning: {}", projectKey, projectTechnologies);
        return ResponseEntity.ok(projectTechnologies);
    }
}
