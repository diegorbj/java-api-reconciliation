package com.diegorbj.reconciliation.services.exceptions;

public class DatabaseException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public DatabaseException(String msg) {
        super(msg);
    }

}
