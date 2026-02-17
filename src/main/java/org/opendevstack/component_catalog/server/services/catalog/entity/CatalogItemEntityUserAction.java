package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityUserActionParameter;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageDefinition;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityMessageTitle;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityRestrictions;
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
    private UserActionEntityRestrictions restrictions;
}
