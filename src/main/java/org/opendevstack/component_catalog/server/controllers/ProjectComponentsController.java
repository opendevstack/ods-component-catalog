package org.opendevstack.component_catalog.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.ProjectComponentsApi;
import org.opendevstack.component_catalog.server.facade.AuthenticationFacade;
import org.opendevstack.component_catalog.server.facade.ProjectComponentsFacade;
import org.opendevstack.component_catalog.server.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
@Validated
public class ProjectComponentsController implements ProjectComponentsApi {
    private final ProjectComponentsFacade projectComponentsFacade;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public ResponseEntity<List<ProjectComponentInfo>> getProjectComponents(String projectKey) {
        var accessToken = authenticationFacade.getAccessToken();

        var componentInfos = Optional
                .ofNullable(projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken))
                .orElse(List.of());
        return ResponseEntity.ok(componentInfos);
    }

    @Override
    public ResponseEntity<ProjectComponentExtendedInfo> getProjectComponentById(String projectKey, String componentId) {
        var accessToken = authenticationFacade.getAccessToken();
        var projectComponent = projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, componentId, accessToken);
        return ResponseEntity.ok(projectComponent);
    }

    @Override
    public ResponseEntity<ProjectComponentsMetrics> getAllProjectComponents(Integer page, Integer size) {
        var accessToken = authenticationFacade.getAccessToken();

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUriString();

        var response = projectComponentsFacade.getAllProjectComponentsMetrics(accessToken, page, size, baseUrl);
        return ResponseEntity.ok(response);
    }
}
