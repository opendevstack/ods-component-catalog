package org.opendevstack.component_catalog.server.services.provisioner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This bean directly maps a project-components/projects/PROJECT-KEY.json file
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectComponents {
    // The key is the componentId, the value is the project component
    private Map<String, ProjectComponent> components;
}
