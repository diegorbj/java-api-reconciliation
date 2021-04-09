package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.Operation;
import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.keys.OperationKeyCodeOne;
import com.diegorbj.reconciliation.repositories.criterias.params.keys.OperationKeyGForce;
import com.diegorbj.reconciliation.repositories.criterias.params.keys.OperationKeyRX;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;

public class OperationFilterParamFactory {

    public static OperationFilterParam create(Operation operation) {
        if (operation.getFinancialInstitution().getCode() == FinancialInstitutionCode.GFORCE) {
            return new OperationKeyGForce(operation);
        } else if (operation.getFinancialInstitution().getCode() == FinancialInstitutionCode.CODEONE) {
            return new OperationKeyCodeOne(operation);
        } else if (operation.getFinancialInstitution().getCode() == FinancialInstitutionCode.RX) {
            return new OperationKeyRX(operation);
        } else {
            throw new InvalidAttributeException("There is no key to associate to the auditing operation");
        }
    }

}
