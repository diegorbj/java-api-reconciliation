package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceLabelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public ServiceLabelDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ServiceLabel toDomain() {
        ServiceLabel newObj = new ServiceLabel();
        newObj.setId(this.getId());
        newObj.setName(this.getName());
        return newObj;
    }

    public static ServiceLabelDTO fromDomain(ServiceLabel obj){
        ServiceLabelDTO newObj = new ServiceLabelDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static ServiceLabelDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static ServiceLabelDTO fromJSON(JSONObject jsonObject) {
        ServiceLabelDTO obj = new ServiceLabelDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null: Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name") == JSONObject.NULL ? null: jsonObject.get("name").toString());
        return obj;
    }

}
