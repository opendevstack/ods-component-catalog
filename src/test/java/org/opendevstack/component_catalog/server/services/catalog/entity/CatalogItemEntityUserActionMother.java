package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.mother.CatalogItemEntityUserActionParameterMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityMessageDefinitionMother;
import org.opendevstack.component_catalog.server.mother.UserActionEntityMessageTitleMother;
import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityUserActionParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictionsMother;

public class CatalogItemEntityUserActionMother {

    public static CatalogItemEntityUserAction of() {
        return of("TEST_PROVISION");
    }

    public static CatalogItemEntityUserAction of(String id) {
        return of(id, UserActionEntityMessageDefinitionMother.ofArray());
    }

    public static CatalogItemEntityUserAction of(String id, UserActionEntityMessageDefinition[] messageDefinitions) {
        return of(
                    id,
                    "TEST Provision",
                    "http://example.com/action1",
                    "Trigger Action 1",
                    CatalogItemEntityUserActionParameterMother.ofArray(),
                    messageDefinitions
        );
    }

    public static CatalogItemEntityUserAction of(String id,
                                                 String displayName,
                                                 String url,
                                                 String triggerMessage,
                                                 CatalogItemEntityUserActionParameter[] parameters,
                                                 UserActionEntityMessageDefinition[] messageDefinitions) {
        return CatalogItemEntityUserAction.builder()
                .id(id)
                .displayName(displayName)
                .url(url)
                .triggerMessage(triggerMessage)
                .parameters(parameters)
                .messagesTitles(UserActionEntityMessageTitleMother.ofArray())
                .messagesDefinitions(messageDefinitions)
                .restrictions(UserActionEntityRestrictionsMother.of())
                .build();
    }

    public static CatalogItemEntityUserAction[] ofArray() {
        return new CatalogItemEntityUserAction[]{
                of(),
                CatalogItemEntityUserAction.builder()
                        .id("CODE")
                        .displayName("TEST View Code")
                        .url("http://src")
                        .triggerMessage(null)
                        .parameters(new CatalogItemEntityUserActionParameter[]{})
                        .messagesDefinitions(UserActionEntityMessageDefinitionMother.ofArray())
                        .restrictions(UserActionEntityRestrictionsMother.of())
                        .build(),
        };
    }
}
