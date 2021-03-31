package com.diegorbj.reconciliation.services.exceptions;

public class ResourceNotFondException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public ResourceNotFondException(Long id) {
        super("Resource Not Fond: " + id);
    }

}
