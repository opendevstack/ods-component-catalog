package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.api.CatalogItemsApi;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.controllers.exceptions.InvalidRestEntityException;
import org.opendevstack.component_catalog.server.controllers.exceptions.RestEntityNotFoundException;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogItemsApiController implements CatalogItemsApi {

    private final AuthorizationInfo authInfo;
    private final CatalogItemsApiFacade catalogItemsApiFacade;

    @Override
    public ResponseEntity<List<CatalogItem>> getCatalogItems(String catalogId, SortOrder sortByTitle) {
        log.debug("User '{}' requested catalog items for catalog id: '{}'",
                authInfo.getCurrentPrincipalName(), catalogId);
        try {
            var catalogItemRequestParams = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .sortOrder(sortByTitle)
                    .build();

            var items = catalogItemsApiFacade.fetchCatalogItems(catalogItemRequestParams);
            return ResponseEntity.ok(items);
        } catch (InvalidIdException | InvalidCatalogEntityException e) {
            throw new BadRequestException("Invalid catalog id: " + catalogId);
        }
    }

    @Override
    public ResponseEntity<List<CatalogItem>> getCatalogItemsForProjectKey(String catalogId, String accessToken, SortOrder sortByTitle,
                                                                          String projectKey) {
        log.debug("User '{}' requested catalog items for catalog id and projectKey: '{}', '{}'",
                authInfo.getCurrentPrincipalName(), catalogId, projectKey);
        try {
            var idToken = catalogItemsApiFacade.getIdToken();

            var catalogItemRequestParams = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .sortOrder(sortByTitle)
                    .projectKey(projectKey)
                    .idToken(idToken)
                    .accessToken(accessToken)
                    .build();

            var items = catalogItemsApiFacade.fetchCatalogItems(catalogItemRequestParams);

            return ResponseEntity.ok(items);
        } catch (InvalidIdException | InvalidCatalogEntityException e) {
            throw new BadRequestException("Invalid catalog id: " + catalogId);
        }
    }

    @Override
    public ResponseEntity<CatalogItem> getCatalogItemById(String id) {
        log.debug("User '{}' requested catalog item with id: '{}'", authInfo.getCurrentPrincipalName(), id);
        try {
            var catalogRequestParams = CatalogRequestParams.builder()
                    .catalogItemId(id)
                    .build();
            var catItem = catalogItemsApiFacade.fetchCatalogItem(catalogRequestParams);
            if (catItem == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(catItem);
        } catch (InvalidIdException e) {
            throw new RestEntityNotFoundException("Catalog item not found, item id: " + id);
        } catch (InvalidCatalogItemEntityException e) {
            throw new InvalidRestEntityException("Invalid catalog item, item id: " + id);
        }
    }

    @Override
    public ResponseEntity<CatalogItem> getCatalogItemByIdForProjectKey(String id, String projectKey, String accessToken) {
        log.debug("User '{}' requested catalog item with id and projectKey: '{}', '{}'",
                authInfo.getCurrentPrincipalName(), id, projectKey);
        try {
            var idToken = catalogItemsApiFacade.getIdToken();

            var catalogRequestParams = CatalogRequestParams.builder()
                    .catalogItemId(id)
                    .projectKey(projectKey)
                    .idToken(idToken)
                    .accessToken(accessToken)
                    .build();
            var catItem = catalogItemsApiFacade.fetchCatalogItem(catalogRequestParams);
            if (catItem == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(catItem);
        } catch (InvalidIdException e) {
            throw new RestEntityNotFoundException("Catalog item not found, item id: " + id);
        } catch (InvalidCatalogItemEntityException e) {
            throw new InvalidRestEntityException("Invalid catalog item, item id: " + id);
        }
    }


}

