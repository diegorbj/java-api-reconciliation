package com.diegorbj.reconciliation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_statement")
public class Statement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Instant processingDate;
    @Column(nullable = false)
    private Instant captureDate;
    @Column(nullable = false)
    private Merchant merchant;
    @Column(nullable = false, length = 50)
    private String statementId;
    @Column(nullable = false)
    private FinancialService financialService;
    @Column(nullable = false)
    private FinancialInstitution financialInstitution;
    @Column(nullable = false)
    private ServiceLabel serviceLabel;
    @Column(nullable = false)
    private Integer numberOfQuotas;
    @Column(length = 20)
    private String merchantIdentification;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "statement_id")
    private Set<Order> orders = new HashSet<>();

    public Statement(Long id, Instant processingDate, Instant captureDate, Merchant merchant, String statementId, FinancialService financialService, FinancialInstitution financialInstitution, ServiceLabel serviceLabel) {
        this.id = id;
        this.processingDate = processingDate;
        this.captureDate = captureDate;
        this.merchant = merchant;
        this.statementId = statementId;
        this.financialService = financialService;
        this.financialInstitution = financialInstitution;
        this.serviceLabel = serviceLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return id.equals(statement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
