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
        List<FinancialServiceDTO> newList = new ArrayList<>();
        for (FinancialService obj : list) {
            newList.add(createToDTO(obj));
        }
        return newList;
    }

    public FinancialServiceDTO findById(Long id) {
        Optional<FinancialService> obj = _repository.findById(id);
        return createToDTO(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public FinancialServiceDTO insert(FinancialServiceDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return createToDTO(_repository.save(createFromDTO(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public FinancialServiceDTO update(Long id, FinancialServiceDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (id == obj.getId()) {
                FinancialServiceDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return createToDTO(_repository.save(createFromDTO(currentState)));
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

    public static FinancialServiceDTO createToDTO(FinancialService obj){
        FinancialServiceDTO newObj = new FinancialServiceDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static FinancialService createFromDTO(FinancialServiceDTO obj){
        FinancialService newObj = new FinancialService();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

}
