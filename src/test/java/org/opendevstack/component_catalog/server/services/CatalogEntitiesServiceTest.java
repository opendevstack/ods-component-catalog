package org.opendevstack.component_catalog.server.services;

import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.mother.CatalogItemEntityMetadataMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogEntitiesServiceTest {

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @InjectMocks
    private CatalogEntitiesService catalogEntitiesService;

    @Test
    void givenAnId_whenGetCatalogEntity_thenReturnCatalogEntity() throws InvalidIdException {
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
        assertThat(result).isPresent()
                .contains(catalogEntity);
    }

    @Test
    void givenACatalogId_whenGetCatalogItemsEntities_thenReturnCatalogItemEntityContextList() throws InvalidIdException {
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
    void givenACatalogId_whenGetCatalogEntityContext_thenContextContainsCommunityPage() throws InvalidIdException {
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

    @Test
    void givenACatalogItemEntityMetadata_whenBuildCodeowners_thenReturnListOfCodeowners() throws InvalidEntityException {
        // Given
        var metadata = CatalogItemEntityMetadataMother.of();
        var pathAt = mock(BitbucketPathAt.class);
        var firstUserEmailName = "any.user";
        var secondUserEmailName = "another.user";
        var thirdUserEmailName = "third.user";

        var firstUserEmail = firstUserEmailName + "@my-domain.com";
        var secondUserEmail = secondUserEmailName + "@example.com";
        var thirdUserEmail = thirdUserEmailName + "@free-domain.com";
        var baseText = "** " + firstUserEmail + System.lineSeparator()
                + ".gitignore " + secondUserEmail + System.lineSeparator()
                + "* " + secondUserEmail + " " + firstUserEmail + " " + thirdUserEmail;

        Optional<String> textContentsAtPathAt = Optional.of(baseText);

        when(pathAt.copy()).thenReturn(pathAt);
        when(pathAt.appendSubPath("CODEOWNERS")).thenReturn(pathAt);
        when(catalogServiceAdapter.textContentsAtPath(pathAt)).thenReturn(textContentsAtPathAt);

        // When
        var codeowners = catalogEntitiesService.buildCodeowners(metadata, pathAt);

        // Then
        assertThat(codeowners).isNotEmpty();
        assertThat(codeowners.get()).hasSize(3);
        assertThat(codeowners.get()).containsAll(List.of(firstUserEmailName, secondUserEmailName, thirdUserEmailName));
    }
}
