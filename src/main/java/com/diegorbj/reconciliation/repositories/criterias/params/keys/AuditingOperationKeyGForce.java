package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditingOperationFilterParam;

public class AuditingOperationKeyGForce extends AuditingOperationFilterParam {

    public AuditingOperationKeyGForce(AuditingOperation auditingOperation) {
        this.setFinancialInstitution(auditingOperation.getFinancialInstitution());
        this.setDateFrom(auditingOperation.getDate());
        this.setDateTo(auditingOperation.getDate());
        this.setMerchant(auditingOperation.getMerchant());
        this.setAuthorizationId(auditingOperation.getAuthorizationId());
        this.setAuthorizationCode(auditingOperation.getAuthorizationCode());
    }

}
