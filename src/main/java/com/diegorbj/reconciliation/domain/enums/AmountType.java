package com.diegorbj.reconciliation.domain.enums;

import lombok.Getter;

@Getter
public enum AmountType {
    PAYABLE(0),
    RECEIVABLE(1);

    public final int code;

    AmountType(int code) {
        this.code = code;
    }

    public static AmountType value(int code) {
        for (AmountType s : values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        return null;
    }

}
