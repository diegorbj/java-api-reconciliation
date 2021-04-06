package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FinancialInstitutionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private String code;

    public FinancialInstitutionDTO(Long id, String name, FinancialInstitutionCode code) {
        this.id = id;
        this.name = name;
        this.setCode(code);
    }

    public FinancialInstitutionCode getCode(){
        return FinancialInstitutionCode.value(this.code);
    }

    public void setCode(FinancialInstitutionCode code){
        this.code = code.getCode();
    }

    public static FinancialInstitutionDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static FinancialInstitutionDTO fromJSON(JSONObject jsonObject) {
        FinancialInstitutionDTO obj = new FinancialInstitutionDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        obj.setCode(jsonObject.get("code") == JSONObject.NULL ? null : FinancialInstitutionCode.valueOf(jsonObject.get("code").toString()));
        return obj;
    }

}