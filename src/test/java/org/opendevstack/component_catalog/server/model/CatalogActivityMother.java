package org.opendevstack.component_catalog.server.model;

import java.math.BigDecimal;

public class CatalogActivityMother {

    public static CatalogActivity of() {
        return of("component-id");
    }

    public static CatalogActivity of(String componentId) {
        return CatalogActivity.builder()
                .componentId(componentId)
                .catalogItemSlug("catalog-item-slug")
                .projectKey("project-key")
                .status(ProvisioningStatus.CREATING)
                .createdAt(new BigDecimal("1783949171"))
                .build();
    }
}
