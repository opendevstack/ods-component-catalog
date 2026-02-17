package org.opendevstack.component_catalog.server.controllers;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogItemsApiControllerTest {

    private final String token = "any-acess-token";
    private final String catalogId = "catalog123";
    private final String projectKey = "projectKey123";
    private final String principalName = "testUser";
    private final String catalogItemId = "catalogItem123";
    private final String invalidCatalogId = "invalidCatalogId";
    private final String invalidProjectKey = "invalidKey";
    private final String invalidCatalogItemId = "invalidCatalogItemId";

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    private CatalogItemsApiFacade catalogItemsApiFacade;

    @InjectMocks
    private CatalogItemsApiController catalogItemsApiController;

    @Test
    void givenValidCatalogId_WhenGetCatalogItems_ThenReturnItemsList() throws InvalidIdException {

        var item = new CatalogItem();
        item.setId("item-1");
        item.setTitle("Item 1");

        when(catalogItemsApiFacade.fetchCatalogItems(any())).thenReturn(List.of(item));

        // When
        var response = catalogItemsApiController.getCatalogItems(catalogId, SortOrder.ASC);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isEqualTo(1);

    }

    @Test
    void givenInvalidCatalogId_WhenGetCatalogItems_ThenThrowBadRequestException() throws InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn("testUser");
        when(catalogItemsApiFacade.fetchCatalogItems(any())).thenThrow(new InvalidIdException("Invalid ID"));

        // When / Then
        assertThatThrownBy(() -> catalogItemsApiController.getCatalogItems(invalidCatalogId, SortOrder.ASC))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Invalid catalog id");
    }

    @Test
    void givenEmptyResult_WhenGetCatalogItems_ThenReturnEmptyList() {
        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);

        // When
        var response = catalogItemsApiController.getCatalogItems(catalogId, SortOrder.ASC);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void givenValidProjectKey_WhenGetCatalogItemsForProjectKey_ThenReturnItemsList() throws InvalidIdException, InvalidCatalogEntityException {
        var item = new CatalogItem();
        item.setId("item-1");
        item.setTitle("Item 1");

        when(catalogItemsApiFacade.fetchCatalogItems(any())).thenReturn(List.of(item));

        // When
        var response = catalogItemsApiController.getCatalogItemsForProjectKey(catalogId, token, SortOrder.ASC, projectKey);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isEqualTo(1);

    }

    @Test
    void givenInvalidProjectKey_WhenGetCatalogItemsForProjectKey_ThenThrowBadRequestException() throws InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn("testUser");
        when(catalogItemsApiFacade.fetchCatalogItems(any())).thenThrow(new InvalidIdException("Invalid ID"));

        // When / Then
        assertThatThrownBy(() -> catalogItemsApiController.getCatalogItemsForProjectKey(catalogId, token, SortOrder.ASC, invalidProjectKey))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Invalid catalog id");
    }

    @Test
    void givenEmptyResult_WhenGetCatalogItemsForProjectKey_ThenReturnEmptyList() throws InvalidCatalogEntityException {
        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);

        // When
        var response = catalogItemsApiController.getCatalogItemsForProjectKey(catalogId, token, SortOrder.ASC, projectKey);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void givenValidCatalogItemId_WhenGetCatalogItemById_ThenReturnCatalogItem() throws InvalidIdException, InvalidCatalogEntityException {

        // Given
        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);

        var catalogItem = new CatalogItem();
        catalogItem.setId(catalogItemId);
        CatalogRequestParams catalogRequestParams = CatalogRequestParams.builder().catalogItemId(catalogItemId).build();
        new ResponseEntity<>(HttpStatus.OK);

        when(catalogItemsApiFacade.fetchCatalogItem(catalogRequestParams)).thenReturn(catalogItem);


        // When
        var response = catalogItemsApiController.getCatalogItemById(catalogItemId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void givenInvalidCatalogItemId_WhenGetCatalogItemById_ThenThrowRestEntityNotFoundException() throws InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn("testUser");
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenThrow(new InvalidIdException("Invalid ID"));

        // When / Then
        assertThatThrownBy(() -> catalogItemsApiController.getCatalogItemById(invalidCatalogItemId))
                .isInstanceOf(RestEntityNotFoundException.class)
                .hasMessageContaining("Catalog item not found");
    }

    @Test
    void givenInvalidCatalogItemEntity_WhenGetCatalogItemById_ThenThrowInvalidRestEntityException()
            throws InvalidCatalogEntityException, InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn("testUser");
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenThrow(new InvalidCatalogItemEntityException("Invalid ID"));

        // When / Then
        assertThatThrownBy(() -> catalogItemsApiController.getCatalogItemById(invalidCatalogItemId))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("Invalid catalog item");
    }

    @Test
    void givenCatalogItemNotFound_WhenGetCatalogItemById_ThenReturnNotFound() throws InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);
        when(catalogItemsApiFacade.fetchCatalogItem(any())).thenReturn(null);
        // When
        var response = catalogItemsApiController.getCatalogItemById(catalogItemId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void givenValidCatalogId_WhenGetCatalogItemByIdForProjectKey_ThenReturnItem() throws InvalidIdException, InvalidCatalogItemEntityException {
        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);
        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setId(catalogItemId);
        when(catalogItemsApiFacade.fetchCatalogItem(any())).thenReturn(catalogItem);

        // When
        var response = catalogItemsApiController.getCatalogItemByIdForProjectKey(catalogId, projectKey, token);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void givenInvalidCatalogId_WhenGetCatalogItemByIdForProjectKey_ThenThrowRestEntityNotFoundException() throws InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn("testUser");
        when(catalogItemsApiFacade.fetchCatalogItem(any())).thenThrow(new InvalidIdException("Invalid ID"));

        // When / Then
        assertThatThrownBy(() -> catalogItemsApiController.getCatalogItemByIdForProjectKey(invalidCatalogId, projectKey, token))
                .isInstanceOf(RestEntityNotFoundException.class)
                .hasMessageContaining("Catalog item not found");
    }

    @Test
    void givenInvalidCatalogItemEntity_WhenGetCatalogItemByIdForProjectKey_ThenThrowInvalidRestEntityException()
            throws InvalidCatalogItemEntityException, InvalidIdException {
        when(authInfo.getCurrentPrincipalName()).thenReturn("testUser");
        when(catalogItemsApiFacade.fetchCatalogItem(any())).thenThrow(new InvalidCatalogItemEntityException("Invalid ID"));


        // When / Then
        assertThatThrownBy(() -> catalogItemsApiController.getCatalogItemByIdForProjectKey(catalogId, projectKey, token))
                .isInstanceOf(InvalidRestEntityException.class)
                .hasMessageContaining("Invalid catalog item");
    }

    @Test
    void givenCatalogItemNotFound_WhenGetCatalogItemByIdForProjectKey_ThenReturnNotFound() throws InvalidIdException, InvalidCatalogItemEntityException {
        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);
        when(catalogItemsApiFacade.fetchCatalogItem(any())).thenReturn(null);

        // When
        var response = catalogItemsApiController.getCatalogItemByIdForProjectKey(catalogId, projectKey, token);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

}