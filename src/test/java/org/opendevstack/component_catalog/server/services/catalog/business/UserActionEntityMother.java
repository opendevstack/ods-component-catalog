package org.opendevstack.component_catalog.server.services.catalog.business;

import org.opendevstack.component_catalog.server.mother.UserActionEntityMessageDefinitionMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityMessageTitleMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityParameterMother;
import org.opendevstack.component_catalog.server.services.catalog.common.*;
import org.opendevstack.component_catalog.server.services.catalog.common.*;

public class UserActionEntityMother {

    public static UserActionEntity of() {
        return of("ACTION_ID_1",
                "Action 1",
                "http://action1-url",
                "Trigger message 1",
                true,
                UserActionEntityParameterMother.ofArray(),
                UserActionEntityMessageTitleMother.ofArray(),
                UserActionEntityMessageDefinitionMother.ofArray(),
                UserActionEntityRestrictionsMother.of(new String[]{"eu"})
        );
    }

    public static UserActionEntity of(String id) {
        return of(
                    id,
                    "Action " + id,
                    "http://" + id + "-url",
                    "Trigger message for " + id,
                    true,
                    UserActionEntityParameterMother.ofArray(),
                    UserActionEntityMessageTitleMother.ofArray(),
                    UserActionEntityMessageDefinitionMother.ofArray(),
                    UserActionEntityRestrictionsMother.of(new String[]{"eu"})
                );
    }

    public static UserActionEntity of(String id, UserActionEntityMessageDefinition[] messagesDefinitions) {
        return of(
                id,
                "Action " + id,
                "http://" + id + "-url",
                "Trigger message for " + id,
                true,
                UserActionEntityParameterMother.ofArray(),
                UserActionEntityMessageTitleMother.ofArray(),
                messagesDefinitions,
                UserActionEntityRestrictionsMother.of(new String[]{"eu"})
        );
    }

    public static UserActionEntity of(String id, String displayName, String url, String triggerMessage,
                                      boolean mandatory, UserActionEntityParameter[] userActionEntityParameter) {
        return of(
                id,
                displayName,
                url,
                triggerMessage,
                mandatory,
                userActionEntityParameter,
                UserActionEntityMessageTitleMother.ofArray(),
                UserActionEntityMessageDefinitionMother.ofArray(),
                UserActionEntityRestrictionsMother.of(new String[]{"eu"})
        );
    }

    public static UserActionEntity of(String id, String displayName, String url, String triggerMessage,
                                      boolean mandatory, UserActionEntityParameter[] userActionEntityParameter,
                                      UserActionEntityMessageTitle[] messagesTitles, UserActionEntityMessageDefinition[] messagesDefinitions,
                                      UserActionEntityRestrictions restrictions) {
        return UserActionEntity.builder()
                .id(id)
                .displayName(displayName)
                .url(url)
                .triggerMessage(triggerMessage)
                .mandatory(mandatory)
                .parameters(userActionEntityParameter) // Assuming parameters is an array
                .messagesTitles(messagesTitles)
                .messagesDefinitions(messagesDefinitions)
                .restrictions(restrictions)
                .build();
    }

    public static UserActionEntity[] ofArray() {
        return new UserActionEntity[]{
                of(),
                of("CODE",
                        "View Code",
                        null,
                        null,
                        true,
                        new UserActionEntityParameter[]{},
                        new UserActionEntityMessageTitle[]{},
                        new UserActionEntityMessageDefinition[]{},
                        new UserActionEntityRestrictions()
                        )
        };
    }

}
