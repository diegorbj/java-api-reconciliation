package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.diegorbj.reconciliation.services.dto.*;
import lombok.Data;

import java.time.Instant;

@Data
public abstract class SourceTransactionFilterParamDTO {
    private Instant dateFrom;
    private Instant dateTo;
    private Long uniqueSequentialNumber;
    private String transactionId;
    private String authorizationCode;
    private TransactionStatus transactionStatus;
    private Integer numberOfInstallmentsFrom;
    private Integer numberOfInstallmentsTo;
    private Double grossAmountFrom;
    private Double grossAmountTo;
    private String transactionInformation;
    private MerchantDTO merchant;
    private FinancialInstitutionDTO financialInstitution;
    private FinancialServiceDTO financialService;
    private ServiceLabelDTO serviceLabel;
    private CardTypeDTO cardType;
    private ModalityDTO modality;
}
