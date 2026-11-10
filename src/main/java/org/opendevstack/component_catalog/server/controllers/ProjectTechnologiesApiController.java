package org.opendevstack.component_catalog.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.ProjectTechnologiesApi;
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

    @Override
    public ResponseEntity<List<ProjectTechnology>> getProjectTechnologies(String projectKey) {
        // Mock technologies, not meant to be used in production, just for testing purposes
        return ResponseEntity.ok(List.of(
                new ProjectTechnology().slug("java").name("Java"),
                new ProjectTechnology().slug("spring-boot").name("Spring Boot"),
                new ProjectTechnology().slug("react").name("React")
        ));
    }
}
