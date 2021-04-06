package com.diegorbj.reconciliation.repositories.criterias.params;

import com.diegorbj.reconciliation.domain.*;
import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import lombok.Data;

import java.time.Instant;

@Data
public abstract class SourceTransactionFilterParam {
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
    private Merchant merchant;
    private FinancialInstitution financialInstitution;
    private FinancialService financialService;
    private ServiceLabel serviceLabel;
    private CardType cardType;
    private Modality modality;
}
