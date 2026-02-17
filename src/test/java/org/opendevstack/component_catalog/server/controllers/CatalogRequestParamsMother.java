package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntityMother;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContextMother;

import java.util.Collections;

public class CatalogRequestParamsMother {

    public static CatalogRequestParams of() {
        return of(CatalogItemEntityContextMother.of());
    }

    public static CatalogRequestParams of(CatalogItemEntityRestrictions restrictions) {
        return of(CatalogItemEntityContextMother.of(restrictions));
    }

    public static CatalogRequestParams of(CatalogItemEntityContext catalogItemEntityContext) {
        return CatalogRequestParams.builder()
                .catalogItemEntityContext(catalogItemEntityContext)
                .permissions(Collections.emptySet())
                .userActionsEntity(UserActionsEntityMother.of())
                .build();
    }
}
