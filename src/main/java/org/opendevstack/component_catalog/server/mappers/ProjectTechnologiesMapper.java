package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectTechnologiesMapper {

    private final CatalogItemsApiFacade catalogItemsApiFacade;

    public Set<ProjectTechnology> asProjectTechnologies(ProjectComponents projectComponents) {
        var projectComponentList = projectComponents.getComponents()
                .values()
                    .stream()
                .toList();

        Set<ProjectTechnology> projectTechnologies =  projectComponentList.stream()
                .map(this::asProjectTechnology)
                .collect(Collectors.toSet());

        log.trace("Mapped project components {} to project technologies {}", projectComponentList, projectTechnologies);
        return projectTechnologies;
    }

    @SneakyThrows
    private ProjectTechnology asProjectTechnology(ProjectComponent component) {
        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemId(component.getCatalogItemId())
                .ignoreVisibilityRestrictions(true)
                .build();

        var catItem = catalogItemsApiFacade.fetchCatalogItem(catalogRequestParams);

        return ProjectTechnology.builder()
                .slug(catItem.getSlug())
                .name(catItem.getTitle())
                .build();
    }
}
