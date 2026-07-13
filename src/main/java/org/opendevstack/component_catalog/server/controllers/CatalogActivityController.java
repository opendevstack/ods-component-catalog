package org.opendevstack.component_catalog.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.CatalogActivityApi;
import org.opendevstack.component_catalog.server.facade.CatalogActivityFacade;
import org.opendevstack.component_catalog.server.model.PaginatedCatalogActivities;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogActivityController implements CatalogActivityApi {

    private final CatalogActivityFacade catalogActivityFacade;

    @Override
    public ResponseEntity<PaginatedCatalogActivities> getCatalogActivitiesById(String catalogId, String project, String status, Long startDate, Long endDate) {
        var activities = catalogActivityFacade.getCatalogActivities(catalogId, project, status, startDate, endDate);

        var paginatedActivities = PaginatedCatalogActivities.builder()
                .data(activities)
                .build();

        return ResponseEntity.ok(paginatedActivities);
    }
}
