package org.opendevstack.component_catalog.server.services.catalog;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserActionEntityMessageType {
    SUCCESS("success"),
    ERROR("error");

    private String value;

    UserActionEntityMessageType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static UserActionEntityMessageType fromValue(String value) {
        for (UserActionEntityMessageType b : UserActionEntityMessageType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}
