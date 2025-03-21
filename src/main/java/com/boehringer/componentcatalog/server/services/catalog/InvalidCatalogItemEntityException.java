package com.boehringer.componentcatalog.server.services.catalog;

public class InvalidCatalogItemEntityException extends RuntimeException {

    public InvalidCatalogItemEntityException(String pathAt) {
        super("Invalid repo catalog item: " + pathAt);
    }
}
