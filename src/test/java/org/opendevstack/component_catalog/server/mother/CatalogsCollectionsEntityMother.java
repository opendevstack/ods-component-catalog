package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntity;

public class CatalogsCollectionsEntityMother {

    public static CatalogsCollectionsEntity of() {
        var catalogOfCatalogsEntity = new CatalogsCollectionsEntity();
        catalogOfCatalogsEntity.setMetadata(CatalogsCollectionsMetadataMother.of());

        return catalogOfCatalogsEntity;
    }
}
