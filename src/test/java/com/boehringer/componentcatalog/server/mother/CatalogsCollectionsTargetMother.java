package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsTarget;

public class CatalogsCollectionsTargetMother {

    public static CatalogsCollectionsTarget of(String slug) {
        var catalogOfCatalogsTarget = new CatalogsCollectionsTarget();

        catalogOfCatalogsTarget.setUrl("/path/to/" + slug);
        catalogOfCatalogsTarget.setSlug(slug);

        return catalogOfCatalogsTarget;
    }
}
