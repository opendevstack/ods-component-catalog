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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogActivityControllerTest {

    private final String catalogId = "catalog-1";
    private final String sort = "creationDate";
    private final String project = "PROJECT-1";
    private final String status = "CREATED";
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
        when(catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate)).thenReturn(List.of(activity));

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, sort, project, status, startDate, endDate);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).isNotNull();
        assertThat(response.getBody().getData()).hasSize(1);
        assertThat(response.getBody().getData().getFirst().getCatalogItemSlug()).isEqualTo("project/repo");
    }

    @Test
    void givenEmptyResult_whenGetCatalogActivitiesById_ThenReturnEmptyList() {
        // Given
        when(catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate)).thenReturn(List.of());

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, sort, project, status, startDate, endDate);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).isEmpty();
    }

    @Test
    void givenFacadeThrowsElementNotFound_whenGetCatalogActivitiesById_ThenPropagateException() {
        // Given
        when(catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate)).thenThrow(new ElementNotFoundException("not found"));

        // When / Then
        assertThatThrownBy(() -> catalogActivityController.getCatalogActivitiesById(catalogId, sort, project, status, startDate, endDate))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void givenValidParameters_whenGetCatalogActivitiesById_ThenReturnPaginatedResponse() {
        // Given
        var activity1 = CatalogActivity.builder()
                .catalogItemSlug("project/repo1")
                .componentId("comp-1")
                .projectKey("PK1")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();
        var activity2 = CatalogActivity.builder()
                .catalogItemSlug("project/repo2")
                .componentId("comp-2")
                .projectKey("PK2")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();
        when(catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate))
                .thenReturn(List.of(activity1, activity2));

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, sort, project, status, startDate, endDate);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).hasSize(2);
        assertThat(response.getBody().getData()).containsExactly(activity1, activity2);
    }

    @Test
    void givenNullFilters_whenGetCatalogActivitiesById_ThenReturnAllActivities() {
        // Given
        var activity = CatalogActivity.builder()
                .catalogItemSlug("project/repo")
                .componentId("comp-1")
                .projectKey("PK")
                .build();
        when(catalogActivityFacade.getCatalogActivities(catalogId, null, null, null, null, null))
                .thenReturn(List.of(activity));

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, null, null, null, null, null);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).hasSize(1);
    }

    @Test
    void givenSortParameter_whenGetCatalogActivitiesById_ThenCallFacadeWithSortParameter() {
        // Given
        var activity = CatalogActivity.builder()
                .catalogItemSlug("project/repo")
                .componentId("comp-1")
                .projectKey("PK")
                .build();
        when(catalogActivityFacade.getCatalogActivities(catalogId, "project", project, status, startDate, endDate))
                .thenReturn(List.of(activity));

        // When
        var response = catalogActivityController.getCatalogActivitiesById(catalogId, "project", project, status, startDate, endDate);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).hasSize(1);
        verify(catalogActivityFacade, times(1))
                .getCatalogActivities(catalogId, "project", project, status, startDate, endDate);
    }
}

