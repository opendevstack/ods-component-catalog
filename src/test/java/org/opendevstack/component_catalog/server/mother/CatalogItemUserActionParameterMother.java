package org.opendevstack.component_catalog.server.mother;

import org.opendevstack.component_catalog.org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterLocationMother;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterLocation;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterValidation;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Collections;
import java.util.List;

public class CatalogItemUserActionParameterMother {

    public static CatalogItemUserActionParameter of() {
        return of("CatalogItemUserActionParameter Name");
    }

    public static CatalogItemUserActionParameter of(List<String> locations) {
        return of("CatalogItemUserActionParameter Name", locations);
    }

    public static CatalogItemUserActionParameter of(String name) {
        return of(name, "CatalogItemUserActionParameter Type", List.of(CatalogItemUserActionParameterValidationMother.of()));
    }

    public static CatalogItemUserActionParameter of(String name, List<String> locations) {
        return of(name, "CatalogItemUserActionParameter Type", List.of(CatalogItemUserActionParameterValidationMother.of()), locations);
    }

    public static CatalogItemUserActionParameter of(String name, String type) {
        return of(name, type, Collections.emptyList());
    }


    public static CatalogItemUserActionParameter of(String name, String type, List<CatalogItemUserActionParameterValidation> validations, List<String> locations) {
        var locationObjects = locations.stream()
                .map(CatalogItemUserActionParameterLocationMother::of).toList();
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
                .locations(JsonNullable.of(locationObjects))
                .build();
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
