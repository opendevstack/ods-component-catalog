package org.opendevstack.component_catalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemUserActionGroupsRestriction {
    private List<String> prefix;
    private List<String> suffix;
}
