package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.services.dto.FinancialServiceDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FinancialServiceService {

    @Autowired
    protected JpaRepository<FinancialService, Long> _repository;

    public List<FinancialServiceDTO> findAll() {
        List<FinancialService> list = _repository.findAll();
        List<FinancialServiceDTO> listDTO = new ArrayList<>();
        for (FinancialService obj : list) {
            listDTO.add(FinancialServiceDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public FinancialServiceDTO findById(Long id) {
        Optional<FinancialService> obj = _repository.findById(id);
        return FinancialServiceDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public FinancialServiceDTO insert(FinancialServiceDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return FinancialServiceDTO.fromDomain(_repository.save(obj.toDomain()));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public FinancialServiceDTO update(Long id, FinancialServiceDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                FinancialServiceDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return FinancialServiceDTO.fromDomain(_repository.save(currentState.toDomain()));
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The card type name can not be empty");
        }
    }

    public void delete(Long id) {
        Optional<FinancialService> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(FinancialServiceDTO from, FinancialServiceDTO to) {
        to.setName(from.getName());
    }

}
