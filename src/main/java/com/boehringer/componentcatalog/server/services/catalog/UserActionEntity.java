package com.boehringer.componentcatalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActionEntity {
    private String id;
    private String displayName;
    private String url;
    private String triggerMessage;
    private boolean mandatory;
    private UserActionEntityParameter[] parameters;
    private UserActionEntityMessageTitle[] messagesTitles;
    private UserActionEntityMessageDefinition[] messagesDefinitions;
}
