package com.diegorbj.reconciliation.domain;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_audit_operation")
public class AuditOperation extends Operation {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "audit_operation_id")
    private Set<AuditInstallment> installments = new HashSet<>();

    public AuditOperation(Long id, Instant date, Long authorizationId, Long pointOfSaleId, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, String rebateInformation, Merchant merchant, FinancialInstitution financialInstitution, FinancialService financialService, ServiceLabel serviceLabel, CardType cardType, Modality modality) {
        super(id, date, authorizationId, pointOfSaleId, transactionId, authorizationCode, transactionStatus, numberOfInstallments, grossAmount, transactionInformation, rebateInformation, merchant, financialInstitution, financialService, serviceLabel, cardType, modality);
    }

}
