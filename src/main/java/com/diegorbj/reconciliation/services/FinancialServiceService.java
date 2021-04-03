package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.repositories.FinancialServiceRepository;
import com.diegorbj.reconciliation.services.dto.FinancialServiceDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.FinancialServiceMapper;
import com.diegorbj.reconciliation.services.mappers.FinancialServiceMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialServiceService {

    @Autowired
    protected FinancialServiceRepository _repository;

    private FinancialServiceMapper _mapper = new FinancialServiceMapperImpl();

    public List<FinancialServiceDTO> findAll() {
        List<FinancialServiceDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    public FinancialServiceDTO findById(Long id) {
        Optional<FinancialService> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    public List<FinancialServiceDTO> findByNameIgnoreCase(String name) {
        List<FinancialServiceDTO> dtoList = _mapper.toDto(_repository.findByNameIgnoreCase(name));
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("Name: '" + name + "'");
        }
        return dtoList;
    }

    public FinancialServiceDTO insert(FinancialServiceDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            try {
                this.findByNameIgnoreCase(obj.getName().trim());
                throw new InvalidAttributeException("The financial service name must be unique");
            } catch (ResourceNotFondException e) {
                return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
            }
        } else {
            throw new InvalidAttributeException("The financial service name can't be empty");
        }
    }

    public FinancialServiceDTO update(Long id, FinancialServiceDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                try {
                    this.findByNameIgnoreCase(obj.getName());
                    throw new InvalidAttributeException("The financial service name must be unique");
                } catch (ResourceNotFondException e) {
                    FinancialServiceDTO currentState = this.findById(id);
                    updateData(obj, currentState);
                    return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
                }
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The financial service name can not be empty");
        }
    }

    public void delete(Long id) {
        Optional<FinancialService> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    private void updateData(FinancialServiceDTO from, FinancialServiceDTO to) {
        to.setName(from.getName());
    }

}
