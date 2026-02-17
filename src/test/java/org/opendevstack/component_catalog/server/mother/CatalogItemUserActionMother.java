package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.model.CatalogItemUserAction;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;

import java.util.List;

public class CatalogItemUserActionMother {

    public static CatalogItemUserAction of() {
        CatalogItemUserActionParameter parameter = CatalogItemUserActionParameterMother.of();

        return of(List.of(parameter));
    }

    public static CatalogItemUserAction of(List<CatalogItemUserActionParameter> parameters) {
        return CatalogItemUserAction.builder()
                .id("TEST_PROVISION")
                .displayName("TEST Provision")
                .url("http://example.com/action1")
                .triggerMessage("Trigger Action 1")
                .parameters(parameters)
                .build();
    }
}
