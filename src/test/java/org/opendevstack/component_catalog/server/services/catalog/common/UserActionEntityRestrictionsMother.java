package org.opendevstack.component_catalog.server.services.catalog.common;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;

public class UserActionEntityRestrictionsMother {

    public static UserActionEntityRestrictions of(CatalogItemUserActionGroupsRestriction groupsRestriction) {
        return UserActionEntityRestrictions.builder()
                .groups(groupsRestriction)
                .build();
    }

    public static UserActionEntityRestrictions of() {
        return of(new String[]{"eu"}, new String[]{"DEVSTACK"}, false);
    }

    public static UserActionEntityRestrictions of(String[] locations) {
        var restrictions = new UserActionEntityRestrictions();
        restrictions.setLocations(locations);
        return restrictions;
    }

    public static UserActionEntityRestrictions of(String[] locations, String[] projects) {
        var restrictions = UserActionEntityRestrictionsMother.of(locations);
        restrictions.setProjects(projects);
        return restrictions;
    }

    public static UserActionEntityRestrictions of(String[] locations, String[] projects, boolean oneTimeOnly) {
        var restrictions = UserActionEntityRestrictionsMother.of(locations, projects);
        restrictions.setOneTimeOnly(oneTimeOnly);
        return restrictions;
    }
}
