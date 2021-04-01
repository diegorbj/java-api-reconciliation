package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.Installment;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InstallmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer number;
    private Double grossAmount;
    private SourceTransactionDTO sourceTransaction;

    public InstallmentDTO(Long id, Integer number, Double grossAmount, SourceTransactionDTO sourceTransaction) {
        this.id = id;
        this.number = number;
        this.grossAmount = grossAmount;
        this.sourceTransaction = sourceTransaction;
    }

    public Installment toDomain() {
        Installment newObj = new Installment();
        newObj.setId(this.getId());
        newObj.setNumber(this.getNumber());
        newObj.setGrossAmount(this.getGrossAmount());
        newObj.setSourceTransaction(this.getSourceTransaction().toDomain());
        return newObj;
    }

    public static InstallmentDTO fromDomain(Installment obj) {
        InstallmentDTO newObj = new InstallmentDTO();
        newObj.setId(obj.getId());
        newObj.setNumber(obj.getNumber());
        newObj.setGrossAmount(obj.getGrossAmount());
        newObj.setSourceTransaction(SourceTransactionDTO.fromDomain(obj.getSourceTransaction()));
        return newObj;
    }

}
