package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.FinancialService;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FinancialServiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public FinancialServiceDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FinancialService toDomain() {
        FinancialService newObj = new FinancialService();
        newObj.setId(this.getId());
        newObj.setName(this.getName());
        return newObj;
    }

    public static FinancialServiceDTO fromDomain(FinancialService obj) {
        FinancialServiceDTO newObj = new FinancialServiceDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static FinancialServiceDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static FinancialServiceDTO fromJSON(JSONObject jsonObject) {
        FinancialServiceDTO obj = new FinancialServiceDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        return obj;
    }

}