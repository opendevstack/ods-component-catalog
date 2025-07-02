package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.api.CatalogsApi;
import com.boehringer.componentcatalog.server.controllers.exceptions.BadRequestException;
import com.boehringer.componentcatalog.server.model.Catalog;
import com.boehringer.componentcatalog.server.services.CatalogEntitiesService;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
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

    @Override
    public ResponseEntity<Catalog> getCatalog(String id) {
        log.debug("Requesting getSingleCatalog with id: {}", id);

        try {
            var catalogEntity = catalogEntitiesService.getCatalogEntityContext(id);
            var catalog = CatalogApiAdapter.asCatalog(catalogEntity.orElseThrow(() -> new BadRequestException("Invalid catalog id.")));

            return new ResponseEntity<>(catalog, HttpStatus.OK);
        } catch (InvalidIdException e) {
            throw new BadRequestException("Invalid catalog id: " + id);
        }
    }
}
