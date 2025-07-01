package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.api.CatalogDescriptorsApi;
import com.boehringer.componentcatalog.server.controllers.exceptions.BadRequestException;
import com.boehringer.componentcatalog.server.model.CatalogDescriptor;
import com.boehringer.componentcatalog.server.services.CatalogsCollectionService;
import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsEntity;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
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

    @Override
    public ResponseEntity<List<CatalogDescriptor>> getCatalogDescriptors() {
        log.debug("Requesting getAllCatalogDescriptors");

        try {
            CatalogsCollectionsEntity catalogsCollection = catalogsCollectionService.getCatalogsCollection()
                    .orElseThrow(() -> new BadRequestException("Invalid catalogs collection id."));

            var catalogs = CatalogApiAdapter.asCatalogDescriptors(catalogsCollection);

            return new ResponseEntity<>(catalogs, HttpStatus.OK);
        } catch (InvalidIdException e) {
            throw new BadRequestException("Invalid catalog of catalogs");
        }
    }
}
