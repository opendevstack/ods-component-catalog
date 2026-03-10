package org.opendevstack.component_catalog.org.opendevstack.component_catalog.server.model;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterLocation;

public class CatalogItemUserActionParameterLocationMother {

    public static CatalogItemUserActionParameterLocation of() {
        return of("eu-mother-location");
    }

    public static CatalogItemUserActionParameterLocation of(String location) {
        return CatalogItemUserActionParameterLocation.builder()
                .location(location)
                .value("1234")
                .build();
    }
}
