package com.diegorbj.reconciliation.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_auditing_operation")
public class AuditingOperation extends Operation {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "auditing_operation_id")
    private Set<AuditingInstallment> auditingInstallments = new HashSet<>();

}
