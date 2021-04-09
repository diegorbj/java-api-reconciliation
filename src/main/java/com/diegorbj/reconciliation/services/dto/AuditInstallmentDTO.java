package com.diegorbj.reconciliation.services.dto;

import lombok.*;
import org.json.JSONObject;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AuditInstallmentDTO extends InstallmentDTO {

    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private AuditOperationDTO operation;

    public AuditInstallmentDTO(Long id, Integer quota, Double grossAmount, AuditOperationDTO operation) {
        super(id, quota,grossAmount);
        this.operation = operation;
    }

    public static AuditInstallmentDTO fromJSON(String jsonString) {
        return AuditInstallmentDTO.fromJSON(new JSONObject(jsonString));
    }

    public static AuditInstallmentDTO fromJSON(JSONObject jsonObject) {
        AuditInstallmentDTO obj = new AuditInstallmentDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setQuota(jsonObject.get("quota") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("quota").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setOperation(jsonObject.get("operation") == JSONObject.NULL ? null : AuditOperationDTO.fromJSON(jsonObject.get("operation").toString()));
        return obj;
    }

}
