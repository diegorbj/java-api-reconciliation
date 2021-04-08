package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;

public class AuditingOperationFilterParamFactory {

    public static AuditingOperationFilterParam create(AuditingOperation auditingOperation) {
        if (auditingOperation.getFinancialInstitution().getCode() == FinancialInstitutionCode.GFORCE) {
            return new AuditingOperationKeyGForce(auditingOperation);
        } else if (auditingOperation.getFinancialInstitution().getCode() == FinancialInstitutionCode.CODEONE) {
            return new AuditingOperationKeyCodeOne(auditingOperation);
        } else if (auditingOperation.getFinancialInstitution().getCode() == FinancialInstitutionCode.RX) {
            return new AuditingOperationKeyRX(auditingOperation);
        } else {
            throw new InvalidAttributeException("There is no key to associate to the auditing operation");
        }
    }

}
