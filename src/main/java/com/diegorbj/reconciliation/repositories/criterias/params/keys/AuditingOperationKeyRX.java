package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditingOperationFilterParam;

public class AuditingOperationKeyRX extends AuditingOperationFilterParam {

    public AuditingOperationKeyRX(AuditingOperation auditingOperation) {
        this.setFinancialInstitution(auditingOperation.getFinancialInstitution());
        this.setDateFrom(auditingOperation.getDate());
        this.setDateTo(auditingOperation.getDate());
        this.setAuthorizationId(auditingOperation.getAuthorizationId());
    }

}
