package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.FinancialInstitutionMapper;
import com.diegorbj.reconciliation.services.mappers.FinancialInstitutionMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialInstitutionService {

    @Autowired
    protected JpaRepository<FinancialInstitution, Long> _repository;

    private FinancialInstitutionMapper _mapper = new FinancialInstitutionMapperImpl();

    public List<FinancialInstitutionDTO> findAll() {
        return _mapper.toDto(_repository.findAll());
    }

    public FinancialInstitutionDTO findById(Long id) {
        Optional<FinancialInstitution> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public FinancialInstitutionDTO insert(FinancialInstitutionDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public FinancialInstitutionDTO update(Long id, FinancialInstitutionDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                FinancialInstitutionDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
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
