package com.diegorbj.reconciliation.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    PENDING(0),
    APPROVED(1),
    CANCELED(2),
    UNDONE(3);

    public final int code;

    TransactionStatus(int code) {
        this.code = code;
    }

    public static TransactionStatus value(int code) {
        for (TransactionStatus s : values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        return null;
    }

}
