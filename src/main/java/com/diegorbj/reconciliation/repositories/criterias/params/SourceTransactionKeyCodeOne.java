package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.SourceTransaction;

public class SourceTransactionKeyCodeOne extends SourceTransactionFilterParam {

    public SourceTransactionKeyCodeOne(SourceTransaction sourceTransaction) {
        this.setFinancialInstitution(sourceTransaction.getFinancialInstitution());
        this.setDateFrom(sourceTransaction.getDate());
        this.setDateTo(sourceTransaction.getDate());
        this.setMerchant(sourceTransaction.getMerchant());
        this.setUniqueSequentialNumber(sourceTransaction.getUniqueSequentialNumber());
        this.setAuthorizationCode(sourceTransaction.getAuthorizationCode());
    }

}
