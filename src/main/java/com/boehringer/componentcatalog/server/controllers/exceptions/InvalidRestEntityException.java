package com.boehringer.componentcatalog.server.controllers.exceptions;

public class InvalidRestEntityException extends RuntimeException {

    public InvalidRestEntityException(String message) {
        super(message);
    }

}
