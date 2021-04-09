package com.diegorbj.reconciliation.services.dto;

import lombok.*;
import org.json.JSONObject;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ConfirmInstallmentDTO extends InstallmentDTO {

    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private AuditOperationDTO auditingOperation;

    public ConfirmInstallmentDTO(Long id, Integer quota, Double grossAmount, AuditOperationDTO auditingOperation) {
        super(id, quota,grossAmount);
        this.auditingOperation = auditingOperation;
    }

    public static ConfirmInstallmentDTO fromJSON(String jsonString) {
        return ConfirmInstallmentDTO.fromJSON(new JSONObject(jsonString));
    }

    public static ConfirmInstallmentDTO fromJSON(JSONObject jsonObject) {
        ConfirmInstallmentDTO obj = new ConfirmInstallmentDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setQuota(jsonObject.get("quota") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("quota").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setAuditingOperation(jsonObject.get("operation") == JSONObject.NULL ? null : AuditOperationDTO.fromJSON(jsonObject.get("operation").toString()));
        return obj;
    }

}
