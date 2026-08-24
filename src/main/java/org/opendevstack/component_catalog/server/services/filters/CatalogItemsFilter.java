package org.opendevstack.component_catalog.server.services.filters;

import org.opendevstack.component_catalog.server.model.CatalogItem;

import java.util.List;

public interface CatalogItemsFilter {

    boolean filter(CatalogItem item, List<String> params);
}
