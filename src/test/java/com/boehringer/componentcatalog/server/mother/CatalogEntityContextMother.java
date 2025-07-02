package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.bitbucket.BitbucketPathAt;
import com.boehringer.componentcatalog.server.services.catalog.CatalogEntityContext;

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
