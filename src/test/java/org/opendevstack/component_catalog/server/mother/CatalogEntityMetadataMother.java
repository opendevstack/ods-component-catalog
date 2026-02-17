package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;

public class CatalogEntityMetadataMother {

    public static CatalogEntityMetadata of() {
        var catalogEntityMetadata = new CatalogEntityMetadata();

        catalogEntityMetadata.setName("Catalog Name");
        catalogEntityMetadata.setDescription("Catalog Description");
        catalogEntityMetadata.setCommunityPage("./community.md");

        catalogEntityMetadata.setSpec(CatalogEntitySpecMother.of());

        return catalogEntityMetadata;
    }
}
