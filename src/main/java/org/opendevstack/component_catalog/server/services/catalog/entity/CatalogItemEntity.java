package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityMetadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This bean directly maps a CatalogItem.yaml file
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemEntity {
    private String kind;
    private CatalogItemEntityMetadata metadata;
    private CatalogItemEntitySpec spec;
}
