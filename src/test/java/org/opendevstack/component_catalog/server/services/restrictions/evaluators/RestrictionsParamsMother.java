package org.opendevstack.component_catalog.server.services.restrictions.evaluators;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;

import java.util.List;

public class RestrictionsParamsMother {

    public static RestrictionsParams of() {
        return RestrictionsParams.builder().build();
    }

    public static RestrictionsParams of(List<String> userGroups) {
        return RestrictionsParams.builder()
                .userGroups(userGroups)
                .build();
    }

    public static RestrictionsParams of(List<CatalogItemUserActionParameter> parameters, List<String> clusters) {
        return RestrictionsParams.builder()
                .parameters(parameters)
                .clusters(clusters)
                .build();
    }

    public static RestrictionsParams of(String projectKey) {
        return RestrictionsParams.builder()
                .projectKey(projectKey)
                .build();
    }
}
