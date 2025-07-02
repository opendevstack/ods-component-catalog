package com.boehringer.componentcatalog.server.services;

import com.boehringer.componentcatalog.server.mother.BitbucketPathAtMother;
import com.boehringer.componentcatalog.server.mother.CatalogEntityMother;
import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogServiceAdapter;
import com.boehringer.componentcatalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogEntitiesServiceTest {

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @InjectMocks
    private CatalogEntitiesService catalogEntitiesService;

    @Test
    public void givenAnId_whenGetCatalogEntity_thenReturnCatalogEntity() throws InvalidIdException {
        // Given
        var id = "aSdFam...yCg==";
        var bitbucketPathAt = mock(BitbucketPathAt.class);
        var catalogEntity = CatalogEntityMother.of();

        when(catalogServiceAdapter.bitbucketPathAtFromId(id)).thenReturn(bitbucketPathAt);

        when(catalogServiceAdapter.getCatalogEntity(bitbucketPathAt, CatalogEntity.class))
                .thenReturn(Optional.of(catalogEntity));

        // When
        var result = catalogEntitiesService.getCatalogEntity(id);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(catalogEntity);
    }

    @Test
    public void givenACatalogId_whenGetCatalogItemsEntities_thenReturnCatalogItemEntityContextList() throws InvalidIdException {
        // given
        var catalogId = "aSdFam...yCg==";
        var catalogIdPathAt = mock(BitbucketPathAt.class);
        var repoCatalog = CatalogEntityMother.of();

        when(catalogServiceAdapter.bitbucketPathAtFromId(catalogId)).thenReturn(catalogIdPathAt);
        when(catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogEntity.class)).thenReturn(Optional.of(repoCatalog));

        // when
        var result = catalogEntitiesService.getCatalogItemsEntities("aSdFam...yCg==");

        // then
        assertThat(result).isNotNull();
    }

    @Test
    public void givenACatalogId_whenGetCatalogEntityContext_thenContextContainsCommunityPage() throws InvalidIdException {
        // given
        var catalogId = "aSdFam...yCg==";
        var catalogIdPathAt = BitbucketPathAtMother.of();
        var catalogItemEntity = CatalogEntityMother.of();

        when(catalogServiceAdapter.bitbucketPathAtFromId(catalogId)).thenReturn(catalogIdPathAt);
        when(catalogServiceAdapter.getCatalogEntity(catalogIdPathAt, CatalogEntity.class)).thenReturn(Optional.of(catalogItemEntity));

        // when
        var result = catalogEntitiesService.getCatalogEntityContext(catalogId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getCommunityPagePath()).isEqualTo("projects/MYPROJECT/repos/repo-slug/raw/some-package/community.md?at=refs/heads/master");
    }
}
