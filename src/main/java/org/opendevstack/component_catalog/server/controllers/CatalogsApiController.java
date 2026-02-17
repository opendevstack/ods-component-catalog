package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.api.CatalogsApi;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadConfigurationException;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.model.Catalog;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogsApiController implements CatalogsApi {

    private final CatalogEntitiesService catalogEntitiesService;
    private final CatalogApiAdapter catalogApiAdapter;
    private final CatalogServiceAdapter catalogServiceAdapter;

    @Override
    public ResponseEntity<Catalog> getCatalog(String catalogId) {
        log.debug("Requesting getSingleCatalog with id: {}", catalogId);

        try {
            var catalogEntity = catalogEntitiesService.getCatalogEntityContext(catalogId);
            var catalog = catalogApiAdapter.asCatalog(catalogEntity.orElseThrow(() -> new BadRequestException("Invalid catalog id.")));
            if (catalogServiceAdapter.contributingFileExists(catalogId)) {
                return new ResponseEntity<>(catalog, HttpStatus.OK);
            } else {
                throw new BadConfigurationException("Contributing file not found.");
            }
        } catch (InvalidIdException e) {
            throw new BadRequestException("Invalid catalog id: " + catalogId);
        }
    }
}
