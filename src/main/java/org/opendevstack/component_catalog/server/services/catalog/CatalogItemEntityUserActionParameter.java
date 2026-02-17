package org.opendevstack.component_catalog.server.services.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogItemEntityUserActionParameter {
    private String name;
    private String type;
    private boolean required;
    private String defaultValue;
    private String[] defaultValues;
    private String[] options;
    private CatalogItemEntityUserActionParameterLocation[] locations;
    private String label;
    private String placeholder;
    private String hint;
    private boolean customizable;
    private boolean visible;
    private CatalogItemEntityUserActionParameterValidation[] validations;
}
