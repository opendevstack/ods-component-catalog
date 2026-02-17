package org.opendevstack.component_catalog.server.services.catalog.common;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemUserActionGroupsRestriction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActionEntityRestrictions {
    private boolean oneTimeOnly;
    private String[] projects;
    private String[] locations;

    @JsonIgnore
    private CatalogItemUserActionGroupsRestriction groups;
}
