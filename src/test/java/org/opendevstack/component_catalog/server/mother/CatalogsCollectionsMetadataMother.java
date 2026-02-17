package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityMetadata;

public class CatalogsCollectionsMetadataMother {

    public static CatalogsCollectionsEntityMetadata of() {
        var catalogOfCatalogsMetadata = new CatalogsCollectionsEntityMetadata();

        catalogOfCatalogsMetadata.setName("catalogOfCatalogs");
        catalogOfCatalogsMetadata.setDescription("A catalog of catalogs");
        catalogOfCatalogsMetadata.setSpec(CatalogsCollectionsSpecMother.of());

        return catalogOfCatalogsMetadata;
    }
}
