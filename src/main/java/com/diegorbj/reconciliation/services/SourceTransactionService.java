package com.diegorbj.reconciliation.services;


import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.domain.SourceTransaction;
import org.springframework.stereotype.Service;

@Service
public class SourceTransactionService extends GenericService<SourceTransaction> {

    @Override
    protected void updateData(SourceTransaction from, SourceTransaction to) {
        to.setDate(from.getDate());
        to.setUniqueSequentialNumber(from.getUniqueSequentialNumber());
        to.setTransactionId(from.getTransactionId());
        to.setAuthorizationCode(from.getAuthorizationCode());
        to.setNumberOfInstallments(from.getNumberOfInstallments());
        to.setGrossAmount(from.getGrossAmount());
        to.setTransactionInformation(from.getTransactionInformation());
        to.setMerchant(from.getMerchant());
        to.setFinancialInstitution(from.getFinancialInstitution());
        to.setFinancialService(from.getFinancialService());
        to.setServiceLabel(from.getServiceLabel());
        to.setCardType(from.getCardType());
        to.setModality(from.getModality());
        for (Installment i : from.getInstallments()) {
            to.getInstallments().add(i);
        }
    }

}
