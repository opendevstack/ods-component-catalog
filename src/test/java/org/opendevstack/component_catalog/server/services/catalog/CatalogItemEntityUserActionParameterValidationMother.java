package org.opendevstack.component_catalog.server.services.catalog;

public class CatalogItemEntityUserActionParameterValidationMother {

    public static CatalogItemEntityUserActionParameterValidation of() {
        return of("^.*$", "Invalid value");
    }

    public static CatalogItemEntityUserActionParameterValidation of(String regex, String errorMessage) {
        return CatalogItemEntityUserActionParameterValidation.builder()
                .regex(regex)
                .errorMessage(errorMessage)
                .build();
    }
}
