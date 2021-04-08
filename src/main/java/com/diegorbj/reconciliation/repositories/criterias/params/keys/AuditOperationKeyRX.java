package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationFilterParam;

public class AuditOperationKeyRX extends AuditOperationFilterParam {

    public AuditOperationKeyRX(AuditOperation auditOperation) {
        this.setFinancialInstitution(auditOperation.getFinancialInstitution());
        this.setDateFrom(auditOperation.getDate());
        this.setDateTo(auditOperation.getDate());
        this.setAuthorizationId(auditOperation.getAuthorizationId());
    }

}
