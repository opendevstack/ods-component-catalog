package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;

public class UserActionRestrictionsMother {

    public static UserActionEntityRestrictions of() {
        return of(new String[]{"ProjectA", "ProjectB"}, new String[]{"LocationA", "LocationB"});
    }

    public static UserActionEntityRestrictions of(String[] projects, String[] locations) {
        return UserActionEntityRestrictions.builder()
                .oneTimeOnly(false)
                .projects(projects)
                .locations(locations)
                .build();
    }
}
