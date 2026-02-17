package org.opendevstack.component_catalog.server.services.catalog;

public class InvalidCatalogEntityException extends RuntimeException {

    public InvalidCatalogEntityException(String pathAt) {
        super("Invalid repo catalog: " + pathAt);
    }
}
