package com.boehringer.componentcatalog.server.controllers.exceptions;

public class RestEntityNotFoundException extends RuntimeException {

    public RestEntityNotFoundException(String message) {
        super(message);
    }

}
