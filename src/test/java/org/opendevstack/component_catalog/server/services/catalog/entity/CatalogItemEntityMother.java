package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.mother.CatalogItemEntityMetadataMother;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityMetadata;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictionsMother;

public class CatalogItemEntityMother {

    public static CatalogItemEntity of() {
        return of(CatalogItemEntityMetadataMother.of());
    }

    public static CatalogItemEntity of(CatalogItemEntityUserAction[] userActions) {
        return of(CatalogItemEntityMetadataMother.of(), userActions, CatalogItemEntityRestrictionsMother.of());
    }

    public static CatalogItemEntity of(CatalogItemEntityRestrictions restrictions) {
        return of(CatalogItemEntityMetadataMother.of(), CatalogItemEntityUserActionMother.ofArray(), restrictions);
    }

    public static CatalogItemEntity of(CatalogItemEntityUserAction[] userActions, CatalogItemEntityRestrictions restrictions) {
        return of(CatalogItemEntityMetadataMother.of(), userActions, restrictions);
    }

    public static CatalogItemEntity of(CatalogItemEntityMetadata metadata) {
        return of("CatalogItem", metadata);
    }

    public static CatalogItemEntity of(CatalogItemEntityMetadata metadata, CatalogItemEntityUserAction[] userActions, CatalogItemEntityRestrictions restrictions) {
        return of("CatalogItem", metadata, userActions, restrictions);
    }

    public static CatalogItemEntity of(String kind, CatalogItemEntityMetadata metadata) {
        return of(kind,
                metadata,
                CatalogItemEntitySpecMother.of(
                        CatalogItemEntityUserActionMother.ofArray(),
                        CatalogItemEntityRestrictionsMother.of()
                ));
    }

    public static CatalogItemEntity of(String kind, CatalogItemEntityMetadata metadata, CatalogItemEntityUserAction[] userActions, CatalogItemEntityRestrictions restrictions) {
        return of(kind,
                metadata,
                CatalogItemEntitySpecMother.of(
                        userActions, restrictions
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
