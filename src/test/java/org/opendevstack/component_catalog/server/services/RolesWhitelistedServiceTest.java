package org.opendevstack.component_catalog.server.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogServiceAdapter;
import org.opendevstack.component_catalog.server.services.catalog.entity.RolesWhitelisted;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolesWhitelistedServiceTest {

    private static final String BASE_RAW_URL = "https://bitbucket.example.com";
    private static final String BASE_REST_URL = "https://bitbucket.example.com/rest";

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @Mock
    private BitbucketService bitbucketService;

    @Mock
    private ProvisionerActionsConfiguration provisionerActionsConfiguration;

    @InjectMocks
    private RolesWhitelistedService rolesWhitelistedService;

    @BeforeEach
    void setUpPathAtBuilder() {
		lenient().when(bitbucketService.pathAtBuilder()).thenReturn(
				BitbucketPathAt.builder()
						.baseRawUrl(BASE_RAW_URL)
						.baseRestUrl(BASE_REST_URL)
		);
    }

    @Test
    void givenCatalogItemWithWhitelistedRoles_whenResolveWhitelistedRoles_thenReturnsMatchingRoles()
			throws InvalidIdException {
		// given
		var catalogItemId = "catalog-item-id";
		var catalogItemPathAt = mock(BitbucketPathAt.class);
		when(catalogItemPathAt.getProjectKey()).thenReturn("MYPROJECT");
		when(catalogItemPathAt.getRepoSlug()).thenReturn("my-repo");
		when(catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId)).thenReturn(catalogItemPathAt);
		configureRolesWhitelistedPath();

		var rolesWhitelisted = RolesWhitelisted.builder()
				.roles(Map.of(
						"administrator", List.of("myproject_my-repo"),
						"developer", List.of("another-project_another-repo"),
						"viewer", List.of("myproject_my-repo", "another-project_another-repo")
				))
				.build();
		when(catalogServiceAdapter.getYamlEntity(any(BitbucketPathAt.class), eq(RolesWhitelisted.class)))
				.thenReturn(Optional.of(rolesWhitelisted));

		// when
		var result = rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(catalogItemId);

		// then
		assertThat(result).containsExactlyInAnyOrder("administrator", "viewer");
		var pathAtCaptor = ArgumentCaptor.forClass(BitbucketPathAt.class);
		verify(catalogServiceAdapter).getYamlEntity(pathAtCaptor.capture(), eq(RolesWhitelisted.class));
		assertThat(pathAtCaptor.getValue().getProjectKey()).isEqualTo("PROVISIONER");
		assertThat(pathAtCaptor.getValue().getRepoSlug()).isEqualTo("project-configurations");
		assertThat(pathAtCaptor.getValue().getSubPath()).isEqualTo("roles-whitelisted.yaml");
		assertThat(pathAtCaptor.getValue().getAt()).isEqualTo("refs/heads/main");
    }

    @Test
    void givenCatalogItemWithoutWhitelistedRoles_whenResolveWhitelistedRoles_thenReturnsEmptyList()
			throws InvalidIdException {
		// given
		var catalogItemId = "catalog-item-id";
		var catalogItemPathAt = mock(BitbucketPathAt.class);
		when(catalogItemPathAt.getProjectKey()).thenReturn("MYPROJECT");
		when(catalogItemPathAt.getRepoSlug()).thenReturn("my-repo");
		when(catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId)).thenReturn(catalogItemPathAt);
		configureRolesWhitelistedPath();
		when(catalogServiceAdapter.getYamlEntity(any(BitbucketPathAt.class), eq(RolesWhitelisted.class)))
				.thenReturn(Optional.of(RolesWhitelisted.builder()
						.roles(Map.of("administrator", List.of("another-project_another-repo")))
						.build()));

		// when
		var result = rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(catalogItemId);

		// then
		assertThat(result).isEmpty();
    }

    @Test
    void givenInvalidCatalogItemId_whenResolveWhitelistedRoles_thenThrowsInvalidEntityException()
			throws InvalidIdException {
		// given
		var catalogItemId = "invalid-catalog-item-id";
		when(catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId)).thenThrow(new InvalidIdException(catalogItemId));

		// when // then
		assertThatThrownBy(() -> rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(catalogItemId))
				.isInstanceOf(InvalidEntityException.class)
				.hasMessage("Invalid catalogItemId: invalid-catalog-item-id");
		verifyNoInteractions(bitbucketService, provisionerActionsConfiguration);
    }

    @Test
    void givenMissingRolesWhitelistedFile_whenResolveWhitelistedRoles_thenThrowsInvalidEntityException()
			throws InvalidIdException {
		// given
		var catalogItemId = "catalog-item-id";
		var catalogItemPathAt = mock(BitbucketPathAt.class);
		when(catalogItemPathAt.getProjectKey()).thenReturn("MYPROJECT");
		when(catalogItemPathAt.getRepoSlug()).thenReturn("my-repo");
		when(catalogServiceAdapter.bitbucketPathAtFromId(catalogItemId)).thenReturn(catalogItemPathAt);
		configureRolesWhitelistedPath();
		when(catalogServiceAdapter.getYamlEntity(any(BitbucketPathAt.class), eq(RolesWhitelisted.class)))
				.thenReturn(Optional.empty());

		// when // then
		assertThatThrownBy(() -> rolesWhitelistedService.resolveWhitelistedRolesForCatalogItemId(catalogItemId))
				.isInstanceOf(InvalidEntityException.class)
				.hasMessageContaining("Invalid RolesWhitelisted.yaml file, path: projects/PROVISIONER/repos/");
    }

    private void configureRolesWhitelistedPath() {
		when(provisionerActionsConfiguration.getProjectKey()).thenReturn("PROVISIONER");
		when(provisionerActionsConfiguration.getProjectConfigurationsRepositorySlug()).thenReturn("project-configurations");
		when(provisionerActionsConfiguration.getProjectConfigurationsRolesWhitelistedPath())
				.thenReturn("roles-whitelisted.yaml");
		when(provisionerActionsConfiguration.getBranchName()).thenReturn("refs/heads/main");
    }
}
