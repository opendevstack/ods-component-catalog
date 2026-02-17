package org.opendevstack.component_catalog.server.services.catalog.business;

import java.util.List;

public class UserActionsEntitySpecMother {

    public static UserActionsEntitySpec of() {
        return of(UserActionEntityMother.ofArray());
    }

    public static UserActionsEntitySpec of(List<UserActionEntity> actions) {
        return of(actions.toArray(new UserActionEntity[0]));
    }

    public static UserActionsEntitySpec of(UserActionEntity[] actions) {
        return UserActionsEntitySpec.builder()
                .actions(actions)
                .build();
    }
}
