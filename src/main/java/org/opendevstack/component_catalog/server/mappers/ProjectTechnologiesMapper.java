package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectTechnologiesMapper {

    private final CatalogItemsApiFacade catalogItemsApiFacade;

    public Set<ProjectTechnology> asProjectTechnologies(ProjectComponents projectComponents) {
        var catalogItemIds = projectComponents.getComponents()
                .values()
                    .stream()
                .map(ProjectComponent::getParameters)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                    .filter(parameter -> parameter.getName().equals("catalog_item_id"))
                    .distinct()
                .map(parameter -> parameter.getValues().stream().findAny())
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                .toList();

        var projectTechnologiesSet = catalogItemIds.stream()
        .map(this::asProjectTechnology)
        .collect(Collectors.toSet());

        // Cleaning up the list of project technologies to remove any entries that have a null or empty slug
        projectTechnologiesSet.removeIf(pt -> pt.getSlug() == null || pt.getSlug().isEmpty());

        log.trace("Mapped project components {} to project technologies {}", projectComponents, projectTechnologiesSet);
        return projectTechnologiesSet;
    }

    @SneakyThrows
    private ProjectTechnology asProjectTechnology(String catalogItemId) {
        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemId(catalogItemId)
                .ignoreVisibilityRestrictions(true)
                .build();

        try {
            var catItem = catalogItemsApiFacade.fetchCatalogItem(catalogRequestParams);

            return Optional.ofNullable(catItem)
                    .map(item -> ProjectTechnology.builder()
                            .slug(item.getSlug())
                            .name(item.getTitle())
                            .build())
                    .orElse(ProjectTechnology.builder().build());
        } catch (InvalidCatalogItemEntityException iciee) {
            log.debug("Catalog item with id '{}' could not be fetched. Returning empty project technology.", catalogItemId, iciee);

            return ProjectTechnology.builder().build();
        }
    }

}
