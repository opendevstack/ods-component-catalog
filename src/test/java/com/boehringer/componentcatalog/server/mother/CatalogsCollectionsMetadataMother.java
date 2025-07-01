package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsMetadata;

public class CatalogsCollectionsMetadataMother {

    public static CatalogsCollectionsMetadata of() {
        var catalogOfCatalogsMetadata = new CatalogsCollectionsMetadata();

        catalogOfCatalogsMetadata.setName("catalogOfCatalogs");
        catalogOfCatalogsMetadata.setDescription("A catalog of catalogs");
        catalogOfCatalogsMetadata.setSpec(CatalogsCollectionsSpecMother.of());

        return catalogOfCatalogsMetadata;
    }
}
