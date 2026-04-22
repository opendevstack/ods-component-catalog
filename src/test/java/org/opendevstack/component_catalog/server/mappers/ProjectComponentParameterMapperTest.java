package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opendevstack.component_catalog.server.model.ProjectComponentParameter;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectComponentParameterMapperTest {

    private ProjectComponentParameterMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProjectComponentParameterMapper();
    }

    @Test
    void givenValidParameter_whenMap_thenReturnMappedProjectComponentParameter() {
        // given
        Parameter param = Parameter.builder()
                .name("param-1")
                .values(List.of("v1", "v2"))
                .build();

        // when
        Optional<ProjectComponentParameter> result =
                mapper.mapToProjectComponentParameter(param);

        // then
        assertThat(result).isPresent();

        ProjectComponentParameter mapped = result.get();
        assertThat(mapped.getName()).isEqualTo("param-1");
        assertThat(mapped.getValues()).containsExactly("v1", "v2");
    }

    @Test
    void givenParameterWithNullFields_whenMap_thenReturnMappedWithNulls() {
        // given
        Parameter param = Parameter.builder()
                .name(null)
                .values(null)
                .build();

        // when
        Optional<ProjectComponentParameter> result =
                mapper.mapToProjectComponentParameter(param);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isNull();
        assertThat(result.get().getValues()).isNull();
    }
}