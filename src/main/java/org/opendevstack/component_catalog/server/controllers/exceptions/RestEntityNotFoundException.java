package org.opendevstack.component_catalog.server.controllers.exceptions;

public class RestEntityNotFoundException extends RuntimeException {

    public RestEntityNotFoundException(String message) {
        super(message);
    }

}
