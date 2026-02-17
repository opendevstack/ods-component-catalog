package org.opendevstack.component_catalog.server.controllers;

import org.opendevstack.component_catalog.server.model.SortOrder;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntity;
import org.opendevstack.component_catalog.server.services.catalog.CatalogEntityPermissionEnum;
import org.opendevstack.component_catalog.server.services.catalog.business.UserActionsEntity;
import org.opendevstack.component_catalog.server.services.catalog.entity.CatalogItemEntityContext;
import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@Data
public class CatalogRequestParams {
    CatalogEntity catalogEntity;
    CatalogItemEntityContext catalogItemEntityContext;
    List<CatalogItemEntityContext> catalogItemEntityContextList;
    UserActionsEntity userActionsEntity;
    Set<CatalogEntityPermissionEnum> permissions;
    SortOrder sortOrder;
    String catalogId;
    String catalogItemId;
    @Builder.Default
    String projectKey = Strings.EMPTY;
    String idToken;
    String accessToken;


}
