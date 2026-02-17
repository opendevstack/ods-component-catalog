package org.opendevstack.component_catalog.server.services.catalog;

import java.util.Collections;
import java.util.List;

public class CatalogItemEntityRestrictionsMother {

    public static CatalogItemEntityRestrictions of() {
        return of(Collections.emptyList());
    }

    public static CatalogItemEntityRestrictions of(List<String> projects) {
        return CatalogItemEntityRestrictions.builder()
                .projects(projects)
                .build();
    }
}
