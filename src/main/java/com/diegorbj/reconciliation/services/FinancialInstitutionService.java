package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
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
public class FinancialInstitutionService {

    @Autowired
    protected JpaRepository<FinancialInstitution, Long> _repository;

    public List<FinancialInstitutionDTO> findAll() {
        List<FinancialInstitution> list = _repository.findAll();
        List<FinancialInstitutionDTO> listDTO = new ArrayList<>();
        for (FinancialInstitution obj : list) {
            listDTO.add(FinancialInstitutionDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public FinancialInstitutionDTO findById(Long id) {
        Optional<FinancialInstitution> obj = _repository.findById(id);
        return FinancialInstitutionDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public FinancialInstitutionDTO insert(FinancialInstitutionDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return FinancialInstitutionDTO.fromDomain(_repository.save(obj.toDomain()));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public FinancialInstitutionDTO update(Long id, FinancialInstitutionDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (id == obj.getId()) {
                FinancialInstitutionDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return FinancialInstitutionDTO.fromDomain(_repository.save(currentState.toDomain()));
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The card type name can not be empty");
        }
    }

    public void delete(Long id) {
        Optional<FinancialInstitution> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(FinancialInstitutionDTO from, FinancialInstitutionDTO to) {
        to.setName(from.getName());
    }

}
