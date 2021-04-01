package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.Modality;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModalityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public ModalityDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Modality toDomain() {
        Modality newObj = new Modality();
        newObj.setId(this.getId());
        newObj.setName(this.getName());
        return newObj;
    }

    public static ModalityDTO fromDomain(Modality obj) {
        ModalityDTO newObj = new ModalityDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static ModalityDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static ModalityDTO fromJSON(JSONObject jsonObject) {
        ModalityDTO obj = new ModalityDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null : jsonObject.get("name").toString());
        return obj;
    }

}
