package org.opendevstack.component_catalog.server.org.opendevstack.component_catalog.server.model.wrapper;

import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;

public record CatalogItemUserActionWrapper (CatalogItemUserAction catalogItemUserAction, boolean valid) {
}
