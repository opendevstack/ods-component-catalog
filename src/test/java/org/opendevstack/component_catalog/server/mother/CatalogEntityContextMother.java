package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.bitbucket.BitbucketPathAt;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityContext;

import java.util.Optional;

public class CatalogEntityContextMother {

    public static CatalogEntityContext of() {
        var catalogEntity = CatalogEntityMother.of();
        var communityPageAt = Optional.of(BitbucketPathAtMother.of());

        return CatalogEntityContext.builder()
                .catalogEntity(catalogEntity)
                .communityPagePathAt(communityPageAt)
                .build();
    }

    public static CatalogEntityContext of(BitbucketPathAt communityPagePathAt) {
        var catalogEntity = CatalogEntityMother.of();
        var communityPageAt = Optional.of(communityPagePathAt);

        return CatalogEntityContext.builder()
                .catalogEntity(catalogEntity)
                .communityPagePathAt(communityPageAt)
                .build();
    }
}
