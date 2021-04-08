package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.repositories.FinancialInstitutionRepository;
import com.diegorbj.reconciliation.services.FinancialInstitutionService;
import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.FinancialInstitutionMapper;
import com.diegorbj.reconciliation.services.mappers.FinancialInstitutionMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialInstitutionServiceImpl implements FinancialInstitutionService {

    @Autowired
    protected FinancialInstitutionRepository _repository;

    @Autowired
    private FinancialInstitutionMapper _mapper;

    @Override
    public List<FinancialInstitutionDTO> findAll() {
        List<FinancialInstitutionDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public FinancialInstitutionDTO findById(Long id) {
        Optional<FinancialInstitution> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<FinancialInstitutionDTO> findByNameIgnoreCase(String name) {
        List<FinancialInstitutionDTO> dtoList = _mapper.toDto(_repository.findByNameIgnoreCase(name));
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("Name: '" + name + "'");
        }
        return dtoList;
    }

    @Override
    public FinancialInstitutionDTO insert(FinancialInstitutionDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            try {
                this.findByNameIgnoreCase(obj.getName().trim());
                throw new InvalidAttributeException("The financial institution name must be unique");
            } catch (ResourceNotFondException e) {
                return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
            }
        } else {
            throw new InvalidAttributeException("The financial institution name can't be empty");
        }
    }

    @Override
    public FinancialInstitutionDTO update(Long id, FinancialInstitutionDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                try {
                    this.findByNameIgnoreCase(obj.getName());
                    throw new InvalidAttributeException("The financial institution name must be unique");
                } catch (ResourceNotFondException e) {
                    FinancialInstitutionDTO currentState = this.findById(id);
                    updateData(obj, currentState);
                    return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
                }
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The financial institution name can not be empty");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<FinancialInstitution> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    private void updateData(FinancialInstitutionDTO from, FinancialInstitutionDTO to) {
        to.setName(from.getName());
    }

}
