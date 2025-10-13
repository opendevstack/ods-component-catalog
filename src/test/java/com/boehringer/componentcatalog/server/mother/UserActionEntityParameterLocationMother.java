package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityParameterLocation;

public class UserActionEntityParameterLocationMother {

    public static UserActionEntityParameterLocation of() {
        return UserActionEntityParameterLocation.builder()
                .location("EU")
                .value("1234")
                .build();
    }
}
