package com.boehringer.componentcatalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

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
