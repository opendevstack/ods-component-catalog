package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentParameter;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.model.ProvisioningStatus;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectComponentExtendedInfoMapperTest {

    private static final String ACCESS_TOKEN = "access-token";
    private static final String PROJECT_KEY = "project-key";
    private static final List<String> USER_GROUPS = List.of("project-key-team");

    @Mock
    private ProjectComponentParameterMapper projectComponentParameterMapper;

    @Mock
    private ProjectComponentsInfoMapper projectComponentsInfoMapper;

    private ProjectComponentExtendedInfoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProjectComponentExtendedInfoMapper(projectComponentParameterMapper, projectComponentsInfoMapper);
    }

    @Test
    void givenComponentWithParameters_whenMap_thenReturnExtendedInfo() throws InvalidIdException {
        // given
        Parameter param1 = Parameter.builder()
                .name("p1")
                .values(List.of("v1"))
                .build();

        Parameter param2 = Parameter.builder()
                .name("p2")
                .values(List.of("v2a", "v2b"))
                .build();

        ProjectComponentParameter mappedParam1 =
                ProjectComponentParameter.builder()
                        .name("p1")
                        .values(List.of("v1"))
                        .build();

        ProjectComponentParameter mappedParam2 =
                ProjectComponentParameter.builder()
                        .name("p2")
                        .values(List.of("v2a", "v2b"))
                        .build();

        when(projectComponentParameterMapper.mapToProjectComponentParameter(param1))
                .thenReturn(Optional.of(mappedParam1));
        when(projectComponentParameterMapper.mapToProjectComponentParameter(param2))
                .thenReturn(Optional.of(mappedParam2));

        ProjectComponent component = ProjectComponentMother.of(
                "C1",
                "CAT-1",
                "REF-1",
                Status.CREATED
        );
        component.setParameters(List.of(param1, param2));

        when(projectComponentsInfoMapper.mapToProjectComponentInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS))
            .thenReturn(Optional.of(ProjectComponentInfo.builder()
                .componentId("C1")
                .componentUrl(component.getComponentUrl())
                .status(ProvisioningStatus.CREATED)
                .canBeDeleted(true)
                .build()));

        // when
        Optional<ProjectComponentExtendedInfo> result =
            mapper.mapToProjectComponentExtendedInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS);

        // then
        assertThat(result).isPresent();
        var info = result.get();

        assertThat(info.getComponentId()).isEqualTo("C1");
        assertThat(info.getComponentUrl()).isEqualTo(component.getComponentUrl());
        assertThat(info.getCatalogItemId()).isEqualTo("CAT-1");
        assertThat(info.getCatalogItemRef()).isEqualTo("REF-1");
        assertThat(info.getStatus()).isEqualTo(ProvisioningStatus.CREATED);
        assertThat(info.getCanBeDeleted()).isTrue();

        assertThat(info.getParameters()).hasSize(2);
        assertThat(info.getParameters()).containsExactly(mappedParam1, mappedParam2);

        verify(projectComponentParameterMapper).mapToProjectComponentParameter(param1);
        verify(projectComponentParameterMapper).mapToProjectComponentParameter(param2);
        verify(projectComponentsInfoMapper).mapToProjectComponentInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS);
    }

    @Test
    void givenComponentWithNullParameters_whenMap_thenReturnEmptyParametersList() throws InvalidIdException {
        // given
        ProjectComponent component = ProjectComponentMother.of(
                "C2",
                "CAT-2",
                "REF-2",
                Status.CREATING
        );
        component.setParameters(null);
        when(projectComponentsInfoMapper.mapToProjectComponentInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS))
            .thenReturn(Optional.of(ProjectComponentInfo.builder()
                .componentId(component.getComponentId())
                .status(ProvisioningStatus.CREATING)
                .canBeDeleted(false)
                .build()));

        // when
        Optional<ProjectComponentExtendedInfo> result =
            mapper.mapToProjectComponentExtendedInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getParameters()).isEmpty();

        verifyNoInteractions(projectComponentParameterMapper);
    }

    @Test
    void givenParameterMappingReturnsEmptyOptional_whenMap_thenThrowComponentNotFoundException() throws InvalidIdException {
        // given
        Parameter param = Parameter.builder()
                .name("bad-param")
                .values(List.of("x"))
                .build();

        when(projectComponentParameterMapper.mapToProjectComponentParameter(param))
                .thenReturn(Optional.empty());

        ProjectComponent component = ProjectComponentMother.of(
                "C404",
                "CAT-X",
                "REF-X",
                Status.UNKNOWN
        );
        component.setParameters(List.of(param));

        // when / then
        assertThatThrownBy(() ->
            mapper.mapToProjectComponentExtendedInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS)
        ).isInstanceOf(ComponentNotFoundException.class)
                .hasMessageContaining("C404");

        verify(projectComponentParameterMapper).mapToProjectComponentParameter(param);
    }

    @Test
    void givenProjectComponentInfoMapperReturnsEmpty_whenMap_thenReturnEmpty() throws InvalidIdException {
        // given
        ProjectComponent component = ProjectComponentMother.of();
        when(projectComponentsInfoMapper.mapToProjectComponentInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS))
                .thenReturn(Optional.empty());

        // when / then
        assertThat(mapper.mapToProjectComponentExtendedInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS))
                .isEmpty();
    }

    @Test
    void givenProjectComponentInfoMapperThrowsInvalidIdException_whenMap_thenReturnEmpty() throws InvalidIdException {
        // given
        ProjectComponent component = ProjectComponentMother.of();
        when(projectComponentsInfoMapper.mapToProjectComponentInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS))
                .thenThrow(new InvalidIdException("invalid id"));

        // when / then
        assertThat(mapper.mapToProjectComponentExtendedInfo(component, ACCESS_TOKEN, PROJECT_KEY, USER_GROUPS))
                .isEmpty();
    }
}