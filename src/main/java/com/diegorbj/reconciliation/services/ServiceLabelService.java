package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.services.dto.ServiceLabelDTO;
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
public class ServiceLabelService {

    @Autowired
    protected JpaRepository<ServiceLabel, Long> _repository;

    public List<ServiceLabelDTO> findAll() {
        List<ServiceLabel> list = _repository.findAll();
        List<ServiceLabelDTO> newList = new ArrayList<>();
        for (ServiceLabel obj : list) {
            newList.add(createToDTO(obj));
        }
        return newList;
    }

    public ServiceLabelDTO findById(Long id) {
        Optional<ServiceLabel> obj = _repository.findById(id);
        return createToDTO(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public ServiceLabelDTO insert(ServiceLabelDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return createToDTO(_repository.save(createFromDTO(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public ServiceLabelDTO update(Long id, ServiceLabelDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (id == obj.getId()) {
                ServiceLabelDTO currentState = this.findById(id);
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
        Optional<ServiceLabel> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(ServiceLabelDTO from, ServiceLabelDTO to) {
        to.setName(from.getName());
    }

    public static ServiceLabelDTO createToDTO(ServiceLabel obj){
        ServiceLabelDTO newObj = new ServiceLabelDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static ServiceLabel createFromDTO(ServiceLabelDTO obj){
        ServiceLabel newObj = new ServiceLabel();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

}
