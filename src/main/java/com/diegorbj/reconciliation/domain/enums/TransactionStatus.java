package com.diegorbj.reconciliation.domain.enums;

public enum TransactionStatus {
    PENDING(0),
    APPROVED(1),
    CANCELED(2),
    UNDONE(3);

    public final int code;

    TransactionStatus(int code) {
        this.code = code;
    }

    public static TransactionStatus valueOf(int code) {
        for (TransactionStatus s : values()) {
            if (s.code == code) {
                return s;
            }
        }
        return null;
    }

}
