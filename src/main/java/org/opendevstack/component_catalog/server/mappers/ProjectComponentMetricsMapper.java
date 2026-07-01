package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.config.ApplicationPropertiesConfiguration;
import org.opendevstack.component_catalog.config.ProvisionerActionsConfiguration;
import org.opendevstack.component_catalog.server.model.ProjectComponentMetrics;
import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.common.IdEncoderDecoder;
import org.opendevstack.component_catalog.server.services.exceptions.InvalidIdException;
import org.opendevstack.component_catalog.server.services.provisioner.Parameter;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class ProjectComponentMetricsMapper {

    private final ProvisionerActionsConfiguration provisionerActionsConfiguration;

    @Qualifier("bitbucketServiceConfig")
    private final ApplicationPropertiesConfiguration.BitbucketServiceProps bitbucketServiceProps;

    public Optional<ProjectComponentMetrics> mapToProjectComponentMetrics(ProjectComponent comp, String projectKey) {
        try {
            return Optional.of(ProjectComponentMetrics.builder()
                    .projectKey(projectKey)
                    .componentId(comp.getComponentId())
                    .caller(getParameterValueByName(Optional.ofNullable(comp.getParameters()).orElse(List.of()), "caller"))
                    .catalogItemSlug(getCatalogItemSlug(comp.getCatalogItemId()))
                    .createdAt(parseBigDecimal(comp.getCreatedAt()))
                    .updatedAt(parseBigDecimal(comp.getUpdatedAt()))
                    .build());
        } catch (Exception e) {
            log.error("Error trying to map project component {} from project {} to ProjectComponentListItem.", comp.getComponentId(), projectKey);
            return Optional.empty();
        }
    }

    private String getParameterValueByName(List<Parameter> parameters, String paramName) {
        return parameters.stream()
                .filter(p -> p.getName().equalsIgnoreCase(paramName))
                .findFirst()
                .map(Parameter::getValues)
                .map(List::getFirst)
                .orElse( null);
    }

    private String getCatalogItemSlug(String catalogItemId) {
        if (catalogItemId == null || catalogItemId.isBlank() || catalogItemId.equalsIgnoreCase("null")) {
            return null;
        }

        String decodedUrl;
        try {
            decodedUrl = IdEncoderDecoder.idDecode(catalogItemId).concat("?at=").concat(provisionerActionsConfiguration.getBranchName());
        } catch (InvalidIdException e) {
            log.error("Could not decode catalogItemId {}", catalogItemId);
            return null;
        }

        // Mocked values for base URLs since we won't be using them
        BitbucketPathAt pathAt;
        try {
            pathAt = BitbucketPathAt.builder()
                        .pathAt(decodedUrl)
                        .baseRawUrl(bitbucketServiceProps.getBaseRawUrl().toString())
                        .baseRestUrl(bitbucketServiceProps.getBaseRestUrl().toString())
                        .build();
        } catch (Exception e) {
            log.error("Could not extract catalog item from this, presumably, incorrect Bitbucket path: {}", decodedUrl);
            return null;
        }

        return pathAt.getRepoSlug();
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return BigDecimal.valueOf(Long.parseLong(value));
    }
}
