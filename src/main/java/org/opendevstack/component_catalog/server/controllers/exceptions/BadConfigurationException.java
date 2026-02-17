package org.opendevstack.component_catalog.server.controllers.exceptions;

public class BadConfigurationException extends RuntimeException {
    public BadConfigurationException(String message) {
        super(message);
    }
}
