package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

/**
 * This bean directly maps a CatalogOfCatalogs.yaml file in the catalog of catalogs repo
 */
@Data
public class CatalogsCollectionsEntity {
    private String kind;
    private CatalogsCollectionsMetadata metadata;
}
