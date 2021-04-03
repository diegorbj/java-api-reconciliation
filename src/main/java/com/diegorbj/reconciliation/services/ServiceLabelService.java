package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.repositories.ServiceLabelRepository;
import com.diegorbj.reconciliation.services.dto.ServiceLabelDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.ServiceLabelMapper;
import com.diegorbj.reconciliation.services.mappers.ServiceLabelMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLabelService {

    @Autowired
    protected ServiceLabelRepository _repository;

    private ServiceLabelMapper _mapper = new ServiceLabelMapperImpl();

    public List<ServiceLabelDTO> findAll() {
        return _mapper.toDto(_repository.findAll());
    }

    public ServiceLabelDTO findById(Long id) {
        Optional<ServiceLabel> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public ServiceLabelDTO insert(ServiceLabelDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public ServiceLabelDTO update(Long id, ServiceLabelDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                ServiceLabelDTO currentState = this.findById(id);
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
        Optional<ServiceLabel> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(ServiceLabelDTO from, ServiceLabelDTO to) {
        to.setName(from.getName());
    }

}
