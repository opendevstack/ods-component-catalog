package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;

@ExtendWith(MockitoExtension.class)
class CatalogItemSlugResolverTest {

    private static final String BASE_RAW_URL = "https://bitbucket.example.com";
    private static final String BASE_REST_URL = "https://bitbucket.example.com/rest";
    private static final String DEFAULT_BRANCH = "refs/heads/main";

    @Mock
    private ProvisionerActionsConfiguration provisionerActionsConfiguration;

    @Mock
    private BitbucketService bitbucketService;

    private CatalogItemSlugResolver catalogItemSlugResolver;

    @BeforeEach
    void setUp() {
        catalogItemSlugResolver = new CatalogItemSlugResolver(provisionerActionsConfiguration, bitbucketService);
    }

    private void configurePathAtBuilder() {
        when(bitbucketService.pathAtBuilder()).thenReturn(BitbucketPathAt.builder()
            .baseRawUrl(BASE_RAW_URL)
            .baseRestUrl(BASE_REST_URL));
    }

    @Test
    void givenCatalogItemIdWithReference_whenResolve_thenReturnsSlugAndKeepsReference() throws InvalidIdException {
        configurePathAtBuilder();
        var catalogItemId = idEncode("projects/CATALOG1/repos/first-catalog-item/raw/CatalogItem.yaml?at=refs/heads/release");

        var result = catalogItemSlugResolver.resolve(catalogItemId);

        assertThat(result.getProjectKey()).isEqualTo("catalog1");
        assertThat(result.getRepoName()).isEqualTo("first-catalog-item");
        verifyNoInteractions(provisionerActionsConfiguration);
    }

    @Test
    void givenCatalogItemIdWithoutReference_whenResolve_thenUsesConfiguredBranchAndReturnsSlug() throws InvalidIdException {
        configurePathAtBuilder();
        var catalogItemId = idEncode("projects/CATALOG1/repos/first-catalog-item/raw/CatalogItem.yaml");
        when(provisionerActionsConfiguration.getBranchName()).thenReturn(DEFAULT_BRANCH);

        var result = catalogItemSlugResolver.resolve(catalogItemId);

        assertThat(result.getProjectKey()).isEqualTo("catalog1");
        assertThat(result.getRepoName()).isEqualTo("first-catalog-item");
        verify(provisionerActionsConfiguration).getBranchName();
    }

    @Test
    void givenInvalidBase64CatalogItemId_whenResolve_thenThrowsInvalidIdException() {
        var catalogItemId = "%%%invalid-base64%%%";

        assertThatThrownBy(() -> catalogItemSlugResolver.resolve(catalogItemId))
                .isInstanceOf(InvalidIdException.class);
        verifyNoInteractions(provisionerActionsConfiguration);
    }

    @Test
    void givenDecodedCatalogItemIdWithInvalidPath_whenResolve_thenThrowsInvalidIdException() {
        configurePathAtBuilder();
        var catalogItemId = idEncode("invalid-path");
        when(provisionerActionsConfiguration.getBranchName()).thenReturn(DEFAULT_BRANCH);

        assertThatThrownBy(() -> catalogItemSlugResolver.resolve(catalogItemId))
                .isInstanceOf(InvalidIdException.class);
        verify(provisionerActionsConfiguration).getBranchName();
    }

}
