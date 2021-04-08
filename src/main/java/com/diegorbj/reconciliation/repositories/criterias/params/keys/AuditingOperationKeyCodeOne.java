package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditingOperationFilterParam;

public class AuditingOperationKeyCodeOne extends AuditingOperationFilterParam {

    public AuditingOperationKeyCodeOne(AuditingOperation auditingOperation) {
        this.setFinancialInstitution(auditingOperation.getFinancialInstitution());
        this.setDateFrom(auditingOperation.getDate());
        this.setDateTo(auditingOperation.getDate());
        this.setMerchant(auditingOperation.getMerchant());
        this.setPointOfSaleId(auditingOperation.getPointOfSaleId());
        this.setAuthorizationCode(auditingOperation.getAuthorizationCode());
    }

}
