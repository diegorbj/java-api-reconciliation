package com.diegorbj.reconciliation.services.exceptions;

public class ResourceNotFondException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public ResourceNotFondException(String value) {
        super("Resource Not Fond: " + value);
    }

}
