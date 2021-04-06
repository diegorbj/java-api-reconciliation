package com.diegorbj.reconciliation.domain.enums;

import lombok.Getter;

@Getter
public enum FinancialInstitutionCode {
    GFORCE("GS"),
    CODEONE("03"),
    RX("05");

    public final String code;

    FinancialInstitutionCode(String code) {
        this.code = code;
    }

    public static FinancialInstitutionCode value(String code) {
        for (FinancialInstitutionCode s : values()) {
            if (s.getCode().equals(code)) {
                return s;
            }
        }
        return null;
    }

}
