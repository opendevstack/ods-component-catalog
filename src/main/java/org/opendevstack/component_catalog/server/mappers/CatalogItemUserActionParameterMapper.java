package org.opendevstack.component_catalog.server.mappers;

import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameter;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterLocation;
import org.opendevstack.component_catalog.server.model.CatalogItemUserActionParameterValidation;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.*;
import org.opendevstack.component_catalog.server.services.catalog.common.UserActionEntityParameter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Slf4j
@AllArgsConstructor
public class CatalogItemUserActionParameterMapper {

    public CatalogItemUserActionParameter asCatalogItemUserActionParameter(CatalogItemEntityUserActionParameter catalogItemEntityUserActionParameter) {
        log.debug("Mapping CatalogItemUserActionParameter to UserActionEntityParameter: {}", catalogItemEntityUserActionParameter);

        List<String> defaultValues = getDefaultValues(catalogItemEntityUserActionParameter.getDefaultValues());

        List<String> options = getOptions(catalogItemEntityUserActionParameter.getOptions());

        List<CatalogItemUserActionParameterLocation> locations = getCatalogItemUserActionParameterLocations(catalogItemEntityUserActionParameter);

        List<CatalogItemUserActionParameterValidation> validations = Optional.ofNullable(catalogItemEntityUserActionParameter.getValidations())
                .map( catalogItemEntityUserActionParameterValidations ->
                        Arrays.stream(catalogItemEntityUserActionParameterValidations)
                                .map(this::asCatalogItemUserActionParameterValidation)
                                .toList()
                ).orElse(Collections.emptyList());

        return CatalogItemUserActionParameter.builder()
                .name(catalogItemEntityUserActionParameter.getName())
                .type(catalogItemEntityUserActionParameter.getType())
                .required(catalogItemEntityUserActionParameter.isRequired())
                .defaultValue(catalogItemEntityUserActionParameter.getDefaultValue())
                .defaultValues(defaultValues)
                .options(options)
                .locations(locations)
                .label(catalogItemEntityUserActionParameter.getLabel())
                .placeholder(catalogItemEntityUserActionParameter.getPlaceholder())
                .hint(catalogItemEntityUserActionParameter.getHint())
                .visible(catalogItemEntityUserActionParameter.isVisible())
                .validations(validations)
                .build();
    }

    public  CatalogItemUserActionParameterValidation asCatalogItemUserActionParameterValidation(CatalogItemEntityUserActionParameterValidation catalogItemEntityUserActionParameterValidation) {
        return CatalogItemUserActionParameterValidation.builder()
                .regex(catalogItemEntityUserActionParameterValidation.getRegex())
                .errorMessage(catalogItemEntityUserActionParameterValidation.getErrorMessage())
                .build();
    }

    public  CatalogItemUserActionParameter asCatalogItemUserActionParameter(UserActionEntityParameter itemEntityUserActionParameter) {
        log.debug("Mapping UserActionEntityParameter to CatalogItemUserActionParameter: {}", itemEntityUserActionParameter);

        List<String> defaultValues = getDefaultValues(itemEntityUserActionParameter.getDefaultValues());

        List<String> options = getOptions(itemEntityUserActionParameter.getOptions());

        List<CatalogItemUserActionParameterLocation> locations = getCatalogItemUserActionParameterLocations(itemEntityUserActionParameter);

        List<CatalogItemUserActionParameterValidation> validations = Optional.ofNullable(itemEntityUserActionParameter.getValidations())
                .map(itemValidations -> Stream.of(itemValidations)
                        .map(this::asCatalogItemUserActionParameterValidation)
                        .toList())
                .orElse(Collections.emptyList());

        return CatalogItemUserActionParameter.builder()
                .name(itemEntityUserActionParameter.getName())
                .type(itemEntityUserActionParameter.getType())
                .required(itemEntityUserActionParameter.isRequired())
                .defaultValue(toJsonNullable(itemEntityUserActionParameter.getDefaultValue()))
                .defaultValues(defaultValues)
                .options(options)
                .locations(locations)
                .label(itemEntityUserActionParameter.getLabel())
                .placeholder(toJsonNullable(itemEntityUserActionParameter.getPlaceholder()))
                .hint(toJsonNullable(itemEntityUserActionParameter.getHint()))
                .visible(itemEntityUserActionParameter.isVisible())
                .validations(validations)
                .build();
    }

    public  CatalogItemUserActionParameterValidation asCatalogItemUserActionParameterValidation(
            UserActionEntityParameterValidation userActionEntityParameterValidation) {
        return CatalogItemUserActionParameterValidation.builder()
                .regex(userActionEntityParameterValidation.getRegex())
                .errorMessage(userActionEntityParameterValidation.getErrorMessage())
                .build();
    }

    public  CatalogItemUserActionParameterLocation asCatalogItemUserActionParameterLocation(
            UserActionEntityParameterLocation userActionEntityParameterLocation) {
        return CatalogItemUserActionParameterLocation.builder()
                .location(userActionEntityParameterLocation.getLocation())
                .value(userActionEntityParameterLocation.getValue())
                .build();
    }

    public  CatalogItemUserActionParameterLocation asCatalogItemUserActionParameterLocation(
            CatalogItemEntityUserActionParameterLocation userActionEntityParameterLocation) {
        return CatalogItemUserActionParameterLocation.builder()
                .location(userActionEntityParameterLocation.getLocation())
                .value(userActionEntityParameterLocation.getValue())
                .build();
    }

    private  List<CatalogItemUserActionParameterLocation> getCatalogItemUserActionParameterLocations(UserActionEntityParameter itemEntityUserActionParameter) {
        return Optional.ofNullable(itemEntityUserActionParameter.getLocations())
                .map(loc ->
                        Stream.of(loc)
                                .map(this::asCatalogItemUserActionParameterLocation)
                                .toList())
                .orElse(Collections.emptyList());
    }

    private  List<CatalogItemUserActionParameterLocation> getCatalogItemUserActionParameterLocations(CatalogItemEntityUserActionParameter catalogItemEntityUserActionParameter) {
        return Optional.ofNullable(catalogItemEntityUserActionParameter.getLocations())
                .map(loc ->
                        Stream.of(loc)
                                .map(this::asCatalogItemUserActionParameterLocation)
                                .toList())
                .orElse(Collections.emptyList());
    }

    private  List<String> getOptions(String[] itemEntityUserActionParameter) {
        return Optional.ofNullable(itemEntityUserActionParameter)
                .map(opt -> Stream.of(opt).toList())
                .orElse(Collections.emptyList());
    }

    private  List<String> getDefaultValues(String[] itemEntityUserActionParameter) {
        return Optional.ofNullable(itemEntityUserActionParameter)
                .map(values -> Stream.of(values).toList())
                .orElse(Collections.emptyList());
    }

    private JsonNullable<String> toJsonNullable(String value) {
        return value == null ? JsonNullable.undefined() : JsonNullable.of(value);
    }
}
