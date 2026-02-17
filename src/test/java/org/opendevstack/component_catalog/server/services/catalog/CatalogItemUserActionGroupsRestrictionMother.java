package org.opendevstack.component_catalog.server.services.catalog;

import java.util.List;

public class CatalogItemUserActionGroupsRestrictionMother {

    public static CatalogItemUserActionGroupsRestriction of() {
        return CatalogItemUserActionGroupsRestriction.builder()
                .prefix(List.of("prefix-1", "prefix-2"))
                .suffix(List.of("suffix-1", "suffix-2"))
                .build();
    }
}
