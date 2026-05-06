package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionMother;
import org.opendevstack.component_catalog.server.mother.CatalogItemUserActionParameterMother;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectComponentsInfoMapperTest {
    private final String token = "token";
    private final String projectKey = "projectKey";

    @Mock
    private ApplicationPropertiesConfiguration.CatalogItemDefaultProps  catalogItemDefaultProps;

    @Mock
    private CatalogItemsApiFacade catalogItemsApiFacade;

    private ProjectComponentsInfoMapper projectComponentsInfoMapper;

    @BeforeEach
    void setUp() {
        projectComponentsInfoMapper = new ProjectComponentsInfoMapper(catalogItemsApiFacade, catalogItemDefaultProps);
    }

    @Test
    void givenValidComponent_whenFetchOk_thenReturnOptionalWithMappedInfo() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var componentId = "C-100";
        var catalogItemId = "cat-100";

        var component = ProjectComponentMother.of(componentId, catalogItemId, "ref-100", Status.CREATED, "https://www.google.com");
        var catalogItem = CatalogItemMother.of("cat-001", "logo-100.png");

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        ProjectComponentInfo info = maybeInfo.get();

        assertThat(info.getComponentId()).isEqualTo(componentId);
        assertThat(info.getComponentUrl()).isEqualTo("https://www.google.com");
        assertThat(info.getStatus()).isEqualTo("CREATED");
        assertThat(info.getLogoUrl()).isEqualTo("logo-100.png");
        assertThat(info.getHasAutomatedDeletionWorkflow()).isFalse();
        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(argThat(p ->
                p != null && ("ce-_vX7vv71N77-977-977-977-9TQ==").equals(p.getCatalogItemId())
        ));
    }

    @Test
    void givenCatalogItemWithBlankLogo_whenMap_thenLogoUrlIsEmptyString() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-200", "cat-200", "ref-200", Status.CREATING);
        var catalogItem = CatalogItemMother.of("cat-001", "     ");

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getLogoUrl()).isEqualTo("");
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isFalse();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenCatalogItemWithNullLogo_whenMap_thenLogoUrlIsEmptyString() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-201", "cat-201", "ref-201", Status.DELETING);
        var catalogItem = CatalogItemMother.of("cat-001", null);

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getLogoUrl()).isEqualTo("");
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isFalse();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenFetchThrowsInvalidIdException_whenMap_thenPropagateException() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-ERR", "cat-ERR", "ref-ERR", Status.UNKNOWN);

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenThrow(new InvalidIdException("invalid id"));

        // when / then
        assertThatThrownBy(() -> projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of()))
                .isInstanceOf(InvalidIdException.class)
                .hasMessageContaining("invalid id");

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenDifferentStatuses_whenMap_thenStatusIsEnumName() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(CatalogItemMother.of("CI1", "logo.png"));

        var c1 = ProjectComponentMother.of("C1", "Q0lELTE", "UkVGLTE", Status.CREATED);
        var c2 = ProjectComponentMother.of("C2", "Q0lELTI", "UkVGLTE", Status.CREATING);
        var c3 = ProjectComponentMother.of("C3", "Q0lELTM", "UkVGLTE", Status.DELETING);
        var c4 = ProjectComponentMother.of("C4", "Q0lELTQ", "UkVGLTE", Status.UNKNOWN);

        // when / then
        assertThat(projectComponentsInfoMapper.mapToProjectComponentInfo(c1, token, projectKey, List.of())).get()
                .extracting(ProjectComponentInfo::getStatus).isEqualTo("CREATED");
        assertThat(projectComponentsInfoMapper.mapToProjectComponentInfo(c2, token, projectKey, List.of())).get()
                .extracting(ProjectComponentInfo::getStatus).isEqualTo("CREATING");
        assertThat(projectComponentsInfoMapper.mapToProjectComponentInfo(c3, token, projectKey, List.of())).get()
                .extracting(ProjectComponentInfo::getStatus).isEqualTo("DELETING");
        assertThat(projectComponentsInfoMapper.mapToProjectComponentInfo(c4, token, projectKey, List.of())).get()
                .extracting(ProjectComponentInfo::getStatus).isEqualTo("UNKNOWN");

        verify(catalogItemsApiFacade, times(4)).fetchCatalogItem(any());
    }

    @Test
    void givenCatalogItemWithProvisionActionAndDeletionWorkflow_whenMap_thenHasAutomatedDeletionWorkflowIsTrue() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-300", "cat-300", "ref-300", Status.CREATED);
        var deletionWorkflowParam = CatalogItemUserActionParameterMother.of("deletion_workflow", "string")
                .defaultValue("some-workflow-id");
        var provisionAction = CatalogItemUserActionMother.of("PROVISION", List.of(deletionWorkflowParam));
        var catalogItem = CatalogItemMother.of("cat-001", "logo.png", List.of(provisionAction));

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isTrue();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenCatalogItemWithProvisionActionButNoDeletionWorkflow_whenMap_thenHasAutomatedDeletionWorkflowIsFalse() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-301", "cat-301", "ref-301", Status.CREATED);
        var otherParam = CatalogItemUserActionParameterMother.of("other_param", "string");
        var provisionAction = CatalogItemUserActionMother.of("PROVISION", List.of(otherParam));
        var catalogItem = CatalogItemMother.of("cat-001", "logo.png", List.of(provisionAction));

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isFalse();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenCatalogItemWithNoProvisionAction_whenMap_thenHasAutomatedDeletionWorkflowIsFalse() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-302", "cat-302", "ref-302", Status.CREATED);
        var otherAction = CatalogItemUserActionMother.of("OTHER", List.of());
        var catalogItem = CatalogItemMother.of("cat-001", "logo.png", List.of(otherAction));

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isFalse();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenCatalogItemWithProvisionActionAndNullDeletionWorkflow_whenMap_thenHasAutomatedDeletionWorkflowIsFalse() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-303", "cat-303", "ref-303", Status.CREATED);
        var deletionWorkflowParam = CatalogItemUserActionParameterMother.of("deletion_workflow", "string")
                .defaultValue(null);
        var provisionAction = CatalogItemUserActionMother.of("PROVISION", List.of(deletionWorkflowParam));
        var catalogItem = CatalogItemMother.of("cat-001", "logo.png", List.of(provisionAction));

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isFalse();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }

    @Test
    void givenCatalogItemWithProvisionAction_andEmptyDeletionWorkflow_whenMap_thenHasAutomatedDeletionWorkflowIsFalse() throws InvalidIdException, InvalidCatalogItemEntityException {
        // given
        var component = ProjectComponentMother.of("C-304", "cat-304", "ref-304", Status.CREATED);
        var deletionWorkflowParam = CatalogItemUserActionParameterMother.of("deletion_workflow", "string")
                .defaultValue("");
        var provisionAction = CatalogItemUserActionMother.of("PROVISION", List.of(deletionWorkflowParam));
        var catalogItem = CatalogItemMother.of("cat-001", "logo.png", List.of(provisionAction));

        when(catalogItemsApiFacade.fetchCatalogItem(any()))
                .thenReturn(catalogItem);

        // when
        Optional<ProjectComponentInfo> maybeInfo = projectComponentsInfoMapper.mapToProjectComponentInfo(component, token, projectKey, List.of());

        // then
        assertThat(maybeInfo).isPresent();
        assertThat(maybeInfo.get().getHasAutomatedDeletionWorkflow()).isFalse();

        verify(catalogItemsApiFacade, times(1)).fetchCatalogItem(any());
    }
}
