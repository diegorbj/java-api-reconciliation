package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.keys.AuditOperationKeyCodeOne;
import com.diegorbj.reconciliation.repositories.criterias.params.keys.AuditOperationKeyGForce;
import com.diegorbj.reconciliation.repositories.criterias.params.keys.AuditOperationKeyRX;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;

public class AuditOperationFilterParamFactory {

    public static AuditOperationFilterParam create(AuditOperation auditOperation) {
        if (auditOperation.getFinancialInstitution().getCode() == FinancialInstitutionCode.GFORCE) {
            return new AuditOperationKeyGForce(auditOperation);
        } else if (auditOperation.getFinancialInstitution().getCode() == FinancialInstitutionCode.CODEONE) {
            return new AuditOperationKeyCodeOne(auditOperation);
        } else if (auditOperation.getFinancialInstitution().getCode() == FinancialInstitutionCode.RX) {
            return new AuditOperationKeyRX(auditOperation);
        } else {
            throw new InvalidAttributeException("There is no key to associate to the auditing operation");
        }
    }

}
