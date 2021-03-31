package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CardTypeService extends GenericService<CardType> {

    @Override
    public CardType insert(CardType obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The card type name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public CardType update(Long id, CardType obj){
        if (! Util.isValidDescription(obj.getName())){
            throw new InvalidAttributeException("The card type name can not be empty");
        } else if (id != obj.getId()){
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(CardType from, CardType to) {
        to.setName(from.getName());
    }

    public static boolean isJSONValid(JSONObject jsonObject){
        return jsonObject != null
                || (   jsonObject.get("id") == null
                     || Util.isValidLong(jsonObject.get("id").toString()))
                || Util.isValidDescription(jsonObject.get("name").toString());
    }

    public static CardType toCardType(JSONObject jsonObject){
        if (! isJSONValid(jsonObject)) {
            throw new InvalidAttributeException("Not a valid Financial Institution");
        }
        CardType obj = new CardType();
        obj.setId(Long.parseLong(jsonObject.get("id").toString()));
        obj.setName(jsonObject.get("name").toString());
        return obj;
    }

}
