package org.opendevstack.component_catalog.server.facade;

import org.opendevstack.component_catalog.server.mappers.ProjectComponentMother;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProjectComponentsMother {
    public static ProjectComponents of(Map<String, ProjectComponent> components) {
        var pc = new ProjectComponents();
        pc.setComponents(components);

        return pc;
    }

    public static ProjectComponents of() {
        ProjectComponent c1 = ProjectComponentMother.of("C1", "cat-1", "ref-1", Status.CREATED);
        ProjectComponent c2 = ProjectComponentMother.of("C2", "cat-2", "ref-2", Status.CREATING);

        var comps = new LinkedHashMap<String, ProjectComponent>();
        comps.put("k1", c1);
        comps.put("k2", c2);

        return ProjectComponentsMother.of(comps);
    }
}
