package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.*;

import java.util.List;

public class CatalogItemEntityMother {

    public static CatalogItemEntity of() {
        return of(CatalogItemEntityMetadataMother.of());
    }

    public static CatalogItemEntity of(CatalogItemEntityUserAction[] userActions) {
        return of(CatalogItemEntityMetadataMother.of(), userActions);
    }

    public static CatalogItemEntity of(CatalogItemEntityMetadata metadata) {
        return of("CatalogItem", metadata);
    }

    public static CatalogItemEntity of(CatalogItemEntityMetadata metadata, CatalogItemEntityUserAction[] userActions) {
        return of("CatalogItem", metadata, userActions);
    }

    public static CatalogItemEntity of(String kind, CatalogItemEntityMetadata metadata) {
        return of(kind,
                metadata,
                CatalogItemEntitySpecMother.of(
                        CatalogItemEntityUserActionMother.ofArray()
                ));
    }

    public static CatalogItemEntity of(String kind, CatalogItemEntityMetadata metadata, CatalogItemEntityUserAction[] userActions) {
        return of(kind,
                metadata,
                CatalogItemEntitySpecMother.of(
                        userActions
                ));
    }

    public static CatalogItemEntity of(String kind,
                                       CatalogItemEntityMetadata metadata,
                                       CatalogItemEntitySpec spec) {
        return CatalogItemEntity.builder()
                .kind(kind)
                .metadata(metadata)
                .spec(spec)
                .build();
    }

}
