package org.opendevstack.component_catalog.server.services.catalog;

import lombok.Data;

import java.util.List;

@Data
public class CatalogEntityMetadata {
    private String name;
    private String description;
    private String communityPage;
    private CatalogEntitySpec spec;
    private List<String> owners;
}
