package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.mother.BitbucketPathAtMother;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogEntitiesServiceTest {

    @Mock
    private BitbucketService bitbucketService;

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    private CatalogEntitiesService catalogEntitiesService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        catalogEntitiesService = new CatalogEntitiesService(bitbucketService, catalogServiceAdapter, ".bitbucket/CODEOWNERS");
    }

    @Test
    void givenAnId_whenGetCatalogEntity_thenReturnCatalogEntity() throws InvalidIdException {
        // Given
        var id = "aSdFam...yCg==";
        var bitbucketPathAt = mock(BitbucketPathAt.class);
        var catalogEntity = CatalogEntityMother.of();

        when(catalogServiceAdapter.bitbucketPathAtFromId(id)).thenReturn(bitbucketPathAt);

        when(catalogServiceAdapter.getCatalogEntity(bitbucketPathAt))
                .thenReturn(Optional.of(catalogEntity));

        // When
        var result = catalogEntitiesService.getCatalogEntity(id);

        // Then
        assertThat(result)
                .isPresent()
                .contains(catalogEntity);
    }

    @Test
    void givenACatalogId_whenGetCatalogItemsEntities_thenReturnCatalogItemEntityContextList()
            throws InvalidIdException {
        // given
        var catalogId = "aSdFam...yCg==";
        var catalogIdPathAt = mock(BitbucketPathAt.class);
        var repoCatalog = CatalogEntityMother.of();

        when(catalogServiceAdapter.bitbucketPathAtFromId(catalogId)).thenReturn(catalogIdPathAt);
        when(catalogServiceAdapter.getCatalogEntity(catalogIdPathAt)).thenReturn(Optional.of(repoCatalog));

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
        when(catalogServiceAdapter.getCatalogEntity(catalogIdPathAt)).thenReturn(Optional.of(catalogItemEntity));

        // when
        var result = catalogEntitiesService.getCatalogEntityContext(catalogId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getCommunityPagePath())
                .isEqualTo("projects/MYPROJECT/repos/repo-slug/raw/some-package/community.md?at=refs/heads/master");
    }

    @Test
    void givenACatalogItemEntityMetadata_whenBuildCodeowners_thenReturnListOfCodeowners()
            throws InvalidEntityException {
        // Given
        var pathAt = mock(BitbucketPathAt.class);
        var firstUserEmailName = "any.user";
        var secondUserEmailName = "another.user";
        var thirdUserEmailName = "third.user";

        Optional<String> textContentsAtPathAt = getTextContentsAtPathAt(firstUserEmailName, secondUserEmailName, thirdUserEmailName);

        when(pathAt.copy()).thenReturn(pathAt);
        when(pathAt.appendSubPath(".bitbucket/CODEOWNERS")).thenReturn(pathAt);
        when(catalogServiceAdapter.textContentsAtPath(pathAt)).thenReturn(textContentsAtPathAt);

        // When
        var codeowners = catalogEntitiesService.buildCodeowners(pathAt);

        // Then
        assertThat(codeowners).isNotEmpty()
                .hasSize(3)
                .containsAll(List.of(firstUserEmailName, secondUserEmailName, thirdUserEmailName));
    }

    @NonNull
    private static Optional<String> getTextContentsAtPathAt(String firstUserEmailName, String secondUserEmailName, String thirdUserEmailName) {
        var firstUserEmail = firstUserEmailName + "@my-domain.com";
        var secondUserEmail = secondUserEmailName + "@example.com";
        var thirdUserEmail = "@" + thirdUserEmailName + "@free-domain.com";
        var baseText = "** " + firstUserEmail + System.lineSeparator()
                + ".gitignore " + secondUserEmail + System.lineSeparator()
                + "* " + secondUserEmail + " " + firstUserEmail + " " + thirdUserEmail;

        return Optional.of(baseText);
    }

    @Test
    void GivenCatalogItemEntityContext_whenCatalogExists_ThenReturnCatalogEntity() {
        //given
        var originalPathAt = BitbucketPathAtMother.of();

        var ctx = CatalogItemEntityContext.builder()
                .id("some-id")
                .repoCatalogItemPathAt(originalPathAt)
                .build();

        var expectedCatalog = CatalogEntityMother.of();

        when(catalogServiceAdapter.getCatalogEntity(any(BitbucketPathAt.class)))
                .thenReturn(Optional.of(expectedCatalog));

        //when
        var maybeCatalog = catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(ctx);

        //then
        assertThat(maybeCatalog).isPresent();
        assertThat(maybeCatalog.get()).isEqualTo(expectedCatalog);

        ArgumentCaptor<BitbucketPathAt> captor = ArgumentCaptor.forClass(BitbucketPathAt.class);
        verify(catalogServiceAdapter).getCatalogEntity(captor.capture());
        var captured = captor.getValue();

        assertThat(captured.getBaseRawUrl()).isEqualTo(originalPathAt.getBaseRawUrl());
        assertThat(captured.getBaseRestUrl()).isEqualTo(originalPathAt.getBaseRestUrl());
        assertThat(captured.getProjectKey()).isEqualTo(originalPathAt.getProjectKey());
        assertThat(captured.getAt()).isEqualTo(originalPathAt.getAt());
        assertThat(captured.getRepoSlug()).isEqualTo("catalog");
        assertThat(captured.getSubPath()).isEqualTo("Catalog.yaml");
    }

    @Test
    void GivenCatalogItemEntityContext_whenCatalogParsingFails_ThenThrowInvalidCatalogEntityException() {
        //given
        var originalPathAt = BitbucketPathAtMother.of();

        var ctx = CatalogItemEntityContext.builder()
                .id("some-id")
                .repoCatalogItemPathAt(originalPathAt)
                .build();

        when(catalogServiceAdapter.getCatalogEntity(any(BitbucketPathAt.class)))
                .thenThrow(new InvalidEntityException("parse error"));

        //when //then
        assertThatThrownBy(() -> catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(ctx))
                .isInstanceOf(
                        org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException.class
                );
    }
}
