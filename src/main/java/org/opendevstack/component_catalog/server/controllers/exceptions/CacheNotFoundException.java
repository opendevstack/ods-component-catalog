package org.opendevstack.component_catalog.server.controllers.exceptions;

public class CacheNotFoundException extends RuntimeException {
    public CacheNotFoundException(String message) {
        super(message);
    }
}
