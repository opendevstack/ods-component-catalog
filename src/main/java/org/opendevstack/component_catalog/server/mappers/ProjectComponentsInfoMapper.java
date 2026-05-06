package org.opendevstack.component_catalog.server.mappers;

import org.openapitools.jackson.nullable.JsonNullable;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.CatalogItem;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.model.ProjectComponentInfo;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idDecode;
import static org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder.idEncode;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectComponentsInfoMapper {
    private final CatalogItemsApiFacade catalogItemsApiFacade;
    private final ApplicationPropertiesConfiguration.CatalogItemDefaultProps catalogItemDefaultProps;

    public Optional<ProjectComponentInfo> mapToProjectComponentInfo(ProjectComponent comp,
                                                                    String accessToken,
                                                                    String projectKey,
                                                                    List<String> userGroups) throws InvalidIdException {
        if (isNotValid(comp, accessToken, projectKey)) {
            return Optional.empty();
        }

        List<String> catalogItemIdWithRef = List.of(comp.getCatalogItemId(),
                comp.getCatalogItemRef() == null ? catalogItemDefaultProps.getReference().getEncoded() : comp.getCatalogItemRef());

        var params = CatalogRequestParams.builder()
                .catalogItemId(encodeMultipartId(catalogItemIdWithRef))
                .projectKey(projectKey)
                .build();

        CatalogItem catalogItem = catalogItemsApiFacade.fetchCatalogItem(params);

        var logoUrl = "";
        var hasAutomatedDeletionWorkflow = false;

        if (catalogItem == null) {
            log.warn("Catalog item not found for component {} with catalogItemId {} and catalogItemRef {}",
                    comp.getComponentId(), comp.getCatalogItemId(), comp.getCatalogItemRef());
        } else {
            logoUrl = Optional.of(catalogItem)
                    .map(CatalogItem::getImageFileId)
                    .filter(StringUtils::isNotBlank)
                    .orElse("");

            var deletionWorkflow = extractDeletionWorkflow(catalogItem);
            hasAutomatedDeletionWorkflow = deletionWorkflow != null;
        }

        var pci = ProjectComponentInfo.builder()
                .componentId(comp.getComponentId())
                .componentUrl(comp.getComponentUrl())
                .status(comp.getStatus().toString())
                .logoUrl(logoUrl)
                .canBeDeleted(containsManagerOrTeam(userGroups, projectKey))
                .hasAutomatedDeletionWorkflow(hasAutomatedDeletionWorkflow)
                .build();

        return Optional.of(pci);
    }

    protected boolean containsManagerOrTeam(List<String> groups, String projectKey) {
        if (groups == null) return false;
        return groups.stream()
                .filter(Objects::nonNull)
                .anyMatch(g -> (g.toUpperCase().contains("MANAGER") || g.toUpperCase().contains("TEAM")) &&
                        (g.toUpperCase().contains(projectKey.toUpperCase())));
    }

    protected String encodeMultipartId(List<String> multipartId) throws InvalidIdException {
        StringBuilder decodedIdBuilder = new StringBuilder();
        for (String id : multipartId) {
            decodedIdBuilder.append(idDecode(id));
        }
        return idEncode(decodedIdBuilder.toString());
    }

    protected boolean isNotValid(ProjectComponent projectComponent,
                                 String accessToken,
                                 String projectKey) {
        return (projectComponent == null || projectComponent.getCatalogItemId() == null || StringUtils.isBlank(accessToken)
                || StringUtils.isBlank(projectKey));
    }

    private String extractDeletionWorkflow(CatalogItem catalogItem) {
        var provisionAction = catalogItem.getUserActions().stream()
                .filter(action -> "PROVISION".equals(action.getId()))
                .findAny();

        var deletionWorkflow = provisionAction.flatMap(action -> action.getParameters().stream()
                .filter(param -> "deletion_workflow".equals(param.getName()))
                .findAny()
                .map(CatalogItemUserActionParameter::getDefaultValue)
                .map(JsonNullable::get))
                .orElse(null);

        log.debug("Extracted deletion workflow '{}' for catalog item '{}'", deletionWorkflow, catalogItem.getId());

        return deletionWorkflow;
    }
}
