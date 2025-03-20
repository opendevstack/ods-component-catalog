package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

import java.util.Map;

/**
 * This bean directly maps a CatalogItem.yaml file
 */
@Data
public class CatalogItemEntity {
    private String kind;
    private CatalogItemEntityMetadata metadata;
}
