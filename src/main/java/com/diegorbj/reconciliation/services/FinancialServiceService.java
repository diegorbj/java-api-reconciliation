package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.utils.Util;
import org.springframework.stereotype.Service;

@Service
public class FinancialServiceService extends GenericService<FinancialService> {

    @Override
    public FinancialService insert(FinancialService obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The financial service name can't be empty");
        }
        return super.insert(obj);
    }

    @Override
    public FinancialService update(Long id, FinancialService obj) {
        if (!Util.isValidDescription(obj.getName())) {
            throw new InvalidAttributeException("The financial service name can not be empty");
        } else if (id != obj.getId()) {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
        return super.update(id, obj);
    }

    @Override
    protected void updateData(FinancialService from, FinancialService to) {
        to.setName(from.getName());
    }

}
