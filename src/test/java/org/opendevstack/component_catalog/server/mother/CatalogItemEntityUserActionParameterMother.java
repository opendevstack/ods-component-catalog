package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.services.catalog.CatalogItemEntityUserActionParameter;

public class CatalogItemEntityUserActionParameterMother {

    public static CatalogItemEntityUserActionParameter of() {
        return ofArray()[0];
    }

    public static CatalogItemEntityUserActionParameter of(String name,
                                                               String type,
                                                               boolean required,
                                                               String defaultValue,
                                                               String label,
                                                               String placeholder,
                                                               String hint,
                                                               boolean customizable,
                                                               boolean visible) {
        return CatalogItemEntityUserActionParameter.builder()
                .name(name)
                .type(type)
                .required(required)
                .defaultValue(defaultValue)
                .label(label)
                .placeholder(placeholder)
                .hint(hint)
                .customizable(customizable)
                .visible(visible)
                .build();
    }

    public static CatalogItemEntityUserActionParameter[] ofArray() {
        return new CatalogItemEntityUserActionParameter[]{
                of(
                        "workflow",
                        "string",
                        true,
                        "9987",
                        "Workflow to execute.",
                        "Simple placeholder",
                        "Wait for the hint",
                        false,
                        false),
                of(
                        "new_parameter",
                        "string",
                        true,
                        null,
                        "The project key.",
                        "Another placeholder",
                        null,
                        true,
                        true)
        };
    }
}
