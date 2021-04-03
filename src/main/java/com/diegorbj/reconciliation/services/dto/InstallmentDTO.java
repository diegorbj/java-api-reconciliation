package com.diegorbj.reconciliation.services.dto;

import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InstallmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Exclude
    private Integer quota;
    @EqualsAndHashCode.Exclude
    private Double grossAmount;
    @EqualsAndHashCode.Exclude
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
