package com.diegorbj.reconciliation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_confirm_installment")
public class ConfirmInstallment extends Installment {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_operation_id")
    private ConfirmOperation operation;

    public ConfirmInstallment(Long id, Integer quota, Double grossAmount, ConfirmOperation operation) {
        super(id,quota,grossAmount);
        this.operation = operation;
    }

}
