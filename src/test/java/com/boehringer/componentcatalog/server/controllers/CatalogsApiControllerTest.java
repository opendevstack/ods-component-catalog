package com.boehringer.componentcatalog.server.controllers;

import com.boehringer.componentcatalog.server.mother.CatalogEntityContextMother;
import com.boehringer.componentcatalog.server.services.CatalogEntitiesService;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogsApiControllerTest {

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

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

        // When
        var response = catalogsApiController.getCatalog(id);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
