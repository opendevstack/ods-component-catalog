package org.opendevstack.component_catalog.server.mappers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opendevstack.component_catalog.server.model.CatalogActivity;
import org.opendevstack.component_catalog.server.services.provisioner.ProjectComponent;
import org.opendevstack.component_catalog.server.services.provisioner.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
@Slf4j
public class CatalogActivityMapper {

    public CatalogActivity asCatalogActivity(String projectKey, String catalogItemSlug, ProjectComponent projectComponent) {
        return CatalogActivity.builder()
                .catalogItemSlug(catalogItemSlug)
                .componentId(projectComponent.getComponentId())
                .projectKey(projectKey)
                .status(asStatusEnum(projectComponent.getStatus()))
                .createdAt(new BigDecimal(projectComponent.getCreatedAt()))
                .build();
    }

    public CatalogActivity.StatusEnum asStatusEnum(Status status) {
        return CatalogActivity.StatusEnum.valueOf(status.name());
    }
}
