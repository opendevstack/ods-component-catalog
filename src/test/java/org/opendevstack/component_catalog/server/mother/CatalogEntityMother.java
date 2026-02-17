package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;

public class CatalogEntityMother {

    public static CatalogEntity of() {
        var catalogEntity = new CatalogEntity();

        catalogEntity.setMetadata(CatalogEntityMetadataMother.of());

        return catalogEntity;
    }
}
