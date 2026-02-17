package org.opendevstack.component_catalog.server.services.catalog.entity;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityRestrictions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemEntitySpec {
    private CatalogItemEntityUserAction[] userActions;
    private CatalogItemEntityRestrictions restrictions;
}
