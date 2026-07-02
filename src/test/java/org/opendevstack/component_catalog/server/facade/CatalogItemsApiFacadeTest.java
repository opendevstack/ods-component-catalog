
package org.opendevstack.component_catalog.server.facade;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.client.projects_info_service.v1_0_0.model.ProjectInfo;
import org.opendevstack.component_catalog.server.controllers.CatalogApiAdapter;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.mappers.CatalogItemMother;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemFilter;
import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.CatalogItemBySlugService;
import org.opendevstack.component_catalog.server.services.ProjectComponentsService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.UserActionsEntitiesService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogEntityException;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    @Spy
    @InjectMocks
    private CatalogItemsApiFacade catalogItemsApiFacade;

    @Test
    void asCatalogItem_returnsCatalogItemUsingClustersFromProjectsApi() {
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

        var componentCount = 0;

        when(catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount)).thenReturn(expectedCatalogItem);
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
    void asCatalogItem_whenProjectInfoHasNullClusters_usesEmptyList() {
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

        var componentCount = 0;

        when(catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount)).thenReturn(expectedCatalogItem);

        // when
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(projectsInfoService, times(1)).getProjectClusters(projectKey, accessToken);
        verify(catalogApiAdapter, times(1)).asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount);
    }

    @Test
    void givenANullAccessToken_whenAsCatalogItem_thenNoRequestToProjectsInfoService_AndEmptyClusters() {
        // Given
        var catalogItemEntityContext = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();
        var projectKey = "projectKey";
        String accessToken = null;

        CatalogItem expectedCatalogItem = CatalogItemMother.of();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(catalogItemEntityContext)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .accessToken(accessToken)
                .build();

        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();

        var componentCount = 0;

        when(catalogApiAdapter.asCatalogItem(catalogRequestParams, clusters, userGroups, componentCount)).thenReturn(expectedCatalogItem);

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(projectsInfoService, times(0)).getProjectClusters(projectKey, accessToken);
    }

    @Test
    void catalogItemFiltersFrom_withProjectKeyAndToken_returnsFiltersUsingClustersFromProjectsApi() {
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

        var componentCount = 0; // Example component count for testing

        when(catalogApiAdapter.catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount)).thenReturn(expectedFilters);

        // when
        var result = catalogItemsApiFacade.catalogItemFiltersFrom(catalogItemRequestParams);

        // then
        assertThat(result).isSameAs(expectedFilters);
        verify(projectsInfoService, times(1)).getProjectClusters(projectKey, accessToken);

        verify(catalogApiAdapter, times(1)).catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount);
    }

    @Test
    void catalogItemFiltersFrom_withoutProjectKeyAndToken_usesEmptyClusters() {
        // given
        var catalogEntity = CatalogEntityMother.of();
        var catalogItemEntityContext = CatalogItemEntityContextMother.of();
        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var projectKey = StringUtils.EMPTY;
        var clusters = Collections.<String>emptyList();
        var userGroups = Collections.<String>emptyList();


        // catalogRequestParams.getCatalogItemEntityContext()
        var catalogItemRequestParams = CatalogRequestParams.builder()
                .catalogEntity(catalogEntity)
                .catalogItemEntityContext(catalogItemEntityContext)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .projectKey(projectKey)
                .build();

        List<CatalogItemFilter> expectedFilters = List.of(mock(CatalogItemFilter.class));

        var componentCount = 0;
        when(catalogApiAdapter.catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount)).thenReturn(expectedFilters);

        // when
        var result = catalogItemsApiFacade.catalogItemFiltersFrom(catalogItemRequestParams);

        // then
        assertThat(result).isSameAs(expectedFilters);
        verify(projectsInfoService, times(0)).getProjectClusters(any(), any());
        verify(projectsInfoService, times(0)).getProjectGroups(catalogItemRequestParams.getAccessToken());

        verify(catalogApiAdapter, times(1)).catalogItemFiltersFrom(catalogItemRequestParams, clusters, userGroups, componentCount);
    }


    @Test
    void fetchCatalogItems_whenContributingCheckForCatalogTrue_mapsBuildsFiltersSortsAndReturnsList()
            throws InvalidIdException, InvalidCatalogEntityException {

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
        doReturn(true).when(catalogItemsApiFacade).filterByProject(any(), eq(projectKey));

        CatalogItem itemB = new CatalogItem();
        itemB.setId("B");
        itemB.setTitle("B-title");

        CatalogItem itemA = new CatalogItem();
        itemA.setId("A");
        itemA.setTitle("A-title");

        doAnswer(invocation -> {
            CatalogRequestParams p = invocation.getArgument(0);
            return p.getCatalogItemEntityContext() == ctx1 ? itemB : itemA;
        }).when(catalogItemsApiFacade).asCatalogItem(any());

        var params = CatalogRequestParams.builder()
                .catalogId(catalogId)
                .projectKey(projectKey)
                .sortOrder(sortOrder)
                .build();

        // when
        var result = catalogItemsApiFacade.fetchCatalogItems(params);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo("A");
        assertThat(result.get(1).getId()).isEqualTo("B");

        verify(catalogItemsApiFacade, times(2)).asCatalogItem(any());
        verify(catalogItemsApiFacade, times(2)).filterByProject(any(), eq(projectKey));
        verify(catalogItemsApiFacade).filterByContributingFileExists(catalogId);
    }


    @Test
    void fetchCatalogItems_whenContributingCheckForCatalogFalse_returnsEmptyListAndSkipsMapping()
            throws InvalidIdException, InvalidCatalogEntityException {

        // given
        var catalogId = "catalog-2";
        var params = CatalogRequestParams.builder()
                .catalogId(catalogId)
                .sortOrder(SortOrder.ASC)
                .build();

        when(catalogEntitiesService.getCatalogItemsEntities(catalogId)).thenReturn(List.of());
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));

        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogId);
        doReturn(false).when(catalogItemsApiFacade).filterByContributingFileExists(catalogId);

        // when
        var result = catalogItemsApiFacade.fetchCatalogItems(params);

        // then
        assertThat(result).isEmpty();

        verify(catalogEntitiesService, times(1)).getCatalogItemsEntities(catalogId);
        verify(userActionsEntitiesService, times(1)).getDefaultUserActionsEntity();
        verify(catalogItemsApiFacade, times(1)).currentPrincipalCatalogPermissions(catalogId);
        verify(catalogItemsApiFacade, times(1)).filterByContributingFileExists(catalogId);
        verify(catalogItemsApiFacade, times(0)).asCatalogItem(any());
    }

    @Test
    void fetchCatalogItems_filtersByProjectAndContributingPerItem_leavesOnlyMatchingOnes()
            throws InvalidIdException, InvalidCatalogEntityException {
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

        doAnswer(inv -> {
            CatalogRequestParams p = inv.getArgument(0);
            if (p.getCatalogItemEntityContext() == ctxKeep) return keep;
            if (p.getCatalogItemEntityContext() == ctxDrop) return drop;
            return null;
        }).when(catalogItemsApiFacade).asCatalogItem(any(CatalogRequestParams.class));

        doAnswer(inv -> {
            CatalogItem it = inv.getArgument(0);
            return "keep".equals(it.getId());
        }).when(catalogItemsApiFacade).filterByProject(any(CatalogItem.class), eq(projectKey));

        var params = CatalogRequestParams.builder()
                .catalogId(catalogId)
                .projectKey(projectKey)
                .sortOrder(SortOrder.ASC)
                .build();

        // when
        var result = catalogItemsApiFacade.fetchCatalogItems(params);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getId()).isEqualTo("keep");

        verify(catalogItemsApiFacade, times(2)).asCatalogItem(any(CatalogRequestParams.class));
        verify(catalogItemsApiFacade, times(2)).filterByProject(any(CatalogItem.class), eq(projectKey));
        verify(catalogItemsApiFacade, times(0)).filterByContributingFileExists("keep");
    }

    @Test
    void fetchCatalogItems_whenAsCatalogItemThrows_propagatesInvalidCatalogEntityException() throws InvalidIdException {
        // given
        var catalogId = "catalog-err";
        when(catalogEntitiesService.getCatalogItemsEntities(catalogId)).thenReturn(List.of(mock(CatalogItemEntityContext.class)));
        when(userActionsEntitiesService.getDefaultUserActionsEntity()).thenReturn(mock(UserActionsEntity.class));
        doReturn(Set.of()).when(catalogItemsApiFacade).currentPrincipalCatalogPermissions(catalogId);
        doReturn(true).when(catalogItemsApiFacade).filterByContributingFileExists(catalogId);

        doThrow(new InvalidCatalogEntityException("bad")).when(catalogItemsApiFacade).asCatalogItem(any());

        var params = CatalogRequestParams.builder().catalogId(catalogId).sortOrder(SortOrder.ASC).build();

        // when / then
        assertThatThrownBy(() -> catalogItemsApiFacade.fetchCatalogItems(params))
                .isInstanceOf(InvalidCatalogEntityException.class)
                .hasMessageContaining("bad");
    }

    @Test
    void fetchCatalogItem_whenEntityFoundAndPassesFilters_returnsOk()
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

        doReturn(true).when(catalogItemsApiFacade).filterByProject(item, projectKey);

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
        verify(catalogItemsApiFacade, times(1)).filterByProject(item, projectKey);
        verify(catalogItemsApiFacade, times(0)).filterByContributingFileExists(catalogItemId);
    }

    @Test
    void fetchCatalogItem_whenEntityMissing_returnsNotFound()
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
    void fetchCatalogItem_whenFilteredOutByProject_returnsNotFound()
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

        doReturn(false).when(catalogItemsApiFacade).filterByProject(item, projectKey);

        var params = CatalogRequestParams.builder()
                .catalogItemId(catalogItemId)
                .projectKey(projectKey)
                .build();

        // when
        var response = catalogItemsApiFacade.fetchCatalogItem(params);

        // then
        assertThat(response).isNull();

        verify(catalogItemsApiFacade, times(1)).filterByProject(item, projectKey);
    }

    @Test
    void fetchCatalogItem_whenAsCatalogItemThrows_propagatesInvalidCatalogItemEntityException()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var catalogItemId = "item-ex";
        when(catalogEntitiesService.getCatalogItemEntity(catalogItemId)).thenReturn(Optional.of(mock(CatalogItemEntityContext.class)));
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
    void fetchCatalogItemBySlug_whenItemFound_returnsMappedCatalogItem()
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
    void fetchCatalogItemBySlug_whenItemNotFound_returnsNull()
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
    void givenCatalogRequestWithMatchingComponents_whenAsCatalogItem_thenCalculatesComponentCountCorrectly() {
        // Given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken(null)
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

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-1"));
        when(provisionerActionsService.getProjectComponents("PRJ-1")).thenReturn(projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId)).thenReturn("catalog-item-repo");

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(1))).thenReturn(expectedCatalogItem);

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(provisionerActionsService).getAllProjectComponentsProjectKeys();
        verify(projectComponentsService, times(2)).getRepoPathFromCatalogItemId(catalogItemId);
    }

    @Test
    void givenCatalogRequestWithNoMatchingComponents_whenAsCatalogItem_thenComponentCountIsZero() {
        // Given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken(null)
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

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-X"));
        when(provisionerActionsService.getProjectComponents("PRJ-X")).thenReturn(projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId)).thenReturn("catalog-item-no-match");

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0))).thenReturn(expectedCatalogItem);

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(catalogApiAdapter).asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0));
    }

    @Test
    void givenCatalogRequestWithInvalidEntityException_whenAsCatalogItem_thenHandlesExceptionAndContinues() {
        // Given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken(null)
                .build();

        var component = ProjectComponent.builder()
                .componentId("comp-err")
                .catalogItemId("catalog-item")
                .status(Status.CREATED)
                .build();

        var projectComponents = ProjectComponents.builder()
                .components(Map.of("comp-err", component))
                .build();

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-ERR"));
        when(provisionerActionsService.getProjectComponents("PRJ-ERR")).thenReturn(projectComponents);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId))
                .thenThrow(new InvalidEntityException("Invalid entity"));

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0))).thenReturn(expectedCatalogItem);

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(projectComponentsService).getRepoPathFromCatalogItemId(catalogItemId);
    }

    @Test
    void givenEmptyProjectComponentsList_whenAsCatalogItem_thenComponentCountIsZero() {
        // Given
        var itemEntityCtx = CatalogItemEntityContextMother.of();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken(null)
                .build();

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(Collections.emptyList());

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0))).thenReturn(expectedCatalogItem);

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(catalogApiAdapter).asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(0));
    }

    @Test
    void givenMultipleProjectsWithMultipleComponents_whenAsCatalogItem_thenCountsMatchingComponentsAcrossAllProjects() {
        // Given
        var itemEntityCtx = CatalogItemEntityContextMother.of();
        var catalogItemId = itemEntityCtx.getId();

        var userActionsEntity = UserActionsEntityMother.of();
        Set<CatalogEntityPermissionEnum> permissions = Collections.emptySet();

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemEntityContext(itemEntityCtx)
                .userActionsEntity(userActionsEntity)
                .permissions(permissions)
                .accessToken(null)
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

        when(provisionerActionsService.getAllProjectComponentsProjectKeys()).thenReturn(List.of("PRJ-1", "PRJ-2"));
        when(provisionerActionsService.getProjectComponents("PRJ-1")).thenReturn(projectComponents1);
        when(provisionerActionsService.getProjectComponents("PRJ-2")).thenReturn(projectComponents2);
        when(projectComponentsService.getRepoPathFromCatalogItemId(catalogItemId)).thenReturn(catalogItemId);

        CatalogItem expectedCatalogItem = CatalogItemMother.of();
        when(catalogApiAdapter.asCatalogItem(eq(catalogRequestParams), anyList(), anyList(), eq(2))).thenReturn(expectedCatalogItem);

        // When
        var result = catalogItemsApiFacade.asCatalogItem(catalogRequestParams);

        // Then
        assertThat(result).isSameAs(expectedCatalogItem);
        verify(provisionerActionsService, times(1)).getAllProjectComponentsProjectKeys();
        verify(provisionerActionsService).getProjectComponents("PRJ-1");
        verify(provisionerActionsService).getProjectComponents("PRJ-2");
    }

}
