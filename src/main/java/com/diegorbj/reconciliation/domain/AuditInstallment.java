package com.diegorbj.reconciliation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_audit_installment")
public class AuditInstallment extends Installment {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_operation_id")
    private AuditOperation operation;

    public AuditInstallment(Long id, Integer quota, Double grossAmount, AuditOperation operation) {
        super(id,quota,grossAmount);
        this.operation = operation;
    }

}
