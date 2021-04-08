package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import lombok.*;

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
@EqualsAndHashCode
@Entity
@Table(name = "tb_auditingOperation")
public class AuditingOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Instant date;
    @Column(nullable = true)
    private Long uniqueSequentialNumber;
    @Column(nullable = true, length = 50)
    private String transactionId;
    @Column(nullable = true, length = 10)
    private String authorizationCode;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private TransactionStatus transactionStatus;
    @Column(nullable = false)
    private Integer numberOfInstallments;
    @Column(nullable = false)
    private Double grossAmount;
    @Column(nullable = true, length = 100)
    private String transactionInformation;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "financialInstitution_id")
    private FinancialInstitution financialInstitution;

    @ManyToOne
    @JoinColumn(name = "financialService_id")
    private FinancialService financialService;

    @ManyToOne
    @JoinColumn(name = "serviceLabel_id")
    private ServiceLabel serviceLabel;

    @ManyToOne
    @JoinColumn(name = "cardType_id")
    private CardType cardType;

    @ManyToOne
    @JoinColumn(name = "modality_id")
    private Modality modality;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "auditing_operation_id")
    private Set<AuditingInstallment> auditingInstallments = new HashSet<>();

    public AuditingOperation(Long id, Instant date, Long uniqueSequentialNumber, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, Merchant merchant, FinancialInstitution financialInstitution, FinancialService financialService, ServiceLabel serviceLabel, CardType cardType, Modality modality) {
        this.id = id;
        this.date = date;
        this.uniqueSequentialNumber = uniqueSequentialNumber;
        this.transactionId = transactionId;
        this.authorizationCode = authorizationCode;
        this.transactionStatus = transactionStatus;
        this.numberOfInstallments = numberOfInstallments;
        this.grossAmount = grossAmount;
        this.transactionInformation = transactionInformation;
        this.merchant = merchant;
        this.financialInstitution = financialInstitution;
        this.financialService = financialService;
        this.serviceLabel = serviceLabel;
        this.cardType = cardType;
        this.modality = modality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditingOperation that = (AuditingOperation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
