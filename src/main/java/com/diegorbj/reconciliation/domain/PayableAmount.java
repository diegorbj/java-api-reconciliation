package com.diegorbj.reconciliation.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AmountType.PAYABLE")
public class PayableAmount extends Entry {

    @Override
    public Double getAmount() {
        return super.getAmount() * -1;
    }

}
