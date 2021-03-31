package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Modality;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class ModalityService extends GenericService<Modality> {

    @Override
    public Modality insert(Modality obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The modality name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public Modality update(Long id, Modality obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The modality name can not be empty");
        } else if (id != obj.getId()) {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(Modality from, Modality to) {
        to.setName(from.getName());
    }

}
