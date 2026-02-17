package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.server.controllers.CatalogRequestParams;
import org.opendevstack.component_catalog.server.facade.CatalogItemsApiFacade;
import org.opendevstack.component_catalog.server.model.CatalogItem;
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
                .build();

        CatalogItem catalogItem = catalogItemsApiFacade.fetchCatalogItem(params);

        var pci = ProjectComponentInfo.builder()
                .componentId(comp.getComponentId())
                .componentUrl(comp.getComponentUrl())
                .status(comp.getStatus().toString())
                .logoUrl(StringUtils.isBlank(catalogItem.getImageFileId()) ? "" : catalogItem.getImageFileId())
                .canBeDeleted(containsManagerOrTeam(userGroups, projectKey))
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
}
