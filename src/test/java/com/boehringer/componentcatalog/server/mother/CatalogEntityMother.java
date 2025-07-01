package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogEntity;

public class CatalogEntityMother {

    public static CatalogEntity of() {
        var catalogEntity = new CatalogEntity();

        catalogEntity.setMetadata(CatalogEntityMetadataMother.of());

        return catalogEntity;
    }
}
