package com.boehringer.componentcatalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemEntityUserAction {
    private String id;
    private String displayName;
    private String url;
    private String triggerMessage;
    private UserActionEntityMessageTitle[] messagesTitles;
    private UserActionEntityMessageDefinition[] messagesDefinitions;
    private CatalogItemEntityUserActionParameter[] parameters;
}
