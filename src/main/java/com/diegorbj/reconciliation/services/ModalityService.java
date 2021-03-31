package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Modality;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ModalityService extends GenericService<Modality> {

    @Override
    public Modality insert(Modality obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The modality name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public Modality update(Long id, Modality obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The modality name can not be empty");
        } else if (id != obj.getId()){
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(Modality from, Modality to) {
        to.setName(from.getName());
    }

    public static boolean isJSONValid(JSONObject jsonObject){
        return jsonObject != null
                || (   jsonObject.get("id") == null
                || Util.isValidLong(jsonObject.get("id").toString()))
                || Util.isValidDescription(jsonObject.get("name").toString());
    }

    public static Modality toModality(JSONObject jsonObject){
        if (! isJSONValid(jsonObject)) {
            throw new InvalidAttributeException("Not a valid Financial Institution");
        }
        Modality obj = new Modality();
        obj.setId(Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name").toString());
        return obj;
    }

}
