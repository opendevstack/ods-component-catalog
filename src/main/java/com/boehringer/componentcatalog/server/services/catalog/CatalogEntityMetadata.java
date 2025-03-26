package com.boehringer.componentcatalog.server.services.catalog;

import lombok.Data;

@Data
public class CatalogEntityMetadata {
    private String name;
    private String description;
    private CatalogEntitySpec spec;
}
