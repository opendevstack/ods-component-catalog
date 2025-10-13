package com.boehringer.componentcatalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This bean directly maps a CatalogOfCatalogs.yaml file in the catalog of catalogs repo
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActionsEntity {
    private String kind;
    private UserActionsEntitySpec spec;
}
