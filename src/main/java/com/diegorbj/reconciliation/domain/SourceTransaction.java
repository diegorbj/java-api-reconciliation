package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sourcetransaction")
public class SourceTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;
    private Long uniqueSequentialNumber;
    private String transactionId;
    private String authorizationCode;
    private TransactionStatus transactionStatus;
    private Integer numberOfInstallments;
    private Double grossAmount;
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

    @OneToMany(mappedBy = "quota.sourceTransaction")
    private Set<Installment> installments = new HashSet<>();

    public SourceTransaction(Long id, Instant date, Long uniqueSequentialNumber, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, Merchant merchant, FinancialInstitution financialInstitution, FinancialService financialService, ServiceLabel serviceLabel, CardType cardType, Modality modality) {
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
        this.installments = installments;
    }
}
