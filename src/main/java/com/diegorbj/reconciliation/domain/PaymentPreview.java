package com.diegorbj.reconciliation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_payment_preview")
public class PaymentPreview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Instant processingDate;
    @Column(nullable = false)
    private Instant previewDate;
    @Column(nullable = false, length = 50)
    private String order;
    @Column(nullable = false)
    private Double grossAmount;
    @Column(nullable = false)
    private Double commissionAmount;
    @Column(nullable = false)
    private Double netAmount;
    @Column(nullable = false)
    private Double previewAmount;
    @Column(nullable = false)

    private double numberOfInstallments;

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


}
