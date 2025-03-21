package com.boehringer.componentcatalog.server.services.exceptions;

public class InvalidEntityException extends Exception {
    public InvalidEntityException(String message) {
        this(message, null);
    }

    public InvalidEntityException(String message, Exception e) {
        super(message, e);
    }
}
