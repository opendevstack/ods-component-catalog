package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
        var catalogItemId = IdEncoderDecoder.idDecode(component.getCatalogItemId());
        var catalogItemRef = IdEncoderDecoder.idDecode(component.getCatalogItemRef());
        var catalogItemRefWithParamDelimiter = catalogItemRef.startsWith("?at=") ? catalogItemRef : "?at=" + catalogItemRef;

        var idWithRef = catalogItemId + catalogItemRefWithParamDelimiter;
        var encodedIdWithRef = IdEncoderDecoder.idEncode(idWithRef);

        var catalogRequestParams = CatalogRequestParams.builder()
                .catalogItemId(encodedIdWithRef)
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
            log.debug("Catalog item with id '{}' could not be fetched. Returning empty project technology.", encodedIdWithRef, iciee);

            return ProjectTechnology.builder().build();
        }
    }
}
