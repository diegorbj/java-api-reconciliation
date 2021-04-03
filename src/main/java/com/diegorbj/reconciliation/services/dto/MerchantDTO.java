package com.diegorbj.reconciliation.services.dto;

import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MerchantDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;
    @EqualsAndHashCode.Exclude
    private String name;

    public MerchantDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static MerchantDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static MerchantDTO fromJSON(JSONObject jsonObject) {
        MerchantDTO obj = new MerchantDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        return obj;
    }

}
