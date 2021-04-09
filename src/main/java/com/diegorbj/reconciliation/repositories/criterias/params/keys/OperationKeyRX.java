package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.Operation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;

public class OperationKeyRX extends OperationFilterParam {

    public OperationKeyRX(Operation operation) {
        this.setFinancialInstitution(operation.getFinancialInstitution());
        this.setDateFrom(operation.getDate());
        this.setDateTo(operation.getDate());
        this.setAuthorizationId(operation.getAuthorizationId());
    }

}
