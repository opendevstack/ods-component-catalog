package org.opendevstack.component_catalog.server.services.catalog;

import org.opendevstack.component_catalog.client.bitbucket.v89.api.ProjectApi;
import org.opendevstack.component_catalog.server.services.BitbucketService;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceAdapterTest {

    @Mock
    private BitbucketService bitbucketService;

    @Mock
    private ProjectApi projectApi;

    @Spy
    @InjectMocks
    private CatalogServiceAdapter catalogServiceAdapter;

    @Test
    public void givenAnId_WhenBitbucketAt_thenBitbucketPathIsReturned() throws InvalidIdException {
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
    public void givenABitbucketPath_whenGetCatalogEntity_ThenObjectIsReturned() {
        // given
        var catalogPathAt = mock(BitbucketPathAt.class);
        var yamlContent = "yaml content";
        Pair<MediaType, String> text = Pair.of(MediaType.APPLICATION_JSON, yamlContent);

        when(bitbucketService.getCachedTextFileContents(catalogPathAt)).thenReturn(Optional.of(text));

        // when
        Optional<String> catalogEntity = catalogServiceAdapter.getCatalogEntity(catalogPathAt, String.class);

        // then
        assertThat(catalogEntity).isPresent();
        assertThat(catalogEntity.get()).isEqualTo(yamlContent);
    }

    @Test
    public void givenValidId_whenContributingReturns2xx_thenTrue() throws InvalidIdException {
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
    public void givenValidId_whenContributingReturnsNon2xx_thenFalse() throws InvalidIdException {
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
    public void givenInvalidId_whenBitbucketPathAtThrows_thenFalse() throws InvalidIdException {
        // given
        var id = "invalid-id";
        doThrow(new InvalidIdException("bad id"))
                .when(catalogServiceAdapter).bitbucketPathAtFromId(id);

        // when
        boolean exists = catalogServiceAdapter.contributingFileExists(id);

        // then
        assertThat(exists).isFalse();
        verifyNoInteractions(projectApi);
    }

}
