package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class MerchantService extends GenericService<Merchant> {

    @Override
    public Merchant insert(Merchant obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The merchant name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public Merchant update(Long id, Merchant obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The merchant name can not be empty");
        } else if (id != obj.getId()) {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(Merchant from, Merchant to) {
        to.setName(from.getName());
    }

}
