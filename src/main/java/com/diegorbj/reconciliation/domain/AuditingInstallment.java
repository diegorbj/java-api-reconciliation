package com.diegorbj.reconciliation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_auditing_installment")
public class AuditingInstallment extends Installment {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditing_operation_id")
    private AuditingOperation operation;

    public AuditingInstallment(Long id, Integer quota, Double grossAmount, AuditingOperation operation) {
        super(id,quota,grossAmount);
        this.operation = operation;
    }

}
