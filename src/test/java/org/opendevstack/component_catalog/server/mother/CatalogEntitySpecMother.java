package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityLink;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntitySpec;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityTarget;

import java.util.Arrays;

public class CatalogEntitySpecMother {

    public static CatalogEntitySpec of() {
        var catalogEntitySpec = new CatalogEntitySpec();

        catalogEntitySpec.setTargets(Arrays.asList(
                CatalogEntityTargetMother.of("target1"),
                CatalogEntityTargetMother.of("target2")
        ).toArray(new CatalogEntityTarget[]{}));

        catalogEntitySpec.setLinks(Arrays.asList(
                CatalogEntityLinkMother.of("link1"),
                CatalogEntityLinkMother.of("link2"),
                CatalogEntityLinkMother.of("link3")
        ).toArray(new CatalogEntityLink[]{}));

        catalogEntitySpec.setTags(Arrays.asList(
                "tasks",
                "technologies"
        ));

        return catalogEntitySpec;
    }
}
