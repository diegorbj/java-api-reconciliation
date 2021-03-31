package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MerchantService extends GenericService<Merchant> {

    @Override
    public Merchant insert(Merchant obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The merchant name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public Merchant update(Long id, Merchant obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The merchant name can not be empty");
        } else if (id != obj.getId()){
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(Merchant from, Merchant to) {
        to.setName(from.getName());
    }

    public static boolean isJSONValid(JSONObject jsonObject){
        return jsonObject != null
                || (   jsonObject.get("id") == null
                || Util.isValidLong(jsonObject.get("id").toString()))
                || Util.isValidDescription(jsonObject.get("name").toString());
    }

    public static Merchant toMerchant(JSONObject jsonObject){
        if (! isJSONValid(jsonObject)) {
            throw new InvalidAttributeException("Not a valid Merchant");
        }
        Merchant obj = new Merchant();
        obj.setId(Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name").toString());
        return obj;
    }

}
