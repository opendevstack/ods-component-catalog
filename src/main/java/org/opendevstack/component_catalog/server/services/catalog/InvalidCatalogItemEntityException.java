package org.opendevstack.component_catalog.server.services.catalog;

public class InvalidCatalogItemEntityException extends RuntimeException {

    public InvalidCatalogItemEntityException(String pathAt) {
        super("Invalid repo catalog item: " + pathAt);
    }
}
