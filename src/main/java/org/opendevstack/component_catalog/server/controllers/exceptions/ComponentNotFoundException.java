package org.opendevstack.component_catalog.server.controllers.exceptions;

public class ComponentNotFoundException extends RuntimeException {
    public ComponentNotFoundException(String message) {
        super(message);
    }
}
