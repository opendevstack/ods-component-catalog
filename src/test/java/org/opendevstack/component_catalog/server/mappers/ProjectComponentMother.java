package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

public class ProjectComponentMother {

    public static ProjectComponent of() {
        return of("componentId1", "catalogItemId1", "catalogItemRef1", Status.CREATED);
    }

    public static ProjectComponent of(String componentId, String catalogItemId, String catalogItemRef, Status status) {
        return of(componentId, catalogItemId, catalogItemRef, status, "http://component.url");
    }

    public static ProjectComponent of(String componentId, String catalogItemId, String catalogItemRef, Status status, String componentUrl) {
        return ProjectComponent.builder()
                .componentId(componentId)
                .catalogItemId(catalogItemId)
                .status(status)
                .catalogItemRef(catalogItemRef)
                .componentUrl(componentUrl)
                .workflowJobId("12345")
                .build();
    }
}
