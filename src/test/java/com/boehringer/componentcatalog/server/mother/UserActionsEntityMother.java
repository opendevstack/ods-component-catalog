package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.UserActionEntity;
import com.boehringer.componentcatalog.server.services.catalog.UserActionsEntity;
import com.boehringer.componentcatalog.server.services.catalog.UserActionsEntitySpec;

import java.util.List;

public class UserActionsEntityMother {

    public static UserActionsEntity of() {
        return UserActionsEntity.builder()
                .kind("UserActions")
                .spec(UserActionsEntitySpecMother.of())
                .build();
    }

    public static UserActionsEntity of(List<UserActionEntity> userActions) {
        return UserActionsEntity.builder()
                .kind("UserActions")
                .spec(UserActionsEntitySpecMother.of(userActions))
                .build();
    }

    public static UserActionsEntity of(String kind, UserActionsEntitySpec spec) {
        return UserActionsEntity.builder()
                .kind(kind)
                .spec(spec)
                .build();
    }
}
