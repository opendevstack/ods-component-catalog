package org.opendevstack.component_catalog.server.services.catalog;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.services.BitbucketService;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceAdapterTest {

    @Mock
    private BitbucketService bitbucketService;

    @Spy
    @InjectMocks
    private CatalogServiceAdapter catalogServiceAdapter;

    @Test
    void givenAnId_WhenBitbucketAt_thenBitbucketPathIsReturned() throws InvalidIdException {
        // Given
        var path = "/path/to/resource";
        var id = Base64.getUrlEncoder().encodeToString(path.getBytes());
        var builder = mock(BitbucketPathAt.BitbucketPathAtBuilder.class);
        var bitbucketPathAt = mock(BitbucketPathAt.class);

        when(bitbucketService.pathAtBuilder()).thenReturn(builder);
        when(builder.pathAt(path)).thenReturn(builder);
        when(builder.build()).thenReturn(bitbucketPathAt);

        // When
        var result = catalogServiceAdapter.bitbucketPathAtFromId(id);

        // Then
        assertThat(result).isEqualTo(bitbucketPathAt);
    }

    @Test
    void givenABitbucketPath_whenGetCatalogEntity_ThenObjectIsReturned() {
        // given
        var catalogPathAt = mock(BitbucketPathAt.class);
        var yamlContent = "{\"kind\":\"Catalog\"}";
        Pair<MediaType, String> text = Pair.of(MediaType.APPLICATION_JSON, yamlContent);

        when(bitbucketService.getCachedTextFileContents(catalogPathAt)).thenReturn(Optional.of(text));

        // when
        Optional<CatalogEntity> catalogEntity = catalogServiceAdapter.getCatalogEntity(catalogPathAt);

        // then
        assertThat(catalogEntity).isPresent();
        assertThat(catalogEntity.get().getKind()).isEqualTo("Catalog");
    }

    @Test
    void givenValidId_whenContributingReturns2xx_thenTrue() throws InvalidIdException {
        // given
        var id = "valid-id";
        var pathAt = mock(BitbucketPathAt.class);

        doReturn(pathAt).when(catalogServiceAdapter).bitbucketPathAtFromId(id);

        when(bitbucketService.doesContributingFileExists(pathAt)).thenReturn(true);

        // when
        boolean exists = catalogServiceAdapter.contributingFileExists(id);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void givenValidId_whenContributingReturnsNon2xx_thenFalse() throws InvalidIdException {
        // given
        var id = "valid-id";
        var pathAt = mock(BitbucketPathAt.class);

        doReturn(pathAt).when(catalogServiceAdapter).bitbucketPathAtFromId(id);

        when(bitbucketService.doesContributingFileExists(pathAt)).thenReturn(false);

        // when
        boolean exists = catalogServiceAdapter.contributingFileExists(id);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void givenInvalidId_whenBitbucketPathAtThrows_thenFalse() throws InvalidIdException {
        // given
        var id = "invalid-id";
        doThrow(new InvalidIdException("bad id"))
                .when(catalogServiceAdapter).bitbucketPathAtFromId(id);

        // when
        boolean exists = catalogServiceAdapter.contributingFileExists(id);

        // then
        assertThat(exists).isFalse();
    }

}
