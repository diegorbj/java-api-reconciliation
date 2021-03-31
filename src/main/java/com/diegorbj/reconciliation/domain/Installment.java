package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.pk.InstallmentId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_installment")
public class Installment implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @EmbeddedId
    private InstallmentId quota = new InstallmentId();

    private Double grossAmount;

    public Installment(SourceTransaction sourceTransaction, Integer quota, Double grossAmount) {
        this.quota.setSourceTransaction(sourceTransaction);
        this.quota.setNumber(quota);
        this.grossAmount = grossAmount;
    }

}
