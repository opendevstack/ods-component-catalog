
package org.opendevstack.component_catalog.server.facade;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.OdsApiServerServiceProps;
import org.opendevstack.component_catalog.server.controllers.CatalogApiAdapter;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.CatalogItemMother;
import org.opendevstack.component_catalog.server.model.CatalogDescriptor;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.org.opendevstack.component_catalog.server.model.wrapper.CatalogItemWrapper;
import org.opendevstack.component_catalog.server.security.AuthorizationInfo;
import org.opendevstack.component_catalog.server.services.*;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.opendevstack.component_catalog.server.services.slug.CatalogItemSlug;
import org.opendevstack.component_catalog.util.JwtUtils;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogItemsApiFacadeTest {

    @Mock
    private ProjectsInfoService projectsInfoService;

    @Mock
    private CatalogApiAdapter catalogApiAdapter;

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

    @Mock
    private UserActionsEntitiesService userActionsEntitiesService;

    @Mock
    private CatalogItemBySlugService catalogItemBySlugService;

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @Mock
    private ProjectComponentsService projectComponentsService;

    @Mock
    private CatalogsCollectionService catalogsCollectionService;

    @Mock
    private OdsApiServerServiceProps odsApiServerServiceProps;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private AuthorizationInfo authInfo;

    @Mock
    private CatalogServiceAdapter catalogServiceAdapter;

    @Spy
    @InjectMocks
    private CatalogItemsApiFacade catalogItemsApiFacade;

    private static final String HUMAN_TOKEN = "humanToken";

    @Test
    void GivenProjectKeyAndAccessToken_WhenAsCatalogItem_ThenReturnsCatalogItemUsingProjectClusters() {
        // given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();
        var projectKey = "projectKey";
        var accessToken = "accessToken";

        var clusters = List.of("cluster-1", "cluster-2");
        var userGroups = List.of("user-group-1", "user-group-2");
        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(clusters);

        CatalogItem expectedCatalogItem = CatalogItemMother.of();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .accessToken(accessToken)
                .build();

        Integer componentCount = null;

        when(catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));
        when(projectsInfoService.getProjectClusters(projectKey, accessToken)).thenReturn(projectInfo);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);

        // when
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(projectsInfoService, times(1)).getProjectClusters(projectKey, accessToken);
        verify(catalogApiAdapter, times(1)).asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);
    }

    @Test
    void GivenProjectInfoWithNullClusters_WhenAsCatalogItem_ThenUsesEmptyClustersList() {
        // given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();
        var projectKey = "projectKey";
        var accessToken = "accessToken";

        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(null);

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        when(projectsInfoService.getProjectClusters(projectKey, accessToken)).thenReturn(projectInfo);

        CatalogItem expectedCatalogItem = CatalogItemMother.of();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .accessToken(accessToken)
                .build();

        Integer componentCount = null;

        when(catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // when
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(projectsInfoService, times(1)).getProjectClusters(projectKey, accessToken);
        verify(catalogApiAdapter, times(1)).asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);
    }

    @Test
    void GivenNullAccessToken_WhenAsCatalogItem_ThenSkipsProjectsInfoServiceAndUsesEmptyClusters() {
        // Given
        var catalogItemEntityContext = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();
        var projectKey = "projectKey";

        // No token provided in params; authentication facade also has no token (unauthenticated context)
        when(authenticationFacade.getAccessToken()).thenReturn(null);

        CatalogItem expectedCatalogItem = CatalogItemMother.of();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(catalogItemEntityContext)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .build();

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        Integer componentCount = null;

        when(catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(authenticationFacade, times(1)).getAccessToken();
        verify(projectsInfoService, times(0)).getProjectClusters(any(), any());
        verify(projectsInfoService, times(0)).getProjectGroups(any());
    }

    @Test
    void GivenProjectKeyAndAccessToken_WhenCatalogItemFiltersFrom_ThenReturnsFiltersUsingProjectClusters() {
        // given
        var catalogEntity = mock(CatalogEntity.class);
        var catalogItemEntityContext = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();
        var projectKey = "projectKey";
        var accessToken = "accessToken";

        var clusters = List.of("cluster-A", "cluster-B");
        var userGroups = List.of("user-group-A", "user-group-B");
        var projectInfo = new ProjectInfo();
        projectInfo.setClusters(clusters);

        var catalogItemRequestParams = CatalogRequestParams.builder()
                .catalogEntity(catalogEntity)
                .catalogItemEntityContext(catalogItemEntityContext)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .accessToken(accessToken)
                .build();

        when(projectsInfoService.getProjectClusters(projectKey, accessToken)).thenReturn(projectInfo);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);

        List<CatalogItemFilter> expectedFilters = List.of(mock(CatalogItemFilter.class));

        Integer componentCount = null;

        when(catalogApiAdapter.catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount))
                .thenReturn(expectedFilters);

        // when
        var result = catalogItemsApiFacade.catalogItemFiltersFrom(catalogItemRequestParams);

        // then
        assertThat(result).isSameAs(expectedFilters);
        verify(projectsInfoService, times(1)).getProjectClusters(projectKey, accessToken);

        verify(catalogApiAdapter, times(1))
                .catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount);
    }

    @Test
    void GivenNoProjectKeyAndNoAccessToken_WhenCatalogItemFiltersFrom_ThenUsesEmptyClusters() {
        // given
        var catalogEntity = CatalogEntityMother.of();
        var catalogItemEntityContext = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var projectKey = StringUtils.EMPTY;
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        // No token provided in params; authentication facade also has no token (unauthenticated context)
        when(authenticationFacade.getAccessToken()).thenReturn(null);

        var catalogItemRequestParams = CatalogRequestParams.builder()
                .catalogEntity(catalogEntity)
                .catalogItemEntityContext(catalogItemEntityContext)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .build();

        List<CatalogItemFilter> expectedFilters = List.of(mock(CatalogItemFilter.class));

        Integer componentCount = null;
        when(catalogApiAdapter.catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount))
                .thenReturn(expectedFilters);

        // when
        var result = catalogItemsApiFacade.catalogItemFiltersFrom(catalogItemRequestParams);

        // then
        assertThat(result).isSameAs(expectedFilters);
        verify(authenticationFacade, times(1)).getAccessToken();
        verify(projectsInfoService, times(0)).getProjectClusters(any(), any());
        verify(projectsInfoService, times(0)).getProjectGroups(any());

        verify(catalogApiAdapter, times(1))
                .catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount);
    }


    @Test
    void GivenContributingFileExists_WhenFetchCatalogItems_ThenMapsFiltersSortsAndReturnsList()
            throws InvalidIdException, InvalidCatalogEntityException {
        try (var mockedJwt = mockHumanToken()) {

            // given
            var catalogId = "catalog-1";
            var projectKey = "PRJ";
            var sortOrder = SortOrder.ASC;

            var ctx1 = mock(CatalogItemEntityContext.class);
            var ctx2 = mock(CatalogItemEntityContext.class);
            var itemEntityCtxs = List.of(ctx1, ctx2);

            var userActionsEntity = mock(UserActionsEntity.class);
            Set<CatalogEntityPermissionEnum> permissions = Set.of(CatalogEntityPermissionEnum.PROJECT_ADMIN);

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId)).thenReturn(itemEntityCtxs);
            when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(userActionsEntity);

            doReturn(permissions).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogId);
            doReturn(true).when(catalogItemsApiFacade).filterByContributingFileExists(anyString());
            doReturn(true).when(catalogItemsApiFacade).applyVisibilityFilter(any(), anyBoolean());

            CatalogItem itemB = new CatalogItem();
            itemB.setId("B");
            itemB.setTitle("B-title");

            CatalogItem itemA = new CatalogItem();
            itemA.setId("A");
            itemA.setTitle("A-title");

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && p.getCatalogItemEntityContext() == ctx1),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(itemB, true));

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && p.getCatalogItemEntityContext() == ctx2),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(itemA, true));

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken(HUMAN_TOKEN)
                    .projectKey(projectKey)
                    .sortOrder(sortOrder)
                    .build();

            // when
            var result = catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getId()).isEqualTo("A");
            assertThat(result.get(1).getId()).isEqualTo("B");

            verify(catalogApiAdapter, times(2)).asCatalogItem(any(), anyList(), anyList(), any());
            verify(catalogItemsApiFacade, times(2)).applyVisibilityFilter(any(), anyBoolean());
            verify(catalogItemsApiFacade).filterByContributingFileExists(catalogId);
        }
    }

    @Test
    void GivenContributingFileMissing_WhenFetchCatalogItems_ThenReturnsEmptyListAndSkipsMapping()
            throws InvalidIdException, InvalidCatalogEntityException {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            var catalogId = "catalog-2";

            mockedJwt.when(() -> JwtUtils.extractClaim("humanToken", "scp"))
                    .thenReturn(Optional.of("Api.Access"));

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken("humanToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId))
                    .thenReturn(List.of());

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of())
                    .when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(catalogId);

            doReturn(false)
                    .when(catalogItemsApiFacade)
                    .filterByContributingFileExists(catalogId);

            // when
            var result = catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            assertThat(result).isEmpty();

            verify(catalogEntitiesService).getCatalogItemsEntities(catalogId);
            verify(userActionsEntitiesService).getDefaultUserActionsEntity();
            verify(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogId);
            verify(catalogItemsApiFacade).filterByContributingFileExists(catalogId);
            verify(catalogItemsApiFacade, never()).asCatalogItem(any());
        }
    }

    @Test
    void GivenMixedItems_WhenFetchCatalogItems_ThenReturnsOnlyProjectMatchingItems()
            throws InvalidIdException, InvalidCatalogEntityException {
        try (var mockedJwt = mockHumanToken()) {

            // given
            var catalogId = "catalog-3";
            var projectKey = "PRJ-X";

            var ctxKeep = mock(CatalogItemEntityContext.class);
            var ctxDrop = mock(CatalogItemEntityContext.class);

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId)).thenReturn(List.of(ctxKeep, ctxDrop));
            when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogId);
            doReturn(true).when(catalogItemsApiFacade).filterByContributingFileExists(catalogId);

            var keep = new CatalogItem();
            keep.setId("keep");
            keep.setTitle("K");

            var drop = new CatalogItem();
            drop.setId("drop");
            drop.setTitle("D");

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && p.getCatalogItemEntityContext() == ctxKeep),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(keep, true));

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && p.getCatalogItemEntityContext() == ctxDrop),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(drop, true));

            doAnswer(inv -> {
                CatalogItem it = inv.getArgument(0);
                return "keep".equals(it.getId());
            }).when(catalogItemsApiFacade).applyVisibilityFilter(any(CatalogItem.class), anyBoolean());

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken(HUMAN_TOKEN)
                    .projectKey(projectKey)
                    .sortOrder(SortOrder.ASC)
                    .build();

            // when
            var result = catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            assertThat(result).hasSize(1);
            assertThat(result.getFirst().getId()).isEqualTo("keep");

            verify(catalogApiAdapter, times(2)).asCatalogItem(any(), anyList(), anyList(), any());
            verify(catalogItemsApiFacade, times(2)).applyVisibilityFilter(any(CatalogItem.class), anyBoolean());
            verify(catalogItemsApiFacade, times(0)).filterByContributingFileExists("keep");
        }
    }

    @Test
    void GivenMappingFailure_WhenFetchCatalogItems_ThenPropagatesInvalidCatalogEntityException()
            throws InvalidIdException {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            mockedJwt.when(() ->
                            JwtUtils.extractClaim("humanToken", "scp"))
                    .thenReturn(Optional.of("Api.Access"));

            var catalogId = "catalog-err";

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId))
                    .thenReturn(List.of(mock(CatalogItemEntityContext.class)));

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of())
                    .when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(catalogId);

            doReturn(true)
                    .when(catalogItemsApiFacade)
                    .filterByContributingFileExists(catalogId);

            when(catalogApiAdapter.asCatalogItem(
                    any(),
                    anyList(),
                    anyList(),
                    any()))
                    .thenThrow(new InvalidCatalogEntityException("bad"));

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken("humanToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            // when / then
            assertThatThrownBy(() ->
                    catalogItemsApiFacade.fetchCatalogItems(params))
                    .isInstanceOf(InvalidCatalogEntityException.class)
                    .hasMessageContaining("bad");
        }
    }

    @Test
    void GivenEmptyCatalogsCollection_WhenFetchCatalogItems_ThenThrowsInvalidCatalogEntityException() throws Exception {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            mockedJwt.when(() -> JwtUtils.extractClaim("validToken", "oid"))
                    .thenReturn(Optional.of("expectedOid"));

            when(odsApiServerServiceProps.getOid()).thenReturn("expectedOid");

            var params = CatalogRequestParams.builder()
                    .catalogId(null)
                    .accessToken("validToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            when(catalogsCollectionService.getCatalogsCollection()).thenReturn(Optional.empty());

            // when / then
            assertThatThrownBy(() -> catalogItemsApiFacade.fetchCatalogItems(params))
                    .isInstanceOf(InvalidCatalogEntityException.class);

            verify(catalogsCollectionService).getCatalogsCollection();
        }
    }

    @Test
    void GivenNoCatalogId_WhenFetchCatalogItems_ThenFetchesItemsFromAllCatalogs() throws Exception {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            mockedJwt.when(() -> JwtUtils.extractClaim("validToken", "oid"))
                    .thenReturn(Optional.of("expectedOid"));

            when(odsApiServerServiceProps.getOid()).thenReturn("expectedOid");

            var params = CatalogRequestParams.builder()
                    .catalogId(null)
                    .accessToken("validToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            var catalogsCollection = mock(CatalogsCollectionsEntity.class);
            when(catalogsCollectionService.getCatalogsCollection())
                    .thenReturn(Optional.of(catalogsCollection));

            var descriptor1 = mock(CatalogDescriptor.class);
            var descriptor2 = mock(CatalogDescriptor.class);

            when(descriptor1.getId()).thenReturn("catalog-1");
            when(descriptor2.getId()).thenReturn("catalog-2");

            when(catalogApiAdapter.asCatalogDescriptors(catalogsCollection))
                    .thenReturn(List.of(descriptor1, descriptor2));

            var ctx1 = mock(CatalogItemEntityContext.class);
            var ctx2 = mock(CatalogItemEntityContext.class);

            when(catalogEntitiesService.getCatalogItemsEntities("catalog-1"))
                    .thenReturn(List.of(ctx1));
            when(catalogEntitiesService.getCatalogItemsEntities("catalog-2"))
                    .thenReturn(List.of(ctx2));

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of()).when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(anyString());

            doReturn(true).when(catalogItemsApiFacade)
                    .filterByContributingFileExists(anyString());
            doReturn(true).when(catalogItemsApiFacade)
                    .applyVisibilityFilter(any(CatalogItem.class), anyBoolean());

            CatalogItem item1 = new CatalogItem();
            item1.setId("item-1");
            item1.setTitle("item-1-title");

            CatalogItem item2 = new CatalogItem();
            item2.setId("item-2");
            item2.setTitle("item-2-title");

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && "catalog-1".equals(p.getCatalogId())),
                    anyList(),
                    anyList(),
                    any()))
                .thenReturn(new CatalogItemWrapper(item1, true));

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && "catalog-2".equals(p.getCatalogId())),
                    anyList(),
                    anyList(),
                    any()))
                .thenReturn(new CatalogItemWrapper(item2, true));

            // when
            var result = catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            assertThat(result).hasSize(2);
            assertThat(result).extracting(CatalogItem::getId)
                    .containsExactlyInAnyOrder("item-1", "item-2");

            verify(catalogsCollectionService).getCatalogsCollection();
            verify(catalogEntitiesService).getCatalogItemsEntities("catalog-1");
            verify(catalogEntitiesService).getCatalogItemsEntities("catalog-2");
        }
    }

    @Test
    void GivenCatalogId_WhenFetchCatalogItems_ThenFetchesOnlyItemsFromThatCatalog() throws Exception {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            var catalogId = "catalog-123";

            mockedJwt.when(() -> JwtUtils.extractClaim("humanToken", "scp"))
                    .thenReturn(Optional.of("Api.Access"));

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken("humanToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            var ctx = mock(CatalogItemEntityContext.class);

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId))
                    .thenReturn(List.of(ctx));

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of()).when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(catalogId);

            doReturn(true).when(catalogItemsApiFacade)
                    .filterByContributingFileExists(catalogId);

            doReturn(true).when(catalogItemsApiFacade)
                    .applyVisibilityFilter(any(CatalogItem.class), anyBoolean());

            CatalogItem item = new CatalogItem();
            item.setId("item-1");

            when(catalogApiAdapter.asCatalogItem(
                    any(),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(item, true));

            // when
            var result = catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            assertThat(result).hasSize(1);
            assertThat(result.getFirst().getId()).isEqualTo("item-1");

            verify(catalogsCollectionService, never()).getCatalogsCollection();
        }
    }

    @Test
    void GivenNoCatalogIdAndInvalidOidToken_WhenFetchCatalogItems_ThenThrowsForbiddenException() {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            mockedJwt.when(() -> JwtUtils.extractClaim("badToken", "oid"))
                    .thenReturn(Optional.of("wrongOid"));

            when(odsApiServerServiceProps.getOid()).thenReturn("expectedOid");

            var params = CatalogRequestParams.builder()
                    .catalogId(null)
                    .accessToken("badToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            // when / then
            assertThatThrownBy(() -> catalogItemsApiFacade.fetchCatalogItems(params))
                    .isInstanceOf(ForbiddenException.class);
        }
    }

    @Test
    void GivenCatalogIdAndRepoReadPermission_WhenFetchCatalogItems_ThenPassesRepoReadPermissionToMapper() throws InvalidIdException {
        try (var mockedJwt = mockHumanToken()) {

            // given
            var catalogId = "catalog-123";

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken(HUMAN_TOKEN)
                    .permissions(Set.of(CatalogEntityPermissionEnum.REPO_READ))
                    .sortOrder(SortOrder.ASC)
                    .build();

            var catalogItemContext = mock(CatalogItemEntityContext.class);

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId))
                    .thenReturn(List.of(catalogItemContext));

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of())
                    .when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(catalogId);

            doReturn(true)
                    .when(catalogItemsApiFacade)
                    .filterByContributingFileExists(catalogId);

            doReturn(true)
                    .when(catalogItemsApiFacade)
                    .applyVisibilityFilter(any(CatalogItem.class), anyBoolean());

            when(catalogApiAdapter.asCatalogItem(
                    any(),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(new CatalogItem(), true));

            // when
            catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            verify(catalogApiAdapter).asCatalogItem(
                    argThat(request ->
                            request != null
                                    && request.getPermissions().contains(CatalogEntityPermissionEnum.REPO_READ)),
                    anyList(),
                    anyList(),
                    any());
        }
    }

    @Test
    void GivenCatalogIdAndApplicationToken_WhenFetchCatalogItems_ThenThrowsForbiddenException()
            throws InvalidIdException {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            mockedJwt.when(() -> JwtUtils.extractClaim("appToken", "scp"))
                    .thenReturn(Optional.empty());

            var params = CatalogRequestParams.builder()
                    .catalogId("catalog-1")
                    .accessToken("appToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            // when / then
            assertThatThrownBy(() -> catalogItemsApiFacade.fetchCatalogItems(params))
                    .isInstanceOf(ForbiddenException.class);

            verify(catalogEntitiesService, never()).getCatalogItemsEntities(anyString());
            verify(catalogsCollectionService, never()).getCatalogsCollection();
        }
    }

    @Test
    void GivenCatalogIdAndAccessToken_WhenFetchCatalogItems_ThenUsesSameTokenForMapping()
            throws Exception {
        try (var mockedJwt = mockHumanToken()) {

            // given
            var catalogId = "catalog-123";

            var params = CatalogRequestParams.builder()
                    .catalogId(catalogId)
                    .accessToken(HUMAN_TOKEN)
                    .sortOrder(SortOrder.ASC)
                    .build();

            var catalogItemContext = mock(CatalogItemEntityContext.class);

            when(catalogEntitiesService.getCatalogItemsEntities(catalogId))
                    .thenReturn(List.of(catalogItemContext));

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of())
                    .when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(catalogId);

            doReturn(true)
                    .when(catalogItemsApiFacade)
                    .filterByContributingFileExists(catalogId);

            doReturn(true)
                    .when(catalogItemsApiFacade)
                    .applyVisibilityFilter(any(CatalogItem.class), anyBoolean());

            when(catalogApiAdapter.asCatalogItem(
                    any(),
                    anyList(),
                    anyList(),
                    any()))
                    .thenReturn(new CatalogItemWrapper(new CatalogItem(), true));

            // when
            catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            verify(catalogApiAdapter).asCatalogItem(
                    argThat(request ->
                            request != null
                                    && HUMAN_TOKEN.equals(request.getAccessToken())),
                    anyList(),
                    anyList(),
                    any());
        }
    }

    @Test
    void GivenNoCatalogId_WhenFetchCatalogItems_ThenSortsAllItemsAscendingByTitle() throws Exception {
        try (var mockedJwt = mockStatic(JwtUtils.class)) {
            // given
            mockedJwt.when(() -> JwtUtils.extractClaim("validToken", "oid"))
                    .thenReturn(Optional.of("expectedOid"));

            when(odsApiServerServiceProps.getOid()).thenReturn("expectedOid");

            var params = CatalogRequestParams.builder()
                    .catalogId(null)
                    .accessToken("validToken")
                    .sortOrder(SortOrder.ASC)
                    .build();

            var catalogsCollection = mock(CatalogsCollectionsEntity.class);
            when(catalogsCollectionService.getCatalogsCollection())
                    .thenReturn(Optional.of(catalogsCollection));

            var descriptor1 = mock(CatalogDescriptor.class);
            var descriptor2 = mock(CatalogDescriptor.class);

            when(descriptor1.getId()).thenReturn("catalog-1");
            when(descriptor2.getId()).thenReturn("catalog-2");

            when(catalogApiAdapter.asCatalogDescriptors(catalogsCollection))
                    .thenReturn(List.of(descriptor1, descriptor2));

            when(catalogEntitiesService.getCatalogItemsEntities("catalog-1"))
                    .thenReturn(List.of(mock(CatalogItemEntityContext.class)));

            when(catalogEntitiesService.getCatalogItemsEntities("catalog-2"))
                    .thenReturn(List.of(mock(CatalogItemEntityContext.class)));

            when(userActionsEntitiesService.getDefaultUserActionsEntity())
                    .thenReturn(mock(UserActionsEntity.class));

            doReturn(Set.of()).when(catalogItemsApiFacade)
                    .currentPrincipalCatalogPermissions(anyString());

            doReturn(true).when(catalogItemsApiFacade)
                    .filterByContributingFileExists(anyString());

            CatalogItem zItem = CatalogItem.builder()
                    .id("item-z")
                    .title("Z-title")
                    .build();
            CatalogItem aItem = CatalogItem.builder()
                    .id("item-a")
                    .title("A-title")
                    .build();

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && "catalog-1".equals(p.getCatalogId())),
                    anyList(),
                    anyList(),
                    any()))
                .thenReturn(new CatalogItemWrapper(zItem, true));

            when(catalogApiAdapter.asCatalogItem(
                    argThat(p -> p != null && "catalog-2".equals(p.getCatalogId())),
                    anyList(),
                    anyList(),
                    any()))
                .thenReturn(new CatalogItemWrapper(aItem, true));

            doReturn(true)
                    .when(catalogItemsApiFacade)
                    .applyVisibilityFilter(any(CatalogItem.class), anyBoolean());

            // when
            var result = catalogItemsApiFacade.fetchCatalogItems(params);

            // then
            assertThat(result)
                    .extracting(CatalogItem::getTitle)
                    .containsExactly("A-title", "Z-title");
        }
    }

    @Test
    void GivenExistingEntityAndPassingFilters_WhenFetchCatalogItem_ThenReturnsCatalogItem()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var catalogItemId = "item-123";
        var projectKey = "PRJ-1";

        var ctx = mock(CatalogItemEntityContext.class);
        when(catalogEntitiesService.getCatalogItemEntity(catalogItemId)).thenReturn(Optional.of(ctx));
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));
        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogItemId);

        var item = new CatalogItem();
        item.setId(catalogItemId);
        item.setTitle("X");
        doReturn(item).when(catalogItemsApiFacade).asCatalogItem(any(CatalogRequestParams.class));

        doReturn(true).when(catalogItemsApiFacade).applyVisibilityFilter(eq(item), anyBoolean());

        var params = CatalogRequestParams.builder()
                .catalogItemId(catalogItemId)
                .projectKey(projectKey)
                .build();

        // when
        var response = catalogItemsApiFacade.fetchCatalogItem(params);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(catalogItemId);

        verify(catalogEntitiesService, times(1)).getCatalogItemEntity(catalogItemId);
        verify(catalogItemsApiFacade, times(1)).asCatalogItem(any(CatalogRequestParams.class));
        verify(catalogItemsApiFacade, times(1)).applyVisibilityFilter(eq(item), anyBoolean());
        verify(catalogItemsApiFacade, times(0)).filterByContributingFileExists(catalogItemId);
    }

    @Test
    void GivenMissingEntity_WhenFetchCatalogItem_ThenReturnsNull()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var catalogItemId = "unknown";
        when(catalogEntitiesService.getCatalogItemEntity(catalogItemId)).thenReturn(Optional.empty());
        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogItemId);
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));

        var params = CatalogRequestParams.builder().catalogItemId(catalogItemId).build();

        // when
        var response = catalogItemsApiFacade.fetchCatalogItem(params);

        // then
        assertThat(response).isNull();

        verify(catalogItemsApiFacade, times(0)).asCatalogItem(any());
    }

    @Test
    void GivenItemFilteredOutByProject_WhenFetchCatalogItem_ThenReturnsNull()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var catalogItemId = "item-456";
        var projectKey = "PRJ-Z";

        var ctx = mock(CatalogItemEntityContext.class);
        when(catalogEntitiesService.getCatalogItemEntity(catalogItemId)).thenReturn(Optional.of(ctx));
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));
        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogItemId);

        var item = new CatalogItem();
        item.setId(catalogItemId);
        doReturn(item).when(catalogItemsApiFacade).asCatalogItem(any(CatalogRequestParams.class));

        doReturn(false).when(catalogItemsApiFacade).applyVisibilityFilter(eq(item), anyBoolean());

        var params = CatalogRequestParams.builder()
                .catalogItemId(catalogItemId)
                .projectKey(projectKey)
                .build();

        // when
        var response = catalogItemsApiFacade.fetchCatalogItem(params);

        // then
        assertThat(response).isNull();

        verify(catalogItemsApiFacade, times(1)).applyVisibilityFilter(eq(item), anyBoolean());
    }

    @Test
    void GivenInvisibleItemAndVisibilityRestrictions_WhenApplyingVisibilityFilter_ThenReturnFalse() {
        var item = new CatalogItem();
        item.setVisible(false);

        var result = catalogItemsApiFacade.applyVisibilityFilter(item, false);

        assertThat(result).isFalse();
    }

    @Test
    void GivenInvisibleItemAndIgnoredVisibilityRestrictions_WhenApplyingVisibilityFilter_ThenReturnTrue() {
        var item = new CatalogItem();
        item.setVisible(false);

        var result = catalogItemsApiFacade.applyVisibilityFilter(item, true);

        assertThat(result).isTrue();
    }

    @Test
    void GivenMappingFailure_WhenFetchCatalogItem_ThenPropagatesInvalidCatalogItemEntityException()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var catalogItemId = "item-ex";
        when(catalogEntitiesService.getCatalogItemEntity(catalogItemId))
                .thenReturn(Optional.of(mock(CatalogItemEntityContext.class)));
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));
        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogItemId);

        doThrow(new InvalidCatalogItemEntityException("invalid item")).when(catalogItemsApiFacade).asCatalogItem(any());

        var params = CatalogRequestParams.builder().catalogItemId(catalogItemId).build();

        // when / then
        assertThatThrownBy(() -> catalogItemsApiFacade.fetchCatalogItem(params))
                .isInstanceOf(InvalidCatalogItemEntityException.class)
                .hasMessageContaining("invalid item");
    }

    @Test
    void GivenExistingSlug_WhenFetchCatalogItemBySlug_ThenReturnsMappedCatalogItem()
            throws Exception {
        // given
        var slug = CatalogItemSlug.parse("myproject_my-repo");
        var itemCtx = mock(CatalogItemEntityContext.class);
        when(itemCtx.getId()).thenReturn("item-id-1");
        when(catalogItemBySlugService.findByCatalogItemSlug(slug)).thenReturn(Optional.of(itemCtx));
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));
        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions("item-id-1");

        var expected = new CatalogItem();
        expected.setId("item-id-1");
        doReturn(expected).when(catalogItemsApiFacade).asCatalogItem(any(CatalogRequestParams.class));

        // when
        var result = catalogItemsApiFacade.fetchCatalogItemBySlug(slug);

        // then
        assertThat(result).isEqualTo(expected);
        verify(catalogItemBySlugService).findByCatalogItemSlug(slug);
    }

    @Test
    void GivenMissingSlug_WhenFetchCatalogItemBySlug_ThenReturnsNull()
            throws Exception {
        // given
        var slug = CatalogItemSlug.parse("myproject_my-repo");
        when(catalogItemBySlugService.findByCatalogItemSlug(slug)).thenReturn(Optional.empty());

        // when
        var result = catalogItemsApiFacade.fetchCatalogItemBySlug(slug);

        // then
        assertThat(result).isNull();
        verify(userActionsEntitiesService, never()).getDefaultUserActionsEntity();
    }

    @Test
    void GivenCatalogRequestWithMatchingComponents_WhenAsCatalogItem_ThenCalculatesComponentCountCorrectly() {
        // Given
        var catalogEntity = CatalogEntityMother.of();
        var userGroups = List.of("owner1", "group1");

        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken("any-access-token")
                .projectKey("PRJ-COMP")
                .build();

        // Create components with matching and non-matching catalog item IDs
        var matchingComponent = ProjectComponent.builder()
                .componentId("comp-1")
                .catalogItemId("catalog-item-repo")
                .status(Status.CREATED)
                .build();

        var nonMatchingComponent = ProjectComponent.builder()
                .componentId("comp-2")
                .catalogItemId("other-catalog-item")
                .status(Status.CREATED)
                .build();

        var projectComponents = ProjectComponents.builder()
                .components(Map.of(
                        "comp-1", matchingComponent,
                        "comp-2", nonMatchingComponent
                ))
                .build();

        when(projectsInfoService.getProjectGroups(catalogRequestParams.getAccessToken())).thenReturn(userGroups);
        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-1"));
        when(provisionerActionsService.getProjectComponents("PRJ-1")).thenReturn(projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId)).thenReturn("catalog-item-repo");

        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx))
                .thenReturn(Optional.of(catalogEntity));

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), eq(userGroups), eq(1)))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(provisionerActionsService).getAllProjectComponentsProjectKeys();
        verify(projectComponentsService).getRepoPathFromCatalogItemId(catalogItemId);
    }

    @Test
    void GivenCatalogRequestWithNoMatchingComponents_WhenAsCatalogItem_ThenComponentCountIsZero() {
        // Given
        var catalogEntity = CatalogEntityMother.of();
        var userGroups = List.of("owner1", "group1");

        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken("any-access-token")
                .build();

        // Create components that don't match
        var nonMatchingComponent1 = ProjectComponent.builder()
                .componentId("comp-x")
                .catalogItemId("other-item-1")
                .status(Status.CREATED)
                .build();

        var nonMatchingComponent2 = ProjectComponent.builder()
                .componentId("comp-y")
                .catalogItemId("other-item-2")
                .status(Status.CREATED)
                .build();

        var projectComponents = ProjectComponents.builder()
                .components(Map.of(
                        "comp-x", nonMatchingComponent1,
                        "comp-y", nonMatchingComponent2
                ))
                .build();

        when(projectsInfoService.getProjectGroups(catalogRequestParams.getAccessToken())).thenReturn(userGroups);

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-X"));
        when(provisionerActionsService.getProjectComponents("PRJ-X")).thenReturn(projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId)).thenReturn("catalog-item-no-match");

        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx))
                .thenReturn(Optional.of(catalogEntity));

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), eq(userGroups), eq(0)))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(catalogApiAdapter).asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0));
    }

    @Test
    void GivenCatalogRequestWithInvalidEntityException_WhenAsCatalogItem_ThenHandlesExceptionAndContinues() {
        // Given
        var catalogEntity = CatalogEntityMother.of();
        var userGroups = List.of("owner1", "group1");

        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken("any-access-token")
                .build();

        var component = ProjectComponent.builder()
                .componentId("comp-err")
                .catalogItemId("catalog-item")
                .status(Status.CREATED)
                .build();

        var projectComponents = ProjectComponents.builder()
                .components(Map.of("comp-err", component))
                .build();

        when(projectsInfoService.getProjectGroups(catalogRequestParams.getAccessToken())).thenReturn(userGroups);

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-ERR"));
        when(provisionerActionsService.getProjectComponents("PRJ-ERR")).thenReturn(projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId))
                .thenThrow(new InvalidEntityException("Invalid entity"));

        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx))
                .thenReturn(Optional.of(catalogEntity));

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), eq(userGroups), eq(0)))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(projectComponentsService).getRepoPathFromCatalogItemId(catalogItemId);
    }

    @Test
    void GivenEmptyProjectComponentsList_WhenAsCatalogItem_ThenComponentCountIsZero() {
        // Given
        var catalogEntity = CatalogEntityMother.of();
        var userGroups = List.of("owner1", "group1");

        var itemEntityCtx = CatalogItemEntityContextMother.of();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken("any-access-token")
                .build();

        when(projectsInfoService.getProjectGroups(catalogRequestParams.getAccessToken())).thenReturn(userGroups);
        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx))
                .thenReturn(Optional.of(catalogEntity));

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(Collections.emptyList());

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), eq(userGroups), eq(0)))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(catalogApiAdapter).asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0));
    }

    @Test
    void GivenMultipleProjectsWithMultipleComponents_WhenAsCatalogItem_ThenCountsMatchingComponentsAcrossAllProjects() {
        // Given
        var catalogEntity = CatalogEntityMother.of();
        var userGroups = List.of("owner1", "group1");

        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken("any-access-token")
                .build();

        // First project with matching component
        var matchingComponent1 = ProjectComponent.builder()
                .componentId("comp-match-1")
                .catalogItemId(catalogItemId)
                .status(Status.CREATED)
                .build();

        var projectComponents1 = ProjectComponents.builder()
                .components(Map.of("comp-match-1", matchingComponent1))
                .build();

        // Second project with matching component
        var matchingComponent2 = ProjectComponent.builder()
                .componentId("comp-match-2")
                .catalogItemId(catalogItemId)
                .status(Status.CREATED)
                .build();

        var projectComponents2 = ProjectComponents.builder()
                .components(Map.of("comp-match-2", matchingComponent2))
                .build();

        when(projectsInfoService.getProjectGroups(catalogRequestParams.getAccessToken())).thenReturn(userGroups);
        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-1", "PRJ-2"));
        when(provisionerActionsService.getProjectComponents("PRJ-1")).thenReturn(projectComponents1);
        when(provisionerActionsService.getProjectComponents("PRJ-2")).thenReturn(projectComponents2);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId)).thenReturn(catalogItemId);

        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx))
                .thenReturn(Optional.of(catalogEntity));

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), eq(userGroups), eq(2)))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(provisionerActionsService, times(1)).getAllProjectComponentsProjectKeys();
        verify(provisionerActionsService).getProjectComponents("PRJ-1");
        verify(provisionerActionsService).getProjectComponents("PRJ-2");
    }

    @Test
    void GivenInvalidIdException_WhenGettingCurrentPrincipalCatalogPermissions_ThenReturnsEmptySet() throws InvalidIdException {
        // given
        var catalogId = "catalog-invalid";
        var principalName = "john.doe";

        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);
        when(catalogEntitiesService.catalogPrincipalPermissions(catalogId, principalName))
                .thenThrow(new InvalidIdException("invalid id"));

        // when
        var result = catalogItemsApiFacade.currentPrincipalCatalogPermissions(catalogId);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void GivenExistingPermissions_WhenGettingCurrentPrincipalCatalogPermissions_ThenReturnsPermissions() throws InvalidIdException {
        // given
        var catalogId = "catalog-ok";
        var principalName = "john.doe";
        var permissions = Set.of(CatalogEntityPermissionEnum.REPO_READ);

        when(authInfo.getCurrentPrincipalName()).thenReturn(principalName);
        when(catalogEntitiesService.catalogPrincipalPermissions(catalogId, principalName)).thenReturn(permissions);

        // when
        var result = catalogItemsApiFacade.currentPrincipalCatalogPermissions(catalogId);

        // then
        assertThat(result).isEqualTo(permissions);
    }

    @Test
    void GivenUserWithoutCatalogOwnerGroup_WhenAsCatalogItem_ThenDoesNotCalculateComponentCount() {
        // given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        var accessToken = "accessToken";
        var userGroups = List.of("non-owner-group");

        var metadata = mock(CatalogEntityMetadata.class);
        var catalogEntity = mock(CatalogEntity.class);
        when(metadata.getOwners()).thenReturn(List.of("owner-group"));
        when(catalogEntity.getMetadata()).thenReturn(metadata);

        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx))
                .thenReturn(Optional.of(catalogEntity));
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(userGroups);

        var requestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(Set.of())
                .accessToken(accessToken)
                .build();

        var expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(requestParams), anyList(), eq(userGroups), isNull()))
                .thenReturn(new CatalogItemWrapper(expectedCatalogItem, true));

        // when
        var result = catalogItemsApiFacade.asCatalogItem(requestParams);

        // then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(provisionerActionsService, never()).getAllProjectComponentsProjectKeys();
        verify(projectComponentsService, never()).getRepoPathFromCatalogItemId(anyString());
    }

    @Test
    void GivenInvalidCatalogItemWrapper_WhenAsCatalogItem_ThenReturnsNull() {
        // given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        when(authenticationFacade.getAccessToken()).thenReturn(null);
        when(catalogEntitiesService.getCatalogEntityByCatalogItemEntityContext(itemEntityCtx)).thenReturn(Optional.empty());

        var requestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(Set.of())
                .build();

        when(catalogApiAdapter.asCatalogItem(eq(requestParams), anyList(), anyList(), isNull()))
                .thenReturn(new CatalogItemWrapper(null, false));

        // when
        var result = catalogItemsApiFacade.asCatalogItem(requestParams);

        // then
        assertThat(result).isNull();
    }

    @Test
    void GivenCatalogId_WhenFilterByContributingFileExists_ThenDelegatesToCatalogServiceAdapter() {
        // given
        var catalogId = "catalog-1";
        when(catalogServiceAdapter.contributingFileExists(catalogId)).thenReturn(true);

        // when
        var result = catalogItemsApiFacade.filterByContributingFileExists(catalogId);

        // then
        assertThat(result).isTrue();
        verify(catalogServiceAdapter).contributingFileExists(catalogId);
    }

    private MockedStatic<JwtUtils> mockHumanToken() {
        var mockedJwt = mockStatic(JwtUtils.class);

        mockedJwt.when(() ->
                        JwtUtils.extractClaim(HUMAN_TOKEN, "scp"))
                .thenReturn(Optional.of("Api.Access"));

        return mockedJwt;
    }

}
