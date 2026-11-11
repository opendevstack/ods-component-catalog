package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.ProjectTechnology;
import org.opendevstack.component_catalog.server.services.catalog.InvalidCatalogItemEntityException;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponents;
import org.springframework.stereotype.Component;

import java.util.List;
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

        // Cleaning up the list of project technologies to remove any entries that have a null or empty slug
        projectTechnologies.removeIf(pt -> pt.getSlug() == null || pt.getSlug().isEmpty());

        log.trace("Mapped project components {} to project technologies {}", projectComponentList, projectTechnologies);
        return projectTechnologies;
    }

    @SneakyThrows
    private ProjectTechnology asProjectTechnology(ProjectComponent component) {
        var encodedIdWithRef = getEncodedIdWithRef(component);

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

    private static String getEncodedIdWithRef(ProjectComponent component) throws InvalidIdException {
        var catalogItemIdParam = Optional.ofNullable(component.getParameters())
                .orElseGet(List::of)
                .stream()
                .filter(p -> p.getName().equals("catalog_item_id"))
                .map(Parameter::getValues)
                .flatMap(List::stream)
                .findAny();


        if (catalogItemIdParam.isPresent()) {
            return catalogItemIdParam.get();
        } else {
            log.debug("Project component {} does not have a catalog_item_id parameter. Using catalogItemId and catalogItemRef to build the encoded id with ref.", component);

            var catalogItemId = IdEncoderDecoder.idDecode(component.getCatalogItemId());
            var catalogItemRef = IdEncoderDecoder.idDecode(component.getCatalogItemRef());
            var catalogItemRefWithParamDelimiter = catalogItemRef.startsWith("?at=") ? catalogItemRef : "?at=" + catalogItemRef;

            var idWithRef = catalogItemId + catalogItemRefWithParamDelimiter;
            var encodedIdWithRef = IdEncoderDecoder.idEncode(idWithRef);

            return encodedIdWithRef;
        }
    }
}
