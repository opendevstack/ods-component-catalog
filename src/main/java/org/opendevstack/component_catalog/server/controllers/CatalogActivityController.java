package org.opendevstack.component_catalog.server.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.api.CatalogActivityApi;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogActivityController implements CatalogActivityApi {

    @Override
    public ResponseEntity<List<CatalogActivity>> getCatalogActivityById(String id) {
        return CatalogActivityApi.super.getCatalogActivityById(id);
    }
}
