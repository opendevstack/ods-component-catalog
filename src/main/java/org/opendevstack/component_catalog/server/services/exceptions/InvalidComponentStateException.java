package org.opendevstack.component_catalog.server.services.exceptions;

public class InvalidComponentStateException extends IllegalArgumentException {
    public InvalidComponentStateException(String message) {
        super(message);
    }
}
