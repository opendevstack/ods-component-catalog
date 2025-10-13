package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.model.CatalogItemUserActionParameter;
import com.boehringer.componentcatalog.server.model.CatalogItemUserActionParameterValidation;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Collections;
import java.util.List;

public class CatalogItemUserActionParameterMother {

    public static CatalogItemUserActionParameter of() {
        return of("CatalogItemUserActionParameter Name", "CatalogItemUserActionParameter Type", List.of(CatalogItemUserActionParameterValidationMother.of()));
    }

    public static CatalogItemUserActionParameter of(String name, String type) {
        return of(name, type, Collections.emptyList());
    }

    public static CatalogItemUserActionParameter of(String name, String type, List<CatalogItemUserActionParameterValidation> validations) {
        return CatalogItemUserActionParameter.builder()
                .name(name)
                .type(type)
                .required(false)
                .defaultValue(JsonNullable.of("CatalogItemUserActionParameter Default Value"))
                .label("CatalogItemUserActionParameter Label")
                .placeholder("CatalogItemUserActionParameter Placeholder")
                .hint("CatalogItemUserActionParameter Hint")
                .visible(true)
                .validations(validations)
                .build();
    }
}
