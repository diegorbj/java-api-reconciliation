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
    private ConfirmOperationDTO operation;

    public ConfirmInstallmentDTO(Long id, Integer quota, Double grossAmount, ConfirmOperationDTO operation) {
        super(id, quota,grossAmount);
        this.operation = operation;
    }

    public static ConfirmInstallmentDTO fromJSON(String jsonString) {
        return ConfirmInstallmentDTO.fromJSON(new JSONObject(jsonString));
    }

    public static ConfirmInstallmentDTO fromJSON(JSONObject jsonObject) {
        ConfirmInstallmentDTO obj = new ConfirmInstallmentDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setQuota(jsonObject.get("quota") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("quota").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setOperation(jsonObject.get("operation") == JSONObject.NULL ? null : ConfirmOperationDTO.fromJSON(jsonObject.get("operation").toString()));
        return obj;
    }

}
