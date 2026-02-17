package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityLink;

public class CatalogEntityLinkMother {

    public static CatalogEntityLink of(String name) {
        var catalogEntityLink = new CatalogEntityLink();

        catalogEntityLink.setUrl("http://example.com/" + name);
        catalogEntityLink.setName(name);

        return catalogEntityLink;
    }
}
