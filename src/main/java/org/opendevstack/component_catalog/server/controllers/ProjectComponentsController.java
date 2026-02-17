package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.api.ProjectComponentsApi;
import org.opendevstack.component_catalog.server.facade.ProjectComponentsFacade;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
@Validated
public class ProjectComponentsController implements ProjectComponentsApi {
    private final ProjectComponentsFacade projectComponentsFacade;

    @Override
    public ResponseEntity<List<ProjectComponentInfo>> getProjectComponents(String projectKey, String accessToken) {
        var componentInfos = Optional
                .ofNullable(projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken))
                .orElse(List.of());
        return ResponseEntity.ok(componentInfos);
    }
}
