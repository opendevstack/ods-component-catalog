package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageDefinition;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageTitle;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActionEntityMessageDefinitionMother {

    public static UserActionEntityMessageDefinition of() {
        return of("Message Definition ID", UserActionEntityMessageType.SUCCESS);
    }

    public static UserActionEntityMessageDefinition of(String id, UserActionEntityMessageType type) {
        return UserActionEntityMessageDefinition.builder()
                .id(id)
                .type(type)
                .message("Simple message for testing purposes for " + id + " with type " + type)
                .build();
    }

    public static UserActionEntityMessageDefinition of(String id, UserActionEntityMessageType type, String message) {
        return UserActionEntityMessageDefinition.builder()
                .id(id)
                .type(type)
                .message(message)
                .build();
    }

    public static UserActionEntityMessageDefinition[] ofArray() {
        return new UserActionEntityMessageDefinition[]{
                of()
        };
    }

    public static UserActionEntityMessageDefinition[] ofArray(UserActionEntityMessageDefinition userActionEntityMessageDefinition) {
        List<UserActionEntityMessageDefinition> list = new ArrayList<>(Arrays.asList(ofArray()));

        list.add(userActionEntityMessageDefinition);

        return list.toArray(new UserActionEntityMessageDefinition[0]);

    }
}
