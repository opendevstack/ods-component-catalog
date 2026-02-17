package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.api.CatalogFiltersApi;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.UserActionsEntitiesService;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("${openapi.componentCatalogREST.base-path:/v1}")
@AllArgsConstructor
@Slf4j
public class CatalogFiltersApiController implements CatalogFiltersApi {

    private final NativeWebRequest request;
    private final AuthorizationInfo authInfo;
    private final CatalogEntitiesService catalogEntitiesService;
    private final UserActionsEntitiesService userActionsEntitiesService;
    private final CatalogItemsApiFacade catalogItemsApiFacade;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<CatalogItemFilter>> getCatalogFilters(String catalogId) {
        log.debug("User '{} [{}]' requested catalog item filters for catalog id: '{}'",
                authInfo.getCurrentName(),
                authInfo.getCurrentPrincipalName(),
                catalogId);
        try {
            var principalPermissions = this.currentPrincipalCatalogPermissions(catalogId);
            var maybeCatalogEntity = this.catalogEntitiesService.getCatalogEntity(catalogId);
            var userActionsEntity = this.userActionsEntitiesService.getDefaultUserActionsEntity();

            // The provided catalog id should be valid and associated to an existing and well-formed catalog
            if(maybeCatalogEntity.isEmpty()) {
                throw new BadRequestException("Invalid catalog id: " + catalogId);
            }

            var catalogItemsEntities = this.catalogEntitiesService.getCatalogItemsEntities(catalogId);

            var catalogRequestParams = CatalogRequestParams.builder()
                    .catalogEntity(maybeCatalogEntity.get())
                    .catalogItemEntityContextList(catalogItemsEntities)
                    .userActionsEntity(userActionsEntity)
                    .permissions(principalPermissions)
                    .build();

            var catalogItemsFilters = catalogItemsApiFacade.catalogItemFiltersFrom(catalogRequestParams);

            return new ResponseEntity<>(catalogItemsFilters, HttpStatus.OK);
        } catch (InvalidIdException | InvalidCatalogEntityException e) {
            throw new BadRequestException("Invalid catalog id: " + catalogId);
        }
    }

    private Set<CatalogEntityPermissionEnum> currentPrincipalCatalogPermissions(String id) throws InvalidIdException {
        return this.catalogEntitiesService.catalogPrincipalPermissions(id, authInfo.getCurrentPrincipalName());
    }

}
