package com.diegorbj.reconciliation.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EntryType.RECEIVABLE")
public class ReceivableAmount extends Entry {

}
