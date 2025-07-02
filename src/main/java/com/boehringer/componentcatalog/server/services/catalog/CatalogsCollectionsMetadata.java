package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

@Data
public class CatalogsCollectionsMetadata {
    private String name;
    private String description;
    private CatalogsCollectionsSpec spec;
}
