package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

public class ProjectComponentMother {
    public static ProjectComponent of(String componentId, String catalogItemId, String catalogItemRef, Status status, String componentUrl) {
        ProjectComponent c = new ProjectComponent();
        c.setComponentId(componentId);
        c.setCatalogItemId(catalogItemId);
        c.setStatus(status);
        c.setCatalogItemRef(catalogItemRef);
        c.setComponentUrl(componentUrl);
        return c;
    }

    public static ProjectComponent of(String componentId, String catalogItemId, String catalogItemRef, Status status) {
        ProjectComponent c = new ProjectComponent();
        c.setComponentId(componentId);
        c.setCatalogItemId(catalogItemId);
        c.setStatus(status);
        c.setCatalogItemRef(catalogItemRef);
        return c;
    }

    public static ProjectComponent of() {
        return of("componentId1", "catalogItemId1", "catalogItemRef1", Status.CREATED);
    }
}
