package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityLink;

public class CatalogEntityLinkMother {

    public static CatalogEntityLink of(String name) {
        var catalogEntityLink = new CatalogEntityLink();

        catalogEntityLink.setUrl("http://example.com/" + name);
        catalogEntityLink.setName(name);

        return catalogEntityLink;
    }
}
