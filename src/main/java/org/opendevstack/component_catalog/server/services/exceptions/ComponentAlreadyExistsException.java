package org.opendevstack.component_catalog.server.services.exceptions;

public class ComponentAlreadyExistsException extends IllegalArgumentException {
    public ComponentAlreadyExistsException(String message) {
        super(message);
    }
}
