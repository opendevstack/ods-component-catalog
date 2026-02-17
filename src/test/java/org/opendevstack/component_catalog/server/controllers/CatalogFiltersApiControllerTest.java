package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.model.CatalogItemFilterMother;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.UserActionsEntitiesService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogFiltersApiControllerTest {

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

    @Mock
    private UserActionsEntitiesService userActionsEntitiesService;

    @Mock
    private CatalogItemsApiFacade catalogItemsApiFacade;

    @InjectMocks
    private CatalogFiltersApiController catalogFiltersApiController;

    @Test
    void givenACatalogId_andAnAccessToken_whenGetCatalogFilters_thenReturnFilters() throws InvalidIdException {
        // given
        var catalogId = "catalogId";

        var userName = "userName";
        var currentPrincipalName = "currentPrincipalName";

        Set<CatalogEntityPermissionEnum> principalPermissions = Collections.emptySet();
        List<CatalogItemEntityContext> catalogItemsEntities = Collections.emptyList();

        var catalogEntity = CatalogEntityMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        var catalogItemFilter =  CatalogItemFilterMother.of();

        List<CatalogItemFilter> catalogItemFilters = List.of(catalogItemFilter);

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogEntity(catalogEntity)
                .catalogItemEntityContextList(catalogItemsEntities)
                .userActionsEntity(userActionsEntity)
                .permissions(principalPermissions)
                .build();

        when(authInfo.getCurrentName()).thenReturn(userName);
        when(authInfo.getCurrentPrincipalName()).thenReturn(currentPrincipalName);

        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(catalogEntity));
        when(catalogEntitiesService.catalogPrincipalPermissions(catalogId, currentPrincipalName)).thenReturn(principalPermissions);
        when(catalogEntitiesService.getCatalogItemsEntities(catalogId)).thenReturn(catalogItemsEntities);
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(userActionsEntity);
        when(catalogItemsApiFacade.catalogItemFiltersFrom(catalogRequestParams)).thenReturn(catalogItemFilters);

        // when
        var catalogFiltersResponse = catalogFiltersApiController.getCatalogFilters(catalogId);

        // then
        assertThat(catalogFiltersResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(catalogFiltersResponse.getBody()).isEqualTo(catalogItemFilters);
    }
}
