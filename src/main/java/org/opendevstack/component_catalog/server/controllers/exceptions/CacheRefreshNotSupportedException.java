package org.opendevstack.component_catalog.server.controllers.exceptions;

public class CacheRefreshNotSupportedException extends RuntimeException {
    public CacheRefreshNotSupportedException(String message) {
        super(message);
    }
}
