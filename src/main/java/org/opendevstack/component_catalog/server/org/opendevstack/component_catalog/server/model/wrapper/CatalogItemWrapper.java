package org.opendevstack.component_catalog.server.org.opendevstack.component_catalog.server.model.wrapper;

import org.opendevstack.component_catalog.server.model.CatalogItem;

public record CatalogItemWrapper(CatalogItem catalogItem, boolean valid) {
}
