package org.opendevstack.component_catalog.server.services.exceptions;

public class InvalidCatalogItemIdStructureException extends RuntimeException {
    public InvalidCatalogItemIdStructureException(String message) {
        super(message);
    }
}
