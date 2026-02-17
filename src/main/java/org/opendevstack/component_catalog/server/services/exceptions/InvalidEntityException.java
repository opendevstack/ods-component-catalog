package org.opendevstack.component_catalog.server.services.exceptions;

public class InvalidEntityException extends RuntimeException {
    public InvalidEntityException(String message) {
        this(message, null);
    }

    public InvalidEntityException(String message, Exception e) {
        super(message, e);
    }
}
