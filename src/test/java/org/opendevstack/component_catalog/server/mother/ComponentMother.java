package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

public class ComponentMother {

    public static ProjectComponent of(String componentId, String catalogItemId, Status status) {
        return ProjectComponent.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .status(status)
                .build();
    }
}
