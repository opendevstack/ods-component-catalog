package org.opendevstack.component_catalog.server.services.catalog.business;

import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
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
    private UserActionEntityRestrictions restrictions;
}
