package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityMetadata;

import java.util.List;

public class CatalogEntityMetadataMother {

    public static CatalogEntityMetadata of() {
        var catalogEntityMetadata = new CatalogEntityMetadata();

        catalogEntityMetadata.setName("Catalog Name");
        catalogEntityMetadata.setDescription("Catalog Description");
        catalogEntityMetadata.setCommunityPage("./community.md");
        catalogEntityMetadata.setOwners(List.of("owner1", "owner2"));

        catalogEntityMetadata.setSpec(CatalogEntitySpecMother.of());

        return catalogEntityMetadata;
    }
}
