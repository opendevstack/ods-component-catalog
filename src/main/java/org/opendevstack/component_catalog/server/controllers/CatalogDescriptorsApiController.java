package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.api.CatalogDescriptorsApi;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.model.CatalogDescriptor;
import org.opendevstack.component_catalog.server.services.CatalogsCollectionService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogDescriptorsApiController implements CatalogDescriptorsApi {

    private final CatalogsCollectionService catalogsCollectionService;
    private final CatalogApiAdapter catalogApiAdapter;

    @Override
    public ResponseEntity<List<CatalogDescriptor>> getCatalogDescriptors() {
        log.debug("Requesting getAllCatalogDescriptors");

        try {
            CatalogsCollectionsEntity catalogsCollection = catalogsCollectionService.getCatalogsCollection()
                    .orElseThrow(() -> new BadRequestException("Invalid catalogs collection id."));

            var catalogs = catalogApiAdapter.asCatalogDescriptors(catalogsCollection);

            return new ResponseEntity<>(catalogs, HttpStatus.OK);
        } catch (InvalidIdException e) {
            throw new BadRequestException("Invalid catalog of catalogs");
        }
    }
}
