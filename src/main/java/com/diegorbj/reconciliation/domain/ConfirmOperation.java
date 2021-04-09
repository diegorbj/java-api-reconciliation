package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_confirm_operation")
public class ConfirmOperation extends Operation {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "confirm_operation_id")
    private Set<ConfirmInstallment> installments = new HashSet<>();

    public ConfirmOperation(Long id, Instant date, Long authorizationId, Long pointOfSaleId, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, String rebateInformation, Merchant merchant, FinancialInstitution financialInstitution, FinancialService financialService, ServiceLabel serviceLabel, CardType cardType, Modality modality) {
        super(id, date, authorizationId, pointOfSaleId, transactionId, authorizationCode, transactionStatus, numberOfInstallments, grossAmount, transactionInformation, rebateInformation, merchant, financialInstitution, financialService, serviceLabel, cardType, modality);
    }

}
