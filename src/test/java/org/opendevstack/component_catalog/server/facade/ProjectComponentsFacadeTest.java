package org.opendevstack.component_catalog.server.facade;

import com.azure.spring.cloud.autoconfigure.implementation.aad.filter.UserPrincipal;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.mappers.CatalogItemMother;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentMother;
import org.opendevstack.component_catalog.server.mappers.ProjectComponentsInfoMapper;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.services.ProjectsInfoService;
import org.opendevstack.component_catalog.server.services.ProvisionerActionsService;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProjectComponentsFacadeTest {
    private final String accessToken = "token";
    private ApplicationPropertiesConfiguration.CatalogItemDefaultProps catalogItemDefaultProps;

    @Mock
    private ProvisionerActionsService provisionerActionsService;

    @Mock
    private CatalogItemsApiFacade catalogItemsApiFacade;

    @Mock
    private ProjectsInfoService projectsInfoService;

    @InjectMocks
    private ProjectComponentsFacade projectComponentsFacade;

    @BeforeEach
    void setUp() {
        ProjectComponentsInfoMapper projectComponentsInfoMapper = new ProjectComponentsInfoMapper(catalogItemsApiFacade,
                catalogItemDefaultProps);
        projectComponentsFacade = new ProjectComponentsFacade(provisionerActionsService, projectComponentsInfoMapper, projectsInfoService);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                new UserPrincipal("idToken", null, null),                // principal (can be UserDetails)
                "password",             // ignored
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );

        // Put it into the SecurityContext
        SecurityContextHolder.setContext(new SecurityContextImpl(auth));
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
        assertThat(result.getFirst().getComponentUrl()).isNull();
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
        assertThat(result.getFirst().getLogoUrl()).isEqualTo("");

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
}


