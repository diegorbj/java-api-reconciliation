package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.CardType;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public CardTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CardType toDomain() {
        CardType newObj = new CardType();
        newObj.setId(this.getId());
        newObj.setName(this.getName());
        return newObj;
    }

    public static CardTypeDTO fromDomain(CardType obj) {
        CardTypeDTO newObj = new CardTypeDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static CardTypeDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static CardTypeDTO fromJSON(JSONObject jsonObject) {
        CardTypeDTO obj = new CardTypeDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        return obj;
    }

}
