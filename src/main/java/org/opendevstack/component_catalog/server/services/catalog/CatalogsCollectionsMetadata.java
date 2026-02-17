package org.opendevstack.component_catalog.server.services.catalog;

import lombok.Data;

@Data
public class CatalogsCollectionsMetadata {
    private String name;
    private String description;
    private CatalogsCollectionsSpec spec;
}
