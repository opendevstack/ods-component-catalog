package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.UserActionEntityMessageType;

public class UserActionEntityMessageTitleMother {

    public static UserActionEntityMessageTitle of() {
        return of(UserActionEntityMessageType.SUCCESS);
    }

    public static UserActionEntityMessageTitle of(UserActionEntityMessageType id) {
        return UserActionEntityMessageTitle.builder()
                .id(id)
                .title("User Action Entity Message Title for " + id)
                .build();
    }

    public static UserActionEntityMessageTitle[] ofArray() {
        return new UserActionEntityMessageTitle[]{
                of(),
                of(UserActionEntityMessageType.ERROR)
        };
    }
}
