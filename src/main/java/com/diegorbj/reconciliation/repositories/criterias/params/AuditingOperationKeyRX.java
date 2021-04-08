package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.AuditingOperation;

public class AuditingOperationKeyRX extends AuditingOperationFilterParam {

    public AuditingOperationKeyRX(AuditingOperation auditingOperation) {
        this.setFinancialInstitution(auditingOperation.getFinancialInstitution());
        this.setDateFrom(auditingOperation.getDate());
        this.setDateTo(auditingOperation.getDate());
        this.setUniqueSequentialNumber(auditingOperation.getUniqueSequentialNumber());
    }

}
