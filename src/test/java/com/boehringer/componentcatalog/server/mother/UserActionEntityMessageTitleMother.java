package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageTitle;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageType;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityParameter;

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
