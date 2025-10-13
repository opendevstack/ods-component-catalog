package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntitySpec;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityUserAction;

public class CatalogItemEntitySpecMother {

    public static CatalogItemEntitySpec of() {
        return CatalogItemEntitySpec.builder()
                .build();
    }

    public static CatalogItemEntitySpec of(CatalogItemEntityUserAction[] userActions) {
        return CatalogItemEntitySpec.builder()
                .userActions(userActions)
                .build();
    }
}
