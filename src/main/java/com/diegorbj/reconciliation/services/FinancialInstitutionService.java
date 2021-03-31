package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class FinancialInstitutionService extends GenericService<FinancialInstitution> {

    @Override
    public FinancialInstitution insert(FinancialInstitution obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The financial institution name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public FinancialInstitution update(Long id, FinancialInstitution obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The financial institution name can not be empty");
        } else if (id != obj.getId()){
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(FinancialInstitution from, FinancialInstitution to) {
        to.setName(from.getName());
    }

    public static boolean isJSONValid(JSONObject jsonObject){
        return jsonObject != null
                || (   jsonObject.get("id") == null
                    || Util.isValidLong(jsonObject.get("id").toString()))
                || Util.isValidDescription(jsonObject.get("name").toString());
    }

    public static FinancialInstitution toFinancialInstitution(JSONObject jsonObject){
        if (! isJSONValid(jsonObject)) {
            throw new InvalidAttributeException("Not a valid Financial Institution");
        }
        FinancialInstitution obj = new FinancialInstitution();
        obj.setId(Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name").toString());
        return obj;
    }

}
