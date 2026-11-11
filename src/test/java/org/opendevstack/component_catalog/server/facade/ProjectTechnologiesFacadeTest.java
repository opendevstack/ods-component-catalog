package org.opendevstack.component_catalog.server.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opendevstack.component_catalog.server.controllers.exceptions.ForbiddenException;
import org.opendevstack.component_catalog.server.mappers.ProjectTechnologiesMapper;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTechnologiesFacadeTest {

    private final String projectKey = "PRJ-123";
    private final String accessToken = "application-token";

    @Mock
    private ProjectComponentsFacade projectComponentsFacade;

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private ProjectTechnologiesMapper projectTechnologiesMapper;

    @InjectMocks
    private ProjectTechnologiesFacade projectTechnologiesFacade;

    @Test
    void givenValidApplicationToken_whenGetProjectTechnologies_thenReturnMappedTechnologies() {
        // given
        var projectComponents = ProjectComponents.builder().build();
        var technology = ProjectTechnology.builder().slug("java").name("Java").build();
        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(authenticationFacade.isAValidApplicationToken(accessToken)).thenReturn(true);
        when(projectComponentsFacade.getAllProjectComponents(projectKey)).thenReturn(projectComponents);
        when(projectTechnologiesMapper.asProjectTechnologies(projectComponents)).thenReturn(Set.of(technology));

        // when
        var result = projectTechnologiesFacade.getProjectTechnologies(projectKey);

        // then
        assertThat(result).containsExactly(technology);
        verify(projectComponentsFacade).getAllProjectComponents(projectKey);
        verify(projectTechnologiesMapper).asProjectTechnologies(projectComponents);
    }

    @Test
    void givenInvalidApplicationToken_whenGetProjectTechnologies_thenThrowForbiddenException() {
        // given
        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(authenticationFacade.isAValidApplicationToken(accessToken)).thenReturn(false);

        // when / then
        assertThatThrownBy(() -> projectTechnologiesFacade.getProjectTechnologies(projectKey))
                .isInstanceOf(ForbiddenException.class)
                .hasMessage("Access denied. Invalid application token.");
        verifyNoInteractions(projectComponentsFacade, projectTechnologiesMapper);
    }

    @Test
    void givenNoProjectComponents_whenGetProjectTechnologies_thenReturnEmptyList() {
        // given
        when(authenticationFacade.getAccessToken()).thenReturn(accessToken);
        when(authenticationFacade.isAValidApplicationToken(accessToken)).thenReturn(true);
        when(projectComponentsFacade.getAllProjectComponents(projectKey)).thenReturn(null);

        // when
        List<ProjectTechnology> result = projectTechnologiesFacade.getProjectTechnologies(projectKey);

        // then
        assertThat(result).isEmpty();
        verifyNoInteractions(projectTechnologiesMapper);
    }
}
