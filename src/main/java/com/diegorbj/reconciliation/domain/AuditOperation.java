package com.diegorbj.reconciliation.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_audit_operation")
public class AuditOperation extends Operation {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "audit_operation_id")
    private Set<AuditInstallment> auditInstallments = new HashSet<>();

}
