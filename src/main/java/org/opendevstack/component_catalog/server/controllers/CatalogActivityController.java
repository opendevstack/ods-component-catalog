package org.opendevstack.component_catalog.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.CatalogActivityApi;
import org.opendevstack.component_catalog.server.facade.CatalogActivityFacade;
import org.opendevstack.component_catalog.server.model.PaginatedCatalogActivities;
import org.opendevstack.component_catalog.server.services.common.PaginationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogActivityController implements CatalogActivityApi {

    private final CatalogActivityFacade catalogActivityFacade;

    @Override
    public ResponseEntity<PaginatedCatalogActivities> getCatalogActivitiesById(String catalogId, String sort, String project, String status, Long startDate, Long endDate, Integer page, Integer size) {
        // MaxSize is hardcoded, taking same value than at ProjectComponentsFacade::getAllProjectComponentsMetrics
        // We may think if we want to make this param configurable
        PaginationUtils.validatePagination(page, size, 100);

        var activities = catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate);

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .build()
                .toUriString();

        var paginatedActivities = catalogActivityFacade.paginateCatalogActivities(activities, page, size, baseUrl);

        return ResponseEntity.ok(paginatedActivities);
    }
}
