package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;

public class CatalogsCollectionsEntityTargetMother {

    public static CatalogsCollectionsEntityTarget of() {
        return of("simple-slug");
    }

    public static CatalogsCollectionsEntityTarget of(String slug) {
        var catalogOfCatalogsTarget = new CatalogsCollectionsEntityTarget();

        catalogOfCatalogsTarget.setUrl("/path/to/" + slug);
        catalogOfCatalogsTarget.setSlug(slug);

        return catalogOfCatalogsTarget;
    }
}
