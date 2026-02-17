package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;

public class CatalogItemEntitySpecMother {

    public static CatalogItemEntitySpec of() {
        return CatalogItemEntitySpec.builder()
                .build();
    }

    public static CatalogItemEntitySpec of(CatalogItemEntityUserAction[] userActions, CatalogItemEntityRestrictions restrictions) {
        return CatalogItemEntitySpec.builder()
                .userActions(userActions)
                .restrictions(restrictions)
                .build();
    }
}
