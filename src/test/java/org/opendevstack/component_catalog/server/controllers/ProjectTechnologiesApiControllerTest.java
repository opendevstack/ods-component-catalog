package org.opendevstack.component_catalog.server.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.facade.ProjectTechnologiesFacade;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTechnologiesApiControllerTest {

    @Mock
    private ProjectTechnologiesFacade projectTechnologiesFacade;

    @InjectMocks
    private ProjectTechnologiesApiController projectTechnologiesApiController;

    @Test
    void givenProjectKey_whenGetProjectTechnologies_thenReturnOkWithTechnologies() {
        // given
        var projectKey = "PRJ-123";
        var technologies = List.of(ProjectTechnology.builder().slug("java").name("Java").build());
        when(projectTechnologiesFacade.getProjectTechnologies(projectKey)).thenReturn(technologies);

        // when
        var response = projectTechnologiesApiController.getProjectTechnologies(projectKey);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyElementsOf(technologies);
        verify(projectTechnologiesFacade).getProjectTechnologies(projectKey);
    }
}
