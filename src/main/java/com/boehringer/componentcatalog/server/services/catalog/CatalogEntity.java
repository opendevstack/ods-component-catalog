package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

/**
 * This bean directly maps a Catalog.yaml file
 */
@Data
public class CatalogEntity {
    private String kind;
    private CatalogEntityMetadata metadata;
}

