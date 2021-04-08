package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.AuditingOperation;

public class AuditingOperationKeyGForce extends AuditingOperationFilterParam {

    public AuditingOperationKeyGForce(AuditingOperation auditingOperation) {
        this.setFinancialInstitution(auditingOperation.getFinancialInstitution());
        this.setDateFrom(auditingOperation.getDate());
        this.setDateTo(auditingOperation.getDate());
        this.setMerchant(auditingOperation.getMerchant());
        this.setUniqueSequentialNumber(auditingOperation.getUniqueSequentialNumber());
        this.setAuthorizationCode(auditingOperation.getAuthorizationCode());
    }

}
