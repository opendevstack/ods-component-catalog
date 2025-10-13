package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

@Data
public class CatalogsCollectionsEntityMetadata {
    private String name;
    private String description;
    private CatalogsCollectionsEntitySpec spec;
}
