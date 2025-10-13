package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityParameter;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityParameterLocation;
import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityParameterValidation;

import java.util.Collections;
import java.util.List;

public class UserActionEntityParameterMother {

    public static UserActionEntityParameter of() {
        return of(
                "workflow",
                "string",
                true,
                "123",
                Collections.emptyList(),
                List.of(UserActionEntityParameterLocationMother.of()),
                Collections.emptyList(),
                "Workflow to execute.",
                "workflow placeholder",
                "workflow hint",
                false,
                false,
                List.of(UserActionEntityParameterValidationMother.of())
        );
    }

    public static UserActionEntityParameter of(String name, String type, boolean required, String defaultValue, List<String> defaultValues,
                                               List<UserActionEntityParameterLocation> locations, List<String> options, String label, String placeholder, String hint,
                                               boolean customizable, boolean visible) {
        return of(name, type, required, defaultValue, defaultValues, locations, options, label, placeholder, hint, customizable, visible, Collections.emptyList());
    }

    public static UserActionEntityParameter of(String name, String type, boolean required, String defaultValue, List<String> defaultValues,
                                               List<UserActionEntityParameterLocation> locations, List<String> options, String label, String placeholder, String hint,
                                               boolean customizable, boolean visible, List<UserActionEntityParameterValidation> validations) {
        return UserActionEntityParameter.builder()
                .name(name)
                .type(type)
                .required(required)
                .defaultValue(defaultValue)
                .defaultValues(defaultValues == null ? new String[0] : defaultValues.toArray(new String[0]))
                .locations(locations == null ? new UserActionEntityParameterLocation[0] : locations.toArray(new UserActionEntityParameterLocation[0]))
                .options(options == null ? new String[0] : options.toArray(new String[0]))
                .label(label)
                .placeholder(placeholder)
                .hint(hint)
                .customizable(customizable)
                .visible(visible)
                .validations(validations.toArray(new UserActionEntityParameterValidation[0]))
                .build();
    }

    public static UserActionEntityParameter[] ofArray() {
        return new UserActionEntityParameter[]{
                of(),
                of(
                        "project_key",
                        "string",
                        true,
                        null,
                        null,
                        List.of(UserActionEntityParameterLocationMother.of()),
                        null,
                        "The project key.",
                        "project_key placeholder",
                        "project_key hint",
                        false,
                        true),
                of(
                        "component_name",
                        "string",
                        true,
                        null,
                        null,
                        null,
                        null,
                        "The name of the component.",
                        "component_name placeholder",
                        "component_name hint",
                        false,
                        true),
                of(
                        "new_parameter",
                        "string",
                        true,
                        null,
                        null,
                        null,
                        null,
                        "The project key.",
                        null,
                        null,
                        false,
                        true),
                of(
                        "new_parameter_multiple",
                        "string",
                        true,
                        null,
                        List.of("option_1", "option_2"),
                        null,
                        List.of("option_1", "option_2", "option_3"),
                        "The project key.",
                        null,
                        null,
                        false,
                        true)

        };
    }
}
