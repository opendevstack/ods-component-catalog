package org.opendevstack.component_catalog.server.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.CacheAdministrationApi;
import org.opendevstack.component_catalog.server.services.cache.CacheAdministrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@RequiredArgsConstructor
@Slf4j
public class CacheAdministrationApiController implements CacheAdministrationApi {

    private final CacheAdministrationService cacheAdministrationService;

    @Override
    public ResponseEntity<Void> refreshCache(String cache) {
        log.info("Trying to refresh cache {}", cache);

        cacheAdministrationService.refreshCache(cache);
        return ResponseEntity.accepted().build();
    }
}
