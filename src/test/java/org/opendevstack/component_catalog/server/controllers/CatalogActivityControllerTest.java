package org.opendevstack.component_catalog.server.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.facade.CatalogActivityFacade;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.model.PaginatedCatalogActivities;
import org.opendevstack.component_catalog.server.model.Pagination;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.model.SortParameter;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogActivityControllerTest {

    private final String catalogId = "catalog-1";
    private final SortParameter sortParameter = SortParameter.CREATION_DATE;
    private final SortOrder sortOrder = SortOrder.ASC;
    private final String project = "PROJECT-1";
    private final ProvisioningStatus status = ProvisioningStatus.CREATED;
    private final Long startDate = 1L;
    private final Long endDate = 2L;
    private final Integer page = 1;
    private final Integer size = 10;

    @Mock
    private CatalogActivityFacade catalogActivityFacade;

    @InjectMocks
    private CatalogActivityController catalogActivityController;

    @BeforeEach
    void setUp() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/project/activities");

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    void givenValidCatalogId_whenGetCatalogActivitiesById_ThenReturnActivitiesList() {
        // Given
        var activity = CatalogActivity.builder()
                .catalogItemSlug("project/repo")
                .componentId("comp-1")
                .projectKey("PK")
                .build();
        var baseUrl = "http://localhost/project/activities";
        var activities = List.of(activity);
        var pagination = Pagination.builder().build();
        var paginatedActivities = PaginatedCatalogActivities.builder()
                .data(activities)
                .pagination(pagination)
                .build();

        when(catalogActivityFacade.getCatalogActivities(
                catalogId,
                sortParameter,
                sortOrder,
                project,
                status,
                startDate,
                endDate
        )).thenReturn(activities);
        when(catalogActivityFacade.paginateCatalogActivities(activities, page, size, baseUrl))
                .thenReturn(paginatedActivities);

        // When
        var response = catalogActivityController.getCatalogActivitiesById(
                catalogId,
                sortParameter,
                sortOrder,
                project,
                status,
                startDate,
                endDate,
                page,
                size
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(paginatedActivities);
    }

    @Test
    void givenFacadeThrowsElementNotFound_whenGetCatalogActivitiesById_ThenPropagateException() {
        // Given
        when(catalogActivityFacade.getCatalogActivities(
                catalogId,
                sortParameter,
                sortOrder,
                project,
                status,
                startDate,
                endDate
        )).thenThrow(new ElementNotFoundException("not found"));

        // When / Then
        assertThatThrownBy(() -> catalogActivityController.getCatalogActivitiesById(
                catalogId,
                sortParameter,
                sortOrder,
                project,
                status,
                startDate,
                endDate,
                page,
                size
        ))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("not found");
    }

}

