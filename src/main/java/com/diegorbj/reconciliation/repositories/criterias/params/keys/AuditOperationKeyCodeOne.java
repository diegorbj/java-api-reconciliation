package com.diegorbj.reconciliation.repositories.criterias.params.keys;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationFilterParam;

public class AuditOperationKeyCodeOne extends AuditOperationFilterParam {

    public AuditOperationKeyCodeOne(AuditOperation auditOperation) {
        this.setFinancialInstitution(auditOperation.getFinancialInstitution());
        this.setDateFrom(auditOperation.getDate());
        this.setDateTo(auditOperation.getDate());
        this.setMerchant(auditOperation.getMerchant());
        this.setPointOfSaleId(auditOperation.getPointOfSaleId());
        this.setAuthorizationCode(auditOperation.getAuthorizationCode());
    }

}
