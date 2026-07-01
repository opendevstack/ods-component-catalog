package org.opendevstack.component_catalog.server.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.model.ProjectComponentMetrics;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProjectComponentListItemMapperTest {

    @Mock
    private ProvisionerActionsConfiguration provisionerActionsConfiguration;

    @Mock
    private ApplicationPropertiesConfiguration.BitbucketServiceProps bitbucketServiceProps;

    private ProjectComponentMetricsMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProjectComponentMetricsMapper(
                provisionerActionsConfiguration,
                bitbucketServiceProps
        );
    }

    @Test
    void givenValidComponent_whenMap_thenReturnsMappedObject() {
        // given
        var projectKey = "PROJECT_KEY";

        var parameter = new Parameter();
        parameter.setName("caller");
        parameter.setValues(List.of("user@test.com"));

        var component = new ProjectComponent();
        component.setComponentId("comp-1");
        component.setParameters(List.of(parameter));
        component.setCatalogItemId(null); // Proper URL decoding is done in another test suite. See IdEncoderDecoderTest
        component.setCreatedAt("1000");
        component.setUpdatedAt("2000");

        // when
        Optional<ProjectComponentMetrics> result =
                mapper.mapToProjectComponentMetrics(component, projectKey);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getProjectKey()).isEqualTo(projectKey);
        assertThat(result.get().getComponentId()).isEqualTo("comp-1");
        assertThat(result.get().getCaller()).isEqualTo("user@test.com");
        assertThat(result.get().getCreatedAt()).isNotNull();
        assertThat(result.get().getUpdatedAt()).isNotNull();
    }

    @Test
    void givenNullParameters_whenMap_thenCallerIsNull() {
        // given
        var projectKey = "PROJECT_KEY";

        var component = new ProjectComponent();
        component.setComponentId("comp-1");
        component.setParameters(null);
        component.setCatalogItemId(null); // Proper URL decoding is done in another test suite. See IdEncoderDecoderTest
        component.setCreatedAt("1000");
        component.setUpdatedAt("2000");

        // when
        Optional<ProjectComponentMetrics> result =
                mapper.mapToProjectComponentMetrics(component, projectKey);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getCaller()).isNull();
    }

    @Test
    void givenInvalidCatalogItemId_whenMap_thenSlugIsNull() {
        // given
        var projectKey = "PROJECT_KEY";

        var component = new ProjectComponent();
        component.setComponentId("comp-1");
        component.setCatalogItemId(null);
        component.setCreatedAt("1000");
        component.setUpdatedAt("2000");

        // when
        Optional<ProjectComponentMetrics> result =
                mapper.mapToProjectComponentMetrics(component, projectKey);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getCatalogItemSlug()).isNull();
    }

    @Test
    void givenNullDates_whenMap_thenDatesAreNull() {
        // given
        var projectKey = "PROJECT_KEY";

        var component = new ProjectComponent();
        component.setComponentId("comp-1");
        component.setCreatedAt(null);
        component.setUpdatedAt(null);

        // when
        Optional<ProjectComponentMetrics> result =
                mapper.mapToProjectComponentMetrics(component, projectKey);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getCreatedAt()).isNull();
        assertThat(result.get().getUpdatedAt()).isNull();
    }

    @Test
    void givenCompletelyInvalidProjectComponent_whenMap_thenOptionalPresentValueIsReturned() {
        // given
        var projectKey = "PROJECT_KEY";

        var component = new ProjectComponent();
        component.setComponentId("comp-1");
        component.setComponentUrl("wrong:url^");
        component.setCatalogItemId(null);
        component.setCatalogItemRef(null);
        component.setParameters(
                List.of(Parameter.builder()
                    .name("caller")
                    .values(null) // Expected value is a list
                .build())
        );
        component.setCreatedAt("invalid");
        component.setUpdatedAt("2000");

        // when
        Optional<ProjectComponentMetrics> result =
                mapper.mapToProjectComponentMetrics(component, projectKey);

        // then
        assertThat(result.isPresent());
    }
}