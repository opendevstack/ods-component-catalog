package org.opendevstack.component_catalog.server.services.exceptions;

public class UnableToDeserializeEntityException extends RuntimeException {
    public UnableToDeserializeEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
