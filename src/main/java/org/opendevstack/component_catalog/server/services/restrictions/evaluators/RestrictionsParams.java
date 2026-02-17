package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RestrictionsParams {
    List<String> clusters;
    List<CatalogItemUserActionParameter> parameters;
    List<String> userGroups;
    String projectKey;
    String catalogItemId;
}
