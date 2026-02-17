package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.UserActionEntityParameterLocation;

public class UserActionEntityParameterLocationMother {

    public static UserActionEntityParameterLocation of() {
        return UserActionEntityParameterLocation.builder()
                .location("EU")
                .value("1234")
                .build();
    }
}
