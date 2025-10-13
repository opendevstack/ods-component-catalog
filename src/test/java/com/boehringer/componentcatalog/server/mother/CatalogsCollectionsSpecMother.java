package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import com.boehringer.componentcatalog.server.services.catalog.CatalogsCollectionsEntityTarget;

import java.util.Arrays;

public class CatalogsCollectionsSpecMother {

    public static CatalogsCollectionsEntitySpec of() {
        var catalogOfCatalogsSpec = new CatalogsCollectionsEntitySpec();
        var targets = Arrays.asList(
                CatalogsCollectionsEntityTargetMother.of("catalog1"),
                CatalogsCollectionsEntityTargetMother.of("catalog2")
        );

        catalogOfCatalogsSpec.setTargets(targets.toArray(new CatalogsCollectionsEntityTarget[]{}));

        return catalogOfCatalogsSpec;
    }
}
