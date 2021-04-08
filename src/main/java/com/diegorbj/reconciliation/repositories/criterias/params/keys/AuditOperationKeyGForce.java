package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationFilterParam;

public class AuditOperationKeyGForce extends AuditOperationFilterParam {

    public AuditOperationKeyGForce(AuditOperation auditOperation) {
        this.setFinancialInstitution(auditOperation.getFinancialInstitution());
        this.setDateFrom(auditOperation.getDate());
        this.setDateTo(auditOperation.getDate());
        this.setMerchant(auditOperation.getMerchant());
        this.setAuthorizationId(auditOperation.getAuthorizationId());
        this.setAuthorizationCode(auditOperation.getAuthorizationCode());
    }

}
