package org.opendevstack.component_catalog.server.services.catalog;

import lombok.Data;

@Data
public class CatalogEntityMetadata {
    private String name;
    private String description;
    private String communityPage;
    private CatalogEntitySpec spec;
}
