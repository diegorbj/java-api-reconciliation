package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ServiceLabelService extends GenericService<ServiceLabel> {

    @Override
    public ServiceLabel insert(ServiceLabel obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The service label name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public ServiceLabel update(Long id, ServiceLabel obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The service label name can not be empty");
        } else if (id != obj.getId()){
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(ServiceLabel from, ServiceLabel to) {
        to.setName(from.getName());
    }

    public static boolean isJSONValid(JSONObject jsonObject){
        return jsonObject != null
                || (   jsonObject.get("id") == null
                || Util.isValidLong(jsonObject.get("id").toString()))
                || Util.isValidDescription(jsonObject.get("name").toString());
    }

    public static ServiceLabel toServiceLabel(JSONObject jsonObject){
        if (! isJSONValid(jsonObject)) {
            throw new InvalidAttributeException("Not a valid Financial Institution");
        }
        ServiceLabel obj = new ServiceLabel();
        obj.setId(Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name").toString());
        return obj;
    }

}
