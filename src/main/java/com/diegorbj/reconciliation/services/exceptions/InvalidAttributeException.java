package com.diegorbj.reconciliation.services.exceptions;

public class InvalidAttributeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidAttributeException(String message) {
        super(message);
    }
}
