package com.diegorbj.reconciliation.services.dto;

import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AuditInstallmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Exclude
    private Integer quota;
    @EqualsAndHashCode.Exclude
    private Double grossAmount;
    @EqualsAndHashCode.Exclude
    private AuditOperationDTO auditingOperation;

    public AuditInstallmentDTO(Long id, Integer quota, Double grossAmount, AuditOperationDTO auditingOperation) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
        this.auditingOperation = auditingOperation;
    }

    public AuditInstallmentDTO(Long id, Integer quota, Double grossAmount) {
        this.id = id;
        this.quota = quota;
        this.grossAmount = grossAmount;
    }

    public static AuditInstallmentDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static AuditInstallmentDTO fromJSON(JSONObject jsonObject) {
        AuditInstallmentDTO obj = new AuditInstallmentDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setQuota(jsonObject.get("quota") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("quota").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setAuditingOperation(jsonObject.get("operation") == JSONObject.NULL ? null : AuditOperationDTO.fromJSON(jsonObject.get("operation").toString()));
        return obj;
    }

}
