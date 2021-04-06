package com.diegorbj.reconciliation.services.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public ResourceAlreadyExistsException(String value) {
        super("Resource Already Exists: " + value);
    }

}
