package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.Operation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;

public class OperationKeyCodeOne extends OperationFilterParam {

    public OperationKeyCodeOne(Operation operation) {
        this.setFinancialInstitution(operation.getFinancialInstitution());
        this.setDateFrom(operation.getDate());
        this.setDateTo(operation.getDate());
        this.setMerchant(operation.getMerchant());
        this.setPointOfSaleId(operation.getPointOfSaleId());
        this.setAuthorizationCode(operation.getAuthorizationCode());
    }

}
