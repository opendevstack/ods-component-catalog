package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.opendevstack.component_catalog.server.services.provisioner.Status;

import java.util.Map;

public class ProjectComponentsMother {

    public static ProjectComponents of(){
        return of(Status.CREATING);
    }

    public static ProjectComponents of(Status status){
        return of("componentId", "catalogItemId", status);
    }

    public static ProjectComponents of(String componentId, String catalogItemId, Status status) {
        var anotherComponentId = "anotherComponentId";

        var component = ComponentMother.of(componentId, catalogItemId, status);
        var anotherComponent = ComponentMother.of(anotherComponentId, "anotherCatalogItemId", Status.UNKNOWN);

        return of(Map.of(
                componentId, component,
                anotherComponentId, anotherComponent
        ));
    }

    public static ProjectComponents of(Map<String, ProjectComponent> components) {
        return ProjectComponents.builder()
                .components(components)
                .build();
    }
}
