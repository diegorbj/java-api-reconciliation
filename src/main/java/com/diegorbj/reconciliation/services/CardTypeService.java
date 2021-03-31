package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class CardTypeService extends GenericService<CardType> {

    @Override
    public CardType insert(CardType obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public CardType update(Long id, CardType obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The card type name can not be empty");
        } else if (id != obj.getId()) {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(CardType from, CardType to) {
        to.setName(from.getName());
    }

}
