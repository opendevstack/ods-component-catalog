package com.boehringer.componentcatalog.server.services.catalog;

import com.boehringer.componentcatalog.server.services.BitbucketService;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import org.springframework.http.MediaType;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceAdapterTest {

    @Mock
    private BitbucketService bitbucketService;

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

        when(bitbucketService.getTextFileContents(catalogPathAt)).thenReturn(Optional.of(text));

        // when
        Optional<String> catalogEntity = catalogServiceAdapter.getCatalogEntity(catalogPathAt, String.class);

        // then
        assertThat(catalogEntity).isPresent();
        assertThat(catalogEntity.get()).isEqualTo(yamlContent);
    }
}
