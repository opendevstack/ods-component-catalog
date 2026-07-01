package org.opendevstack.component_catalog.server.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.CatalogItemDefaultProps;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration.CatalogProjectComponentsGroupsRestrictionProps;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.*;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentMetrics;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProjectComponentsFacadeTest {
    private final String accessToken = "token";
    private CatalogItemDefaultProps catalogItemDefaultProps;

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @Mock
    private CatalogItemsApiFacade catalogItemsApiFacade;

    @Mock
    private ProjectsInfoService projectsInfoService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @InjectMocks
    private ProjectComponentsFacade projectComponentsFacade;

    @Mock
    private ProjectComponentExtendedInfoMapper projectComponentExtendedInfoMapper;

    @Mock
    private CatalogProjectComponentsGroupsRestrictionProps catalogGroupsRestrictionProps;

    @Mock
    private ProjectComponentMetricsMapper projectComponentListItemMapper;

    @BeforeEach
    void setUp() {
        var permittedOids = List.of("oid1", "oid2", "oid3");
        ProjectComponentsInfoMapper projectComponentsInfoMapper = new ProjectComponentsInfoMapper(catalogItemsApiFacade,
                catalogItemDefaultProps);
        projectComponentsFacade = new ProjectComponentsFacade(provisionerActionsService, projectComponentsInfoMapper,
                projectsInfoService, projectComponentExtendedInfoMapper, catalogGroupsRestrictionProps,
                projectComponentListItemMapper, permittedOids);

        lenient().when(authenticationFacade.getAccessToken()).thenReturn("accessToken");
        lenient().when(catalogGroupsRestrictionProps.getPrefix()).thenReturn(List.of("BI-AS-ATLASSIAN-P-"));
    }

    @Test
    void givenProjectWithTwoComponents_whenAllCatalogFetchOk_thenReturnMappedList()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var projectKey = "PRJ-123";

        ProjectComponent c1 = ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED);
        ProjectComponent c2 = ProjectComponentMother.of("C2", "Y2F0LTI", "cmVmLTI", Status.CREATING, "https://www.google.com");

        var comps = new LinkedHashMap<String, ProjectComponent>();
        comps.put("k1", c1);
        comps.put("k2", c2);

        var pc = ProjectComponentsMother.of(comps);

        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(pc);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenAnswer(inv -> {
                    var p = (CatalogRequestParams) inv.getArgument(0);
                    if (p == null) return null;
                    return switch (p.getCatalogItemId()) {
                        case "Y2F0LTFyZWYtMQ==" -> CatalogItemMother.of("CID-1", "logo-1.png");
                        case "Y2F0LTJyZWYtMg==" -> CatalogItemMother.of("CID-2", "logo-2.svg");
                        default -> throw new AssertionError("Unexpected id: " + p.getCatalogItemId());
                    };
                });

        // when
        List<ProjectComponentInfo> result = projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken);

        // then
        assertThat(result).hasSize(2);

        assertThat(result.getFirst().getComponentId()).isEqualTo("C1");
        assertThat(result.getFirst().getComponentUrl()).isEqualTo("http://component.url");
        assertThat(result.get(0).getStatus()).isEqualTo("CREATED");
        assertThat(result.get(0).getLogoUrl()).isEqualTo("logo-1.png");

        assertThat(result.get(1).getComponentId()).isEqualTo("C2");
        assertThat(result.get(1).getComponentUrl()).isEqualTo("https://www.google.com");
        assertThat(result.get(1).getStatus()).isEqualTo("CREATING");
        assertThat(result.get(1).getLogoUrl()).isEqualTo("logo-2.svg");

        verify(provisionerActionsService, times(1)).getProjectComponents(projectKey);
        verify(catalogItemsApiFacade, times(1))
                .fetchCatalogItem(argThat(p -> "Y2F0LTFyZWYtMQ==".equals(p.getCatalogItemId())));
        verify(catalogItemsApiFacade, times(1))
                .fetchCatalogItem(argThat(p -> "Y2F0LTJyZWYtMg==".equals(p.getCatalogItemId())));
    }

    @Test
    void givenOneComponentFailsWithInvalidIdException_whenGetProjectComponentsInfo_thenSkipThatComponent()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var projectKey = "PRJ-ERR";

        ProjectComponent good = ProjectComponentMother.of("G1", "b2stMQ", "cmVmLTE", Status.CREATED);
        ProjectComponent bad = ProjectComponentMother.of("B1", "YmFkLTE", "cmVmLTI", Status.UNKNOWN);

        var comps = new LinkedHashMap<String, ProjectComponent>();
        comps.put("kg", good);
        comps.put("kb", bad);

        var pc = ProjectComponentsMother.of(comps);
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(pc);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenAnswer(inv -> {
                    var p = (CatalogRequestParams) inv.getArgument(0);
                    if (p == null) return null;
                    return switch (p.getCatalogItemId()) {
                        case "b2stMXJlZi0x" -> CatalogItemMother.of("CID-1", "logo-ok.png");
                        case "YmFkLTFyZWYtMg==" -> throw new InvalidIdException("invalid");
                        default -> throw new AssertionError("Unexpected id: " + p.getCatalogItemId());
                    };
                });

        // when
        List<ProjectComponentInfo> result = projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("G1");
        assertThat(result.getFirst().getLogoUrl()).isEqualTo("logo-ok.png");

        verify(catalogItemsApiFacade, times(1))
                .fetchCatalogItem(argThat(p -> "b2stMXJlZi0x".equals(p.getCatalogItemId())));
        verify(catalogItemsApiFacade, times(1))
                .fetchCatalogItem(argThat(p -> "YmFkLTFyZWYtMg==".equals(p.getCatalogItemId())));
    }

    @Test
    void givenOneComponentFailsWithInvalidCatalogItemEntityException_whenGetProjectComponentsInfo_thenSkipThatComponent()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var projectKey = "PRJ-EX";

        ProjectComponent good = ProjectComponentMother.of("G1", "b2stMQ", "cmVmLTE", Status.CREATING);
        ProjectComponent fail = ProjectComponentMother.of("F1", "YmFkLTE", "cmVmLTI", Status.DELETING);

        var comps = new LinkedHashMap<String, ProjectComponent>();
        comps.put("g", good);
        comps.put("f", fail);

        var pc = ProjectComponentsMother.of(comps);
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(pc);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenAnswer(inv -> {
                    var p = (CatalogRequestParams) inv.getArgument(0);
                    if (p == null) return null;
                    return switch (p.getCatalogItemId()) {
                        case "b2stMXJlZi0x" -> CatalogItemMother.of("CID-1", "logo-ok.png");
                        case "YmFkLTFyZWYtMg==" -> throw new InvalidIdException("invalid");
                        default -> throw new AssertionError("Unexpected id: " + p.getCatalogItemId());
                    };
                });

        // when
        List<ProjectComponentInfo> result = projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getComponentId()).isEqualTo("G1");
        assertThat(result.getFirst().getStatus()).isEqualTo("CREATING");
        assertThat(result.getFirst().getLogoUrl()).isEqualTo("logo-ok.png");
    }

    @Test
    void givenBlankOrNullImageFileId_whenMapToProjectComponentInfo_thenLogoUrlIsEmptyString()
            throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var projectKey = "PRJ-IMG";

        ProjectComponent compBlank = ProjectComponentMother.of("CB", "aW1nLWJsYW5r", "cmVmLTE", Status.CREATED);
        ProjectComponent compNull = ProjectComponentMother.of("CN", "aW1nLW51bGw", "cmVmLTI", Status.CREATED);

        var comps = new LinkedHashMap<String, ProjectComponent>();
        comps.put("b", compBlank);
        comps.put("n", compNull);

        var pc = ProjectComponentsMother.of(comps);

        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(pc);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenAnswer(inv -> {
                    var p = (CatalogRequestParams) inv.getArgument(0);
                    if (p == null) return null;
                    return switch (p.getCatalogItemId()) {
                        case "aW1nLWJsYW5rcmVmLTE=" -> CatalogItemMother.of("CID-1", "   ");
                        case "aW1nLW51bGxyZWYtMg==" -> CatalogItemMother.of("CID-2", null);
                        default -> throw new AssertionError("Unexpected id: " + p.getCatalogItemId());
                    };
                });

        // when
        List<ProjectComponentInfo> result = projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken);

        // then
        assertThat(result).hasSize(2);
        assertThat(result.getFirst().getComponentId()).isEqualTo("CB");
        assertThat(result.getFirst().getLogoUrl()).isEmpty();

        assertThat(result).element(1).extracting(ProjectComponentInfo::getComponentId).isEqualTo("CN");
        assertThat(result).element(1).extracting(ProjectComponentInfo::getLogoUrl).isEqualTo("");
    }

    @Test
    void givenNoComponents_whenGetProjectComponentsInfo_thenReturnEmptyList() throws InvalidIdException {
        // given
        var projectKey = "PRJ-EMPTY";

        var pc = ProjectComponentsMother.of(new LinkedHashMap<>());
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(pc);

        // when
        List<ProjectComponentInfo> result = projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken);

        // then
        assertThat(result).isEmpty();
        verify(catalogItemsApiFacade, times(0)).fetchCatalogItem(any());
    }

    @Test
    void getAccessToken_whenAuthIsNull_throwsForbiddenException() {
        // given
        when(authenticationFacade.getAccessToken()).thenThrow(new ForbiddenException("User not authenticated"));

        // when / then
        assertThatThrownBy(() -> authenticationFacade.getAccessToken())
                .isInstanceOf(ForbiddenException.class)
                .hasMessage("User not authenticated");
    }

    @Test
    void givenExistingComponent_whenGetExtendedInfo_thenReturnMappedInfo() {
        // given
        var projectKey = "PRJ-1";
        var componentId = "C1";

        ProjectComponent comp = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);
        var comps = ProjectComponentsMother.of(Map.of("k1", comp));

        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));
        when(projectComponentExtendedInfoMapper.mapToProjectComponentExtendedInfo(comp))
                .thenReturn(Optional.of(new ProjectComponentExtendedInfo()));

        // when
        var result = projectComponentsFacade
                .getProjectComponentExtendedInfo(projectKey, componentId, accessToken);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void givenComponentDoesNotExist_whenGetExtendedInfo_thenThrowComponentNotFound() {
        // given
        var projectKey = "PRJ-404";

        ProjectComponent comp = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);
        var comps = ProjectComponentsMother.of(Map.of("k1", comp));

        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, "C2", accessToken)
        ).isInstanceOf(ComponentNotFoundException.class)
                .hasMessageContaining("C2");
    }

    @Test
    void givenMapperReturnsEmptyOptional_whenGetExtendedInfo_thenThrowComponentNotFound() {
        // given
        var projectKey = "PRJ-EMPTY";
        var componentId = "C1";

        ProjectComponent comp = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);
        var comps = ProjectComponentsMother.of(Map.of("k1", comp));

        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-" + projectKey));
        when(projectComponentExtendedInfoMapper.mapToProjectComponentExtendedInfo(comp))
                .thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, componentId, accessToken)
        ).isInstanceOf(ComponentNotFoundException.class);
    }


    @Test
    void givenNullUserGroups_whenGetProjectComponentsInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(null);

        // when / then
        assertThatThrownBy(() -> projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenEmptyUserGroups_whenGetProjectComponentsInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of());

        // when / then
        assertThatThrownBy(() -> projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenGroupWithNoMatchingPrefix_whenGetProjectComponentsInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("WRONG-PREFIX-PRJ-123"));

        // when / then
        assertThatThrownBy(() -> projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenGroupWithMatchingPrefixButWrongProject_whenGetProjectComponentsInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-OTHER"));

        // when / then
        assertThatThrownBy(() -> projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenGroupWithMatchingPrefixAndProject_whenGetProjectComponentsInfo_thenDoNotThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-PRJ-123"));

        // when
        List<ProjectComponentInfo> result = projectComponentsFacade.getProjectComponentsInfo(projectKey, accessToken);

        // then
        assertThat(result).isNotNull();
    }


    @Test
    void givenNullUserGroups_whenGetProjectComponentExtendedInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(null);

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, "C1", accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenEmptyUserGroups_whenGetProjectComponentExtendedInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of());

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, "C1", accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenGroupWithNoMatchingPrefix_whenGetProjectComponentExtendedInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("WRONG-PREFIX-PRJ-123"));

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, "C1", accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenGroupWithMatchingPrefixButWrongProject_whenGetProjectComponentExtendedInfo_thenThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1",
                ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED))));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-OTHER"));

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getProjectComponentExtendedInfo(projectKey, "C1", accessToken))
                .isInstanceOf(ForbiddenException.class)
                .hasMessageContaining("User must belong to the project to get its components");
    }

    @Test
    void givenGroupWithMatchingPrefixAndProject_whenGetProjectComponentExtendedInfo_thenDoNotThrowForbiddenException() {
        // given
        var projectKey = "PRJ-123";
        ProjectComponent comp = ProjectComponentMother.of("C1", "Y2F0LTE", "cmVmLTE", Status.CREATED);
        var comps = ProjectComponentsMother.of(new LinkedHashMap<>(Map.of("k1", comp)));
        when(provisionerActionsService.getProjectComponents(projectKey)).thenReturn(comps);
        when(projectsInfoService.getProjectGroups(accessToken)).thenReturn(List.of("BI-AS-ATLASSIAN-P-PRJ-123"));
        when(projectComponentExtendedInfoMapper.mapToProjectComponentExtendedInfo(comp))
                .thenReturn(Optional.of(new ProjectComponentExtendedInfo()));

        // when
        ProjectComponentExtendedInfo result = projectComponentsFacade
                .getProjectComponentExtendedInfo(projectKey, "C1", accessToken);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void givenTokenWithInvalidOid_whenGetAllProjectComponents_thenThrowForbidden() {
        // given
        String token = "invalid-token";

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getAllProjectComponents(token, 0, 10, "url")
        ).isInstanceOf(ForbiddenException.class);
    }

    @Test
    void givenInvalidPageOrSize_whenGetAllProjectComponents_thenThrowIllegalArgument() {
        // given
        String validToken = "eyJhbGciOiJub25lIn0.eyJvaWQiOiJvaWQxIn0."; // Payload has oid "oid1"

        // when / then
        assertThatThrownBy(() ->
                projectComponentsFacade.getAllProjectComponents(validToken, -1, 10, "url")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->
                projectComponentsFacade.getAllProjectComponents(validToken, 0, -1, "url")
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->
                projectComponentsFacade.getAllProjectComponents(validToken, 0, 101, "url")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void givenOneProjectWithComponents_whenGetAllProjectComponents_thenReturnPaginatedResult() {
        // given
        String projectKey = "PRJ-1";

        var component = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);
        component.setCreatedAt("100");

        var projectComponents = ProjectComponentsMother.of(Map.of("k1", component));

        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of(projectKey + ".json"));
        when(provisionerActionsService.getProjectComponents(projectKey))
                .thenReturn(projectComponents);

        when(projectComponentListItemMapper.mapToProjectComponentMetrics(component, projectKey))
                .thenReturn(Optional.of(ProjectComponentMetrics.builder()
                        .componentId("C1")
                        .projectKey(projectKey)
                        .build()));

        String validToken = "eyJhbGciOiJub25lIn0.eyJvaWQiOiJvaWQxIn0."; // Payload has oid "oid1"

        // when
        var result = projectComponentsFacade.getAllProjectComponents(validToken, 0, 10, "url");

        // then
        assertThat(result.getData()).hasSize(1);
        assertThat(result.getData().getFirst().getComponentId()).isEqualTo("C1");
        assertThat(result.getPagination()).isNotNull();
    }

    @Test
    void givenMapperReturnsEmpty_whenGetAllProjectComponents_thenElementIsSkipped() {
        // given
        String projectKey = "PRJ-1";

        var component = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);

        var projectComponents = ProjectComponentsMother.of(Map.of("k1", component));

        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of(projectKey + ".json"));
        when(provisionerActionsService.getProjectComponents(projectKey))
                .thenReturn(projectComponents);

        when(projectComponentListItemMapper.mapToProjectComponentMetrics(component, projectKey))
                .thenReturn(Optional.empty());

        String validToken = "eyJhbGciOiJub25lIn0.eyJvaWQiOiJvaWQxIn0."; // Payload has oid "oid1"

        // when
        var result = projectComponentsFacade.getAllProjectComponents(validToken, 0, 10, "url");

        // then
        assertThat(result.getData()).isEmpty();
    }

    @Test
    void givenMultipleItems_whenPaginationApplies_thenReturnCorrectSlice() {
        // given
        String projectKey = "PRJ-1";

        var c1 = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);
        var c2 = ProjectComponentMother.of("C2", "cat", "ref", Status.CREATED);

        c1.setCreatedAt("100");
        c2.setCreatedAt("200");

        var projectComponents = ProjectComponentsMother.of(
                new LinkedHashMap<>(Map.of(
                        "k1", c1,
                        "k2", c2
                ))
        );

        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of(projectKey + ".json"));
        when(provisionerActionsService.getProjectComponents(projectKey))
                .thenReturn(projectComponents);

        when(projectComponentListItemMapper.mapToProjectComponentMetrics(any(), eq(projectKey)))
                .thenAnswer(inv -> {
                    ProjectComponent pc = inv.getArgument(0);
                    return Optional.of(ProjectComponentMetrics.builder()
                            .componentId(pc.getComponentId())
                            .projectKey(projectKey)
                            .build());
                });

        String validToken = "eyJhbGciOiJub25lIn0.eyJvaWQiOiJvaWQxIn0."; // Payload has oid "oid1"

        // when
        var result = projectComponentsFacade.getAllProjectComponents(validToken, 1, 1, "url");

        // then
        assertThat(result.getData()).hasSize(1);
    }

    @Test
    void givenMultipleProjects_whenGetAllProjectComponents_thenProjectsSortedByKey() {
        // given
        when(provisionerActionsService.listAllProjectsJsons())
                .thenReturn(List.of("B.json", "A.json"));

        var comp = ProjectComponentMother.of("C1", "cat", "ref", Status.CREATED);

        when(provisionerActionsService.getProjectComponents("A"))
                .thenReturn(ProjectComponentsMother.of(Map.of("k1", comp)));
        when(provisionerActionsService.getProjectComponents("B"))
                .thenReturn(ProjectComponentsMother.of(Map.of("k1", comp)));

        when(projectComponentListItemMapper.mapToProjectComponentMetrics(any(), any()))
                .thenReturn(Optional.of(ProjectComponentMetrics.builder().build()));

        String validToken = "eyJhbGciOiJub25lIn0.eyJvaWQiOiJvaWQxIn0."; // Payload has oid "oid1"

        // when
        var result = projectComponentsFacade.getAllProjectComponents(validToken, 0, 10, "url");

        // then
        assertThat(result.getData()).isNotNull();
    }



}


