package org.opendevstack.component_catalog.server.services.filters;

import org.opendevstack.component_catalog.server.model.CatalogItem;

import java.util.List;

// Evaluators are triggered AFTER merge parameters on items.
public interface CatalogItemsFilter {

    boolean filter(CatalogItem item, List<String> params);
}
