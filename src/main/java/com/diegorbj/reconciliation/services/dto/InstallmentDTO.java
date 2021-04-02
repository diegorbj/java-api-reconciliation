package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.domain.SourceTransaction;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InstallmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer quota;
    private Double grossAmount;
    private SourceTransactionDTO sourceTransaction;

    public InstallmentDTO(Long id, Integer quota, Double grossAmount, SourceTransactionDTO sourceTransaction) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
        this.sourceTransaction = sourceTransaction;
    }

    public InstallmentDTO(Long id, Integer quota, Double grossAmount) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
    }

    public Installment toDomain() {
        Installment newObj = new Installment();
        newObj.setId(this.getId());
        newObj.setQuota(this.getQuota());
        newObj.setGrossAmount(this.getGrossAmount());
        //newObj.setSourceTransaction(this.getSourceTransaction().toDomain());
        if (this.getSourceTransaction() != null) {
            SourceTransaction st = new SourceTransaction();
            st.setId(this.getSourceTransaction().getId());
            newObj.setSourceTransaction(st);
        }
        return newObj;
    }

    public static InstallmentDTO fromDomain(Installment obj) {
        InstallmentDTO newObj = new InstallmentDTO();
        newObj.setId(obj.getId());
        newObj.setQuota(obj.getQuota());
        newObj.setGrossAmount(obj.getGrossAmount());
        //newObj.setSourceTransaction(SourceTransactionDTO.fromDomain(obj.getSourceTransaction()));
        if (obj.getSourceTransaction() != null) {
            SourceTransactionDTO stDTO = new SourceTransactionDTO();
            stDTO.setId(obj.getSourceTransaction().getId());
            newObj.setSourceTransaction(stDTO);
        }
        return newObj;
    }

    public static InstallmentDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static InstallmentDTO fromJSON(JSONObject jsonObject) {
        InstallmentDTO obj = new InstallmentDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setQuota(jsonObject.get("quota") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("quota").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setSourceTransaction(jsonObject.get("sourceTransaction") == JSONObject.NULL ? null : SourceTransactionDTO.fromJSON(jsonObject.get("sourceTransaction").toString()));
        return obj;
    }

}
