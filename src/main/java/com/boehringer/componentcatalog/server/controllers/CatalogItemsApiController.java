package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.api.CatalogItemsApi;
import com.boehringer.componentcatalog.server.controllers.exceptions.BadRequestException;
import com.boehringer.componentcatalog.server.controllers.exceptions.RestEntityNotFoundException;
import com.boehringer.componentcatalog.server.controllers.exceptions.InvalidRestEntityException;
import com.boehringer.componentcatalog.server.model.CatalogItem;
import com.boehringer.componentcatalog.server.model.SortOrder;
import com.boehringer.componentcatalog.server.security.AuthorizationInfo;
import com.boehringer.componentcatalog.server.services.CatalogEntitiesService;
import com.boehringer.componentcatalog.server.services.UserActionsEntitiesService;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import com.boehringer.componentcatalog.server.services.catalog.InvalidCatalogEntityException;
import com.boehringer.componentcatalog.server.services.catalog.InvalidCatalogItemEntityException;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityPermissionEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

import static com.boehringer.componentcatalog.server.controllers.CatalogApiAdapter.asCatalogItem;
import static com.boehringer.componentcatalog.server.controllers.CatalogApiAdapter.sortCatalogItems;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogItemsApiController implements CatalogItemsApi {

    private final AuthorizationInfo authInfo;
    private final CatalogEntitiesService catalogEntitiesService;
    private final UserActionsEntitiesService userActionsEntitiesService;

    @Override
    public ResponseEntity<List<CatalogItem>> getCatalogItems(String catalogId, SortOrder sortByTitle) {
        log.debug("User '{}' requested catalog items for catalog id: '{}'",
                authInfo.getCurrentPrincipalName(),
                catalogId);
        try {
            var principalPermissions = this.currentPrincipalCatalogPermissions(catalogId);
            var itemsEntitiesCtxs = this.catalogEntitiesService.getCatalogItemsEntities(catalogId);
            var userActionsEntity = this.userActionsEntitiesService.getDefaultUserActionsEntity();

            var items = itemsEntitiesCtxs.stream()
                    .map(context -> asCatalogItem(context, userActionsEntity, principalPermissions))
                    .toList();

            items = sortCatalogItems(items, sortByTitle);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (InvalidIdException | InvalidCatalogEntityException e) {
            throw new BadRequestException("Invalid catalog id: " + catalogId);
        }
    }

    @Override
    public ResponseEntity<CatalogItem> getCatalogItemById(String id) {
        log.debug("User '{}' requested catalog item with id: '{}'", authInfo.getCurrentPrincipalName(), id);

        try {
            var principalPermissions = this.currentPrincipalCatalogPermissions(id);
            var maybeItemEntityCtx = this.catalogEntitiesService.getCatalogItemEntity(id);
            var userActionsEntity = this.userActionsEntitiesService.getDefaultUserActionsEntity();

            return maybeItemEntityCtx
                    .map(itemEntity -> asCatalogItem(itemEntity, userActionsEntity, principalPermissions))
                    .map(ResponseEntity::ok)
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (InvalidIdException e) {
            throw new RestEntityNotFoundException("Catalog item not found, item id: " + id);
        } catch (InvalidCatalogItemEntityException e) {
            throw new InvalidRestEntityException("Invalid catalog item, item id: " + id);
        }
    }

    private Set<CatalogEntityPermissionEnum> currentPrincipalCatalogPermissions(String id) {
        var principalName = authInfo.getCurrentPrincipalName();

        try {
            return this.catalogEntitiesService.catalogPrincipalPermissions(id, principalName);
        } catch (InvalidIdException e) {
            log.error("Unable to get permissions for: '{}' and resource with id: {}", principalName, id, e);
            return Set.of();
        }
    }

}
