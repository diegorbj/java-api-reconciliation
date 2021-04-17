package com.diegorbj.reconciliation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_adjustment")
public class Adjustment implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String identification;
    @Column(length = 50)
    private String protocol;
    @Column(nullable = false)
    private Double netAmount;
    private Instant adjustmentDate;
    private Instant originalTransactionDate;
    private Long originalAuthorizationId;
    private Long originalPointOfSaleId;
    @Column(length = 50)
    private String originalTransactionId;
    @Column(length = 10)
    private String originalAuthorizationCode;
    @Column(length = 100)
    private String originalTransactionInformation;
    @Column(length = 20)
    private String originalMerchantIdentification;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant originalMerchant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confirm_operation_id")
    private ConfirmOperation originalOperation;

    @ManyToOne
    @JoinColumn(name = "adjustment_motive_id")
    private AdjustmentMotive adjustmentMotive;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "entry_id")
    private Entry entry;

    public Adjustment(Long id, String identification, String protocol, Double netAmount, Instant adjustmentDate, Instant originalTransactionDate, Long originalAuthorizationId, Long originalPointOfSaleId, String originalTransactionId, String originalAuthorizationCode, String originalTransactionInformation, String originalMerchantIdentification, Merchant originalMerchant, ConfirmOperation originalOperation, AdjustmentMotive adjustmentMotive, Entry entry) {
        this.id = id;
        this.identification = identification;
        this.protocol = protocol;
        this.netAmount = netAmount;
        this.adjustmentDate = adjustmentDate;
        this.originalTransactionDate = originalTransactionDate;
        this.originalAuthorizationId = originalAuthorizationId;
        this.originalPointOfSaleId = originalPointOfSaleId;
        this.originalTransactionId = originalTransactionId;
        this.originalAuthorizationCode = originalAuthorizationCode;
        this.originalTransactionInformation = originalTransactionInformation;
        this.originalMerchantIdentification = originalMerchantIdentification;
        this.originalMerchant = originalMerchant;
        this.originalOperation = originalOperation;
        this.adjustmentMotive = adjustmentMotive;
        this.entry = entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adjustment that = (Adjustment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
