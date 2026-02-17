package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityTarget;

public class CatalogEntityTargetMother {
    public static CatalogEntityTarget of(String path) {
        var catalogEntityTarget = new CatalogEntityTarget();

        catalogEntityTarget.setUrl("http://example.com/" + path);

        return catalogEntityTarget;
    }
}
