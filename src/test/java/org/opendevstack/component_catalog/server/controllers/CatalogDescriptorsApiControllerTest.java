package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.model.CatalogDescriptor;
import org.opendevstack.component_catalog.server.mother.CatalogsCollectionsEntityMother;
import org.opendevstack.component_catalog.server.services.CatalogsCollectionService;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogDescriptorsApiControllerTest {

    @Mock
    private CatalogsCollectionService catalogsCollectionService;

    @Mock
    private CatalogApiAdapter catalogApiAdapter;

    @InjectMocks
    private CatalogDescriptorsApiController catalogDescriptorsApiController;

    @Test
    public void givenACatalogCollection_WhenGetCatalogDescriptors_ThenReturnCatalogs() throws InvalidIdException {
        // Given
        var catalogsCollection = CatalogsCollectionsEntityMother.of();
        when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.of(catalogsCollection));

        List<CatalogDescriptor> catalogs = new ArrayList<>();
        CatalogDescriptor catalog1 = new CatalogDescriptor();
        catalog1.setId("catalog1");
        catalogs.add(catalog1);
        CatalogDescriptor catalog2 = new CatalogDescriptor();
        catalog2.setId("catalog2");
        catalogs.add(catalog2);

        when(catalogApiAdapter.asCatalogDescriptors(catalogsCollection)).thenReturn(catalogs);

        // When
        var response = catalogDescriptorsApiController.getCatalogDescriptors();

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
    }
}
