package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsEntity;

public class CatalogsCollectionsEntityMother {

    public static CatalogsCollectionsEntity of() {
        var catalogOfCatalogsEntity = new CatalogsCollectionsEntity();
        catalogOfCatalogsEntity.setMetadata(CatalogsCollectionsMetadataMother.of());

        return catalogOfCatalogsEntity;
    }
}
