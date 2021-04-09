package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class OperationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    protected Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @EqualsAndHashCode.Exclude
    protected Instant date;
    @EqualsAndHashCode.Exclude
    protected Long authorizationId;
    @EqualsAndHashCode.Exclude
    protected Long pointOfSaleId;
    @EqualsAndHashCode.Exclude
    protected String transactionId;
    @EqualsAndHashCode.Exclude
    protected String authorizationCode;
    @EqualsAndHashCode.Exclude
    protected TransactionStatus transactionStatus;
    @EqualsAndHashCode.Exclude
    protected Integer numberOfInstallments;
    @EqualsAndHashCode.Exclude
    protected Double grossAmount;
    @EqualsAndHashCode.Exclude
    protected String transactionInformation;
    @EqualsAndHashCode.Exclude
    protected String rebateInformation;
    @EqualsAndHashCode.Exclude
    protected MerchantDTO merchant;
    @EqualsAndHashCode.Exclude
    protected FinancialInstitutionDTO financialInstitution;
    @EqualsAndHashCode.Exclude
    protected FinancialServiceDTO financialService;
    @EqualsAndHashCode.Exclude
    protected ServiceLabelDTO serviceLabel;
    @EqualsAndHashCode.Exclude
    protected CardTypeDTO cardType;
    @EqualsAndHashCode.Exclude
    protected ModalityDTO modality;

    public OperationDTO(Long id, Instant date, Long authorizationId, Long pointOfSaleId, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, String rebateInformation, MerchantDTO merchant, FinancialInstitutionDTO financialInstitution, FinancialServiceDTO financialService, ServiceLabelDTO serviceLabel, CardTypeDTO cardType, ModalityDTO modality) {
        this.id = id;
        this.date = date;
        this.authorizationId = authorizationId;
        this.pointOfSaleId = pointOfSaleId;
        this.transactionId = transactionId;
        this.authorizationCode = authorizationCode;
        this.transactionStatus = transactionStatus;
        this.numberOfInstallments = numberOfInstallments;
        this.grossAmount = grossAmount;
        this.transactionInformation = transactionInformation;
        this.rebateInformation = rebateInformation;
        this.merchant = merchant;
        this.financialInstitution = financialInstitution;
        this.financialService = financialService;
        this.serviceLabel = serviceLabel;
        this.cardType = cardType;
        this.modality = modality;
    }

}
