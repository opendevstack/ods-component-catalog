package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.controllers.exceptions.BadConfigurationException;
import org.opendevstack.component_catalog.server.controllers.exceptions.BadRequestException;
import org.opendevstack.component_catalog.server.model.Catalog;
import org.opendevstack.component_catalog.server.mother.CatalogEntityContextMother;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogsApiControllerTest {

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

    @Mock
    private CatalogApiAdapter catalogApiAdapter;

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @InjectMocks
    private CatalogsApiController catalogsApiController;

    @Test
    public void givenAnExistingCatalog_WhenGetSingleCatalog_ThenReturnCatalog() throws InvalidIdException {
        // Given
        var id = "catalogId";
        var communityPagePathAt = mock(BitbucketPathAt.class);
        when(communityPagePathAt.getPathAt()).thenReturn("https://my-project/path_at"); // Required as Context initalizes based on pathAt
        var catalogEntityContext = CatalogEntityContextMother.of(communityPagePathAt);

        when(catalogEntitiesService.getCatalogEntityContext(id)).thenReturn(Optional.of(catalogEntityContext));
        when(catalogServiceAdapter.contributingFileExists(any())).thenReturn(true);

        var catalog = new Catalog();
        catalog.setName("catalog1");

        when(catalogApiAdapter.asCatalog(catalogEntityContext)).thenReturn(catalog);

        // When
        var response = catalogsApiController.getCatalog(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void givenInvalidIdException_WhenGetSingleCatalog_ThenThrowBadRequest() throws InvalidIdException {
        // Given
        var id = "invalidId";

        when(catalogEntitiesService.getCatalogEntityContext(id))
                .thenThrow(new InvalidIdException("bad id"));

        // When / Then
        assertThatThrownBy(() -> catalogsApiController.getCatalog(id))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Invalid catalog id: " + id);
    }

    @Test
    public void givenCatalogDoesNotExist_WhenGetSingleCatalog_ThenThrowBadRequest() throws InvalidIdException {
        // Given
        var id = "nonExistingCatalog";

        when(catalogEntitiesService.getCatalogEntityContext(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThatThrownBy(() -> catalogsApiController.getCatalog(id))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Invalid catalog id.");
    }

    @Test
    public void givenCatalogWithoutContributingFile_WhenGetSingleCatalog_ThenThrowBadConfiguration() throws InvalidIdException {
        // Given
        var id = "catalogId";
        var communityPagePathAt = mock(BitbucketPathAt.class);
        when(communityPagePathAt.getPathAt()).thenReturn("https://my-project/path_at");
        var catalogEntityContext = CatalogEntityContextMother.of(communityPagePathAt);

        when(catalogEntitiesService.getCatalogEntityContext(id))
                .thenReturn(Optional.of(catalogEntityContext));

        when(catalogServiceAdapter.contributingFileExists(id))
                .thenReturn(false);

        var catalog = new Catalog();
        when(catalogApiAdapter.asCatalog(catalogEntityContext)).thenReturn(catalog);

        // When / Then
        assertThatThrownBy(() -> catalogsApiController.getCatalog(id))
                .isInstanceOf(BadConfigurationException.class)
                .hasMessage("Contributing file not found.");
    }

}
