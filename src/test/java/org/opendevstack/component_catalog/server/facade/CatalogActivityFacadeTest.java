package org.opendevstack.component_catalog.server.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.CatalogActivityMapper;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentMother;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.model.CatalogActivityMother;
import org.opendevstack.component_catalog.server.model.PaginatedCatalogActivities;
import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.model.SortParameter;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogActivityFacadeTest {

    @Mock
    private ProjectComponentsFacade projectComponentsFacade;

    @Mock
    private CatalogEntitiesService catalogEntitiesService;

    @Mock
    private ProjectsInfoService projectsInfoService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private CatalogActivityMapper catalogActivityMapper;

    @InjectMocks
    private CatalogActivityFacade catalogActivityFacade;

    private final String catalogId = "catalog-id";
    private final SortParameter sortParameter = SortParameter.CREATION_DATE;
    private final SortOrder sortOrder = SortOrder.ASC;
    private final String project = null;
    private final String status = null;
    private final Long startDate = null;
    private final Long endDate = null;

    @BeforeEach
    void setUp() {
        lenient().when(authenticationFacade.getAccessToken()).thenReturn("token");
    }

    @Test
    void givenValidCatalogId_whenGetCatalogActivitiesById_ThenReturnActivitiesList() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath = "projects/PROJ/repos/my-repo/raw/CatalogItem.yaml";
        String encodedId = IdEncoderDecoder.idEncode(rawPath);
        ProjectComponent pc = ProjectComponentMother.of("C1", encodedId, "ref", Status.CREATED);
        ProjectComponents pcs = ProjectComponents.builder().components(Map.of("k1", pc)).build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity activity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/my-repo")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc))).thenReturn(activity);

        // when
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, sortParameter, sortOrder, project, status, startDate, endDate);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
        assertThat(result.getFirst().getProjectKey()).isEqualTo("PROJ");

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verify(projectsInfoService, times(1)).getProjectGroups("token");
        verify(projectComponentsFacade, times(1)).getAllProjectComponents();
        verify(catalogActivityMapper, times(1)).asCatalogActivity(eq("PROJ"), anyString(), eq(pc));
    }

    @Test
    void givenUserNotAdminForCatalog_whenGetCatalogActivitiesById_ThenThrowForbiddenException() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("OTHER-GROUP"));

        // when
        var exception = assertThrows(ForbiddenException.class, () ->
                catalogActivityFacade.getCatalogActivities(catalogId, sortParameter, sortOrder, project, status, startDate, endDate)
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("User is not admin for catalog owner groups");

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verify(projectsInfoService, times(1)).getProjectGroups("token");
        verifyNoInteractions(projectComponentsFacade);
    }

    @Test
    void givenFacadeThrowsElementNotFound_whenGetCatalogActivitiesById_ThenPropagateException() throws Exception {
        // given
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> catalogActivityFacade.getCatalogActivities(catalogId, sortParameter, sortOrder, project, status, startDate, endDate))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("Catalog entity not found");

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verifyNoInteractions(projectComponentsFacade);
    }

    @ParameterizedTest
    @EnumSource(
            value = SortParameter.class,
            names = {"CREATION_DATE", "PROJECT", "STATUS"}
    )
    void givenSortParameter_whenGetCatalogActivities_thenApplySorting(SortParameter sortParameter) throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath = "projects/PROJ/repos/repo/raw/CatalogItem.yaml";
        String encodedId = IdEncoderDecoder.idEncode(rawPath);
        ProjectComponent pc = ProjectComponentMother.of("C1", encodedId, "ref", Status.CREATED);
        ProjectComponents pcs = ProjectComponents.builder()
                .components(Map.of("k1", pc))
                .build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity activity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc)))
                .thenReturn(activity);

        // when
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(
                catalogId, sortParameter, sortOrder, null, null, null, null);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
    }

    @Test
    void givenStatus_whenGetCatalogActivities_thenFilterByStatus() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath1 = "projects/PROJ/repos/repo-1/raw/CatalogItem.yaml";
        String rawPath2 = "projects/PROJ/repos/repo-2/raw/CatalogItem.yaml";
        String encodedId1 = IdEncoderDecoder.idEncode(rawPath1);
        String encodedId2 = IdEncoderDecoder.idEncode(rawPath2);
        ProjectComponent pc1 = ProjectComponentMother.of("C1", encodedId1, "ref", Status.CREATED);
        ProjectComponent pc2 = ProjectComponentMother.of("C2", encodedId2, "ref", Status.FAILED);
        ProjectComponents pcs = ProjectComponents.builder()
                .components(Map.of("k1", pc1, "k2", pc2))
                .build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity createdActivity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo-1")
                .status(CatalogActivity.StatusEnum.CREATED)
                .createdAt(new BigDecimal("100"))
                .build();
        CatalogActivity failedActivity = CatalogActivity.builder()
                .componentId("C2")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo-2")
                .status(CatalogActivity.StatusEnum.FAILED)
                .createdAt(new BigDecimal("200"))
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc1))).thenReturn(createdActivity);
        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc2))).thenReturn(failedActivity);

        // when
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, sortParameter, sortOrder, project, CatalogActivity.StatusEnum.CREATED.getValue(), startDate, endDate);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
        assertThat(result.getFirst().getStatus()).isEqualTo(CatalogActivity.StatusEnum.CREATED);

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verify(projectsInfoService, times(1)).getProjectGroups("token");
        verify(projectComponentsFacade, times(1)).getAllProjectComponents();
        verify(catalogActivityMapper, times(1)).asCatalogActivity(eq("PROJ"), anyString(), eq(pc1));
        verify(catalogActivityMapper, times(1)).asCatalogActivity(eq("PROJ"), anyString(), eq(pc2));
    }

    @Test
    void givenDateRange_whenGetCatalogActivities_thenFilterByDateRange() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath1 = "projects/PROJ/repos/repo-1/raw/CatalogItem.yaml";
        String rawPath2 = "projects/PROJ/repos/repo-2/raw/CatalogItem.yaml";
        String rawPath3 = "projects/PROJ/repos/repo-3/raw/CatalogItem.yaml";
        String encodedId1 = IdEncoderDecoder.idEncode(rawPath1);
        String encodedId2 = IdEncoderDecoder.idEncode(rawPath2);
        String encodedId3 = IdEncoderDecoder.idEncode(rawPath3);
        ProjectComponent pc1 = ProjectComponentMother.of("C1", encodedId1, "ref", Status.CREATED);
        ProjectComponent pc2 = ProjectComponentMother.of("C2", encodedId2, "ref", Status.CREATED);
        ProjectComponent pc3 = ProjectComponentMother.of("C3", encodedId3, "ref", Status.CREATED);
        ProjectComponents pcs = ProjectComponents.builder()
                .components(Map.of("k1", pc1, "k2", pc2, "k3", pc3))
                .build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity beforeRangeActivity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo-1")
                .status(CatalogActivity.StatusEnum.CREATED)
                .createdAt(new BigDecimal("99"))
                .build();
        CatalogActivity startBoundaryActivity = CatalogActivity.builder()
                .componentId("C2")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo-2")
                .status(CatalogActivity.StatusEnum.CREATED)
                .createdAt(new BigDecimal("100"))
                .build();
        CatalogActivity endBoundaryActivity = CatalogActivity.builder()
                .componentId("C3")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo-3")
                .status(CatalogActivity.StatusEnum.CREATED)
                .createdAt(new BigDecimal("150"))
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc1))).thenReturn(beforeRangeActivity);
        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc2))).thenReturn(startBoundaryActivity);
        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc3))).thenReturn(endBoundaryActivity);

        // when
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, sortParameter, sortOrder, project, null, 100L, 150L);

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(CatalogActivity::getComponentId).containsExactly("C2", "C3");
        assertThat(result).extracting(CatalogActivity::getCreatedAt).containsExactly(new BigDecimal("100"), new BigDecimal("150"));

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verify(projectsInfoService, times(1)).getProjectGroups("token");
        verify(projectComponentsFacade, times(1)).getAllProjectComponents();
        verify(catalogActivityMapper, times(1)).asCatalogActivity(eq("PROJ"), anyString(), eq(pc1));
        verify(catalogActivityMapper, times(1)).asCatalogActivity(eq("PROJ"), anyString(), eq(pc2));
        verify(catalogActivityMapper, times(1)).asCatalogActivity(eq("PROJ"), anyString(), eq(pc3));
    }

    @Test
    void givenAListOfCatalogActivities_whenPaginateCatalogActivities_ThenReturnPaginatedResults() {
        // given
        var activities = List.of(
                CatalogActivityMother.of(),
                CatalogActivityMother.of("component-1"),
                CatalogActivityMother.of("component-2")
        );
        var page = 1;
        var size = 2;
        var baseUrl = "https://component-catalog.myserver.com";

        // when
        PaginatedCatalogActivities result = catalogActivityFacade.paginateCatalogActivities(activities, page, size, baseUrl);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getData()).hasSize(1);
        assertThat(result.getData().getFirst().getComponentId()).isEqualTo("component-2");
        assertThat(result.getPagination()).isNotNull();
        assertThat(result.getPagination().getPage()).isEqualTo(page);
    }
}


