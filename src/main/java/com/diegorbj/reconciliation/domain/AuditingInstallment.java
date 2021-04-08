package com.diegorbj.reconciliation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tb_auditingInstallment")
public class AuditingInstallment implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private Integer quota;
    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private Double grossAmount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditing_operation_id")
    private AuditingOperation operation;

    public AuditingInstallment(Long id, Integer quota, Double grossAmount, AuditingOperation operation) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
        this.operation = operation;
    }

    public AuditingInstallment(Long id, Integer quota, Double grossAmount) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
    }

}
