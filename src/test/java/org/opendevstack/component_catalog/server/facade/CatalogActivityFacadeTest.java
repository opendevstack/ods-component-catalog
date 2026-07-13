package org.opendevstack.component_catalog.server.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.mappers.CatalogActivityMapper;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentMother;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.mother.CatalogEntityMother;
import org.opendevstack.component_catalog.server.services.CatalogEntitiesService;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.exceptions.ElementNotFoundException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    private final String sort = "creationDate";
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
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate);

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
    void givenEmptyResult_whenGetCatalogActivitiesById_ThenReturnEmptyList() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("OTHER-GROUP"));

        // when
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate);

        // then
        assertThat(result).isEmpty();

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verify(projectsInfoService, times(1)).getProjectGroups("token");
        verifyNoInteractions(projectComponentsFacade);
    }

    @Test
    void givenFacadeThrowsElementNotFound_whenGetCatalogActivitiesById_ThenPropagateException() throws Exception {
        // given
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> catalogActivityFacade.getCatalogActivities(catalogId, sort, project, status, startDate, endDate))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessageContaining("Catalog entity not found");

        verify(catalogEntitiesService, times(1)).getCatalogEntity(catalogId);
        verifyNoInteractions(projectComponentsFacade);
    }

    @Test
    void givenSortByCreationDate_whenGetCatalogActivities_ThenApplySorting() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath = "projects/PROJ/repos/repo/raw/CatalogItem.yaml";
        String encodedId = IdEncoderDecoder.idEncode(rawPath);
        ProjectComponent pc = ProjectComponentMother.of("C1", encodedId, "ref", Status.CREATED);
        ProjectComponents pcs = ProjectComponents.builder().components(Map.of("k1", pc)).build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity activity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc))).thenReturn(activity);

        // when - filter by sort parameter "creationDate"
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, "creationDate", null, null, null, null);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
    }

    @Test
    void givenSortByProject_whenGetCatalogActivities_ThenApplySorting() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath = "projects/PROJ/repos/repo/raw/CatalogItem.yaml";
        String encodedId = IdEncoderDecoder.idEncode(rawPath);
        ProjectComponent pc = ProjectComponentMother.of("C1", encodedId, "ref", Status.CREATED);
        ProjectComponents pcs = ProjectComponents.builder().components(Map.of("k1", pc)).build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity activity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc))).thenReturn(activity);

        // when - filter by sort parameter "project"
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, "project", null, null, null, null);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
    }

    @Test
    void givenSortByStatus_whenGetCatalogActivities_ThenApplySorting() throws Exception {
        // given
        CatalogEntity entity = CatalogEntityMother.of();
        when(catalogEntitiesService.getCatalogEntity(catalogId)).thenReturn(Optional.of(entity));
        when(projectsInfoService.getProjectGroups("token")).thenReturn(List.of("owner1"));

        String rawPath = "projects/PROJ/repos/repo/raw/CatalogItem.yaml";
        String encodedId = IdEncoderDecoder.idEncode(rawPath);
        ProjectComponent pc = ProjectComponentMother.of("C1", encodedId, "ref", Status.CREATED);
        ProjectComponents pcs = ProjectComponents.builder().components(Map.of("k1", pc)).build();

        when(projectComponentsFacade.getAllProjectComponents()).thenReturn(Map.of("PROJ", pcs));

        CatalogActivity activity = CatalogActivity.builder()
                .componentId("C1")
                .projectKey("PROJ")
                .catalogItemSlug("proj/repo")
                .status(CatalogActivity.StatusEnum.CREATED)
                .build();

        when(catalogActivityMapper.asCatalogActivity(eq("PROJ"), anyString(), eq(pc))).thenReturn(activity);

        // when - filter by sort parameter "status"
        List<CatalogActivity> result = catalogActivityFacade.getCatalogActivities(catalogId, "status", null, null, null, null);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
    }
}


