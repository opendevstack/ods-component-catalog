package org.opendevstack.component_catalog.server.services.slug;

/**
 * Thrown when a composite catalog item slug cannot be parsed or is otherwise invalid.
 */
public class InvalidCatalogItemSlugException extends RuntimeException {

    public InvalidCatalogItemSlugException(String message) {
        super(message);
    }
}

