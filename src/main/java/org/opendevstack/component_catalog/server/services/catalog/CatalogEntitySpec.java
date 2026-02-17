package org.opendevstack.component_catalog.server.services.catalog;

import lombok.Data;

import java.util.List;

@Data
public class CatalogEntitySpec {
    private CatalogEntityLink[] links;
    private List<String> tags;
    private CatalogEntityTarget[] targets;
}
