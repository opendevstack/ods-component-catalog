package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.mother.CatalogsCollectionsEntityMother;
import com.boehringer.componentcatalog.server.services.CatalogsCollectionService;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogDescriptorsApiControllerTest {

    @Mock
    private CatalogsCollectionService catalogsCollectionService;

    @InjectMocks
    private CatalogDescriptorsApiController catalogDescriptorsApiController;

    @Test
    public void givenACatalogCollection_WhenGetCatalogDescriptors_ThenReturnCatalogs() throws InvalidIdException {
        // Given
        var catalogsCollection = CatalogsCollectionsEntityMother.of();

        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(catalogsCollection));

        // When
        var response = catalogDescriptorsApiController.getCatalogDescriptors();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
    }
}
