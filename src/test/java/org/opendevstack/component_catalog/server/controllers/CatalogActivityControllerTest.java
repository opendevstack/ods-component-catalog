package org.opendevstack.component_catalog.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.facade.CatalogActivityFacade;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogActivityControllerTest {

    private final String catalogId = "catalog-1";
    private final String projectKey = "project-1";
    private final String status = "component-1";
    private final Long startDate = 1L;
    private final Long endDate = 2L;

    @Mock
    private CatalogActivityFacade catalogActivityFacade;

    @InjectMocks
    private CatalogActivityController catalogActivityController;

    @Test
    void givenValidCatalogId_whenGetCatalogActivitiesById_ThenReturnActivitiesList() {
        // Given
        var activity = CatalogActivity.builder().catalogItemSlug("project/repo").componentId("comp-1").projectKey("PK").build();
        when(catalogActivityFacade.getCatalogActivities(catalogId)).thenReturn(List.of(activity));

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, projectKey, status, startDate, endDate);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().getFirst().getCatalogItemSlug()).isEqualTo("project/repo");
    }

    @Test
    void givenEmptyResult_whenGetCatalogActivitiesById_ThenReturnEmptyList() {
        // Given
        when(catalogActivityFacade.getCatalogActivities(catalogId)).thenReturn(List.of());

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, projectKey, status, startDate, endDate);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void givenFacadeThrowsElementNotFound_whenGetCatalogActivitiesById_ThenPropagateException() {
        // Given
        when(catalogActivityFacade.getCatalogActivities(catalogId)).thenThrow(new ElementNotFoundException("not found"));

        // When / Then
        assertThatThrownBy(() -> catalogActivityController.getCatalogActivitiesById(catalogId, projectKey, status, startDate, endDate))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("not found");
    }

}

