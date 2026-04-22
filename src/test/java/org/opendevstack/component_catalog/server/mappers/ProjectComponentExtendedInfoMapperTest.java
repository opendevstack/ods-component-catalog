package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.controllers.exceptions.ComponentNotFoundException;
import org.opendevstack.component_catalog.server.model.ProjectComponentExtendedInfo;
import org.opendevstack.component_catalog.server.model.ProjectComponentParameter;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectComponentExtendedInfoMapperTest {

    @Mock
    private ProjectComponentParameterMapper projectComponentParameterMapper;

    private ProjectComponentExtendedInfoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProjectComponentExtendedInfoMapper(projectComponentParameterMapper);
    }

    @Test
    void givenComponentWithParameters_whenMap_thenReturnExtendedInfo() {
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

        // when
        Optional<ProjectComponentExtendedInfo> result =
                mapper.mapToProjectComponentExtendedInfo(component);

        // then
        assertThat(result).isPresent();
        var info = result.get();

        assertThat(info.getComponentId()).isEqualTo("C1");
        assertThat(info.getCatalogItemId()).isEqualTo("CAT-1");
        assertThat(info.getCatalogItemRef()).isEqualTo("REF-1");
        assertThat(info.getStatus()).isEqualTo("CREATED");

        assertThat(info.getParameters()).hasSize(2);
        assertThat(info.getParameters()).containsExactly(mappedParam1, mappedParam2);

        verify(projectComponentParameterMapper).mapToProjectComponentParameter(param1);
        verify(projectComponentParameterMapper).mapToProjectComponentParameter(param2);
    }

    @Test
    void givenComponentWithNullParameters_whenMap_thenReturnEmptyParametersList() {
        // given
        ProjectComponent component = ProjectComponentMother.of(
                "C2",
                "CAT-2",
                "REF-2",
                Status.CREATING
        );
        component.setParameters(null);

        // when
        Optional<ProjectComponentExtendedInfo> result =
                mapper.mapToProjectComponentExtendedInfo(component);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getParameters()).isEmpty();

        verifyNoInteractions(projectComponentParameterMapper);
    }

    @Test
    void givenParameterMappingReturnsEmptyOptional_whenMap_thenThrowComponentNotFoundException() {
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
                mapper.mapToProjectComponentExtendedInfo(component)
        ).isInstanceOf(ComponentNotFoundException.class)
                .hasMessageContaining("C404");

        verify(projectComponentParameterMapper).mapToProjectComponentParameter(param);
    }
}