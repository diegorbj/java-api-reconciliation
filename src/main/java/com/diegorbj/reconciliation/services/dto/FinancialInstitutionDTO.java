package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FinancialInstitutionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public FinancialInstitutionDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FinancialInstitution toDomain() {
        FinancialInstitution newObj = new FinancialInstitution();
        newObj.setId(this.getId());
        newObj.setName(this.getName());
        return newObj;
    }

    public static FinancialInstitutionDTO fromDomain(FinancialInstitution obj) {
        FinancialInstitutionDTO newObj = new FinancialInstitutionDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static FinancialInstitutionDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static FinancialInstitutionDTO fromJSON(JSONObject jsonObject) {
        FinancialInstitutionDTO obj = new FinancialInstitutionDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        return obj;
    }

}