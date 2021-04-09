package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.Operation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;

public class OperationKeyGForce extends OperationFilterParam {

    public OperationKeyGForce(Operation operation) {
        this.setFinancialInstitution(operation.getFinancialInstitution());
        this.setDateFrom(operation.getDate());
        this.setDateTo(operation.getDate());
        this.setMerchant(operation.getMerchant());
        this.setAuthorizationId(operation.getAuthorizationId());
        this.setAuthorizationCode(operation.getAuthorizationCode());
    }

}
