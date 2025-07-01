package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsSpec;
import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsTarget;

import java.util.Arrays;

public class CatalogsCollectionsSpecMother {

    public static CatalogsCollectionsSpec of() {
        var catalogOfCatalogsSpec = new CatalogsCollectionsSpec();
        var targets = Arrays.asList(
                CatalogsCollectionsTargetMother.of("catalog1"),
                CatalogsCollectionsTargetMother.of("catalog2")
        );

        catalogOfCatalogsSpec.setTargets(targets.toArray(new CatalogsCollectionsTarget[]{}));

        return catalogOfCatalogsSpec;
    }
}
