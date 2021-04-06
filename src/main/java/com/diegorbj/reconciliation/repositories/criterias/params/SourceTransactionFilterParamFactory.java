package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;

public class SourceTransactionFilterParamFactory {

    public static SourceTransactionFilterParam create(SourceTransaction sourceTransaction) {
        if (sourceTransaction.getFinancialInstitution().getCode() == FinancialInstitutionCode.GFORCE) {
            return new SourceTransactionKeyGForce(sourceTransaction);
        } else if (sourceTransaction.getFinancialInstitution().getCode() == FinancialInstitutionCode.CODEONE) {
            return new SourceTransactionKeyCodeOne(sourceTransaction);
        } else if (sourceTransaction.getFinancialInstitution().getCode() == FinancialInstitutionCode.RX) {
            return new SourceTransactionKeyRX(sourceTransaction);
        } else {
            throw new InvalidAttributeException("There is no key to associate to the source transaction");
        }
    }

}
