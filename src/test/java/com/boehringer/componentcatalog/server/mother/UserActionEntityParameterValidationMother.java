package com.boehringer.componentcatalog.server.mother;

import com.boehringer.componentcatalog.server.services.catalog.UserActionEntityParameterValidation;

public class UserActionEntityParameterValidationMother {

    public static UserActionEntityParameterValidation of() {
        return UserActionEntityParameterValidation.builder()
                .regex("/^[a-z\\s]{0,255}$/i")
                .errorMessage("Only lowercase letters and spaces are allowed, up to 255 characters.")
                .build();
    }
}
