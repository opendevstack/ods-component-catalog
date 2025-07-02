package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityTarget;

public class CatalogEntityTargetMother {
    public static CatalogEntityTarget of(String path) {
        var catalogEntityTarget = new CatalogEntityTarget();

        catalogEntityTarget.setUrl("http://example.com/" + path);

        return catalogEntityTarget;
    }
}
