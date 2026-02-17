package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterValidation;

public class CatalogItemUserActionParameterValidationMother {

    public static CatalogItemUserActionParameterValidation of() {
            return CatalogItemUserActionParameterValidation.builder()
                    .regex("/^[a-z\\s]{0,255}$/i")
                    .errorMessage("Only lowercase letters and spaces are allowed, up to 255 characters.")
                    .build();
        }
}
