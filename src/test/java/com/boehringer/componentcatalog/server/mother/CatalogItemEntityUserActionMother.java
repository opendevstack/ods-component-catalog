package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntity;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityUserAction;
import com.boehringer.componentcatalog.server.services.catalog.CatalogItemEntityUserActionParameter;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityMessageDefinition;

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
                        .build(),
        };
    }
}
