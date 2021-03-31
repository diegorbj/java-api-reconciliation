package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Installment;
import org.springframework.stereotype.Service;

@Service
public class InstallmentService extends GenericService<Installment> {

    @Override
    protected void updateData(Installment from, Installment to) {
        to.setGrossAmount(from.getGrossAmount());
    }

}
