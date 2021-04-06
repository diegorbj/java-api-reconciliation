package com.diegorbj.reconciliation.domain.enums;

import lombok.Getter;

@Getter
public enum FinancialInstitutionCode {
    GFORCE("GF"),
    CODEONE("C1"),
    RX("RX");

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
