package org.opendevstack.component_catalog.server.services.exceptions;

public class InvalidIdException extends Exception {

    public InvalidIdException(String id) {
        this("Invalid id: " + id, null);
    }

    public InvalidIdException(String id, Exception cause) {
        super("Invalid id: " + id, cause);
    }
}
