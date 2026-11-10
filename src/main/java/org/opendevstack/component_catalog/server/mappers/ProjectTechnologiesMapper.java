package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectTechnologiesMapper {
    public List<ProjectTechnology> asProjectTechnologies(ProjectComponents projectComponents) {
        return null;
    }
}
