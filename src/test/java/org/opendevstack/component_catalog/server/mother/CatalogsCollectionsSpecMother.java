package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogsCollectionsEntityTarget;

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
