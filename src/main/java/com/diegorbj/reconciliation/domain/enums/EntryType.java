package com.diegorbj.reconciliation.domain.enums;

import lombok.Getter;

@Getter
public enum EntryType {
    GROSS(0),
    MERCHANT_FEE(1),
    ISSUING_BANK_FEE(2),
    SCHEME_FEE(3),
    ACQUIRER_FEE(4),
    NET(5),
    CHARGE(6);

    public final int code;

    EntryType(int code) {
        this.code = code;
    }

    public static EntryType value(int code) {
        for (EntryType s : values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        return null;
    }

}
