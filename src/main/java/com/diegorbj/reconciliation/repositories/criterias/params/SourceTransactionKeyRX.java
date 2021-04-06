package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.SourceTransaction;

public class SourceTransactionKeyRX extends SourceTransactionFilterParam {

    public SourceTransactionKeyRX(SourceTransaction sourceTransaction) {
        this.setFinancialInstitution(sourceTransaction.getFinancialInstitution());
        this.setDateFrom(sourceTransaction.getDate());
        this.setDateTo(sourceTransaction.getDate());
        this.setUniqueSequentialNumber(sourceTransaction.getUniqueSequentialNumber());
    }

}
