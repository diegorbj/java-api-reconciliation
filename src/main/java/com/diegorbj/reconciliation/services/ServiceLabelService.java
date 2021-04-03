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
        List<ServiceLabelDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    public ServiceLabelDTO findById(Long id) {
        Optional<ServiceLabel> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    public List<ServiceLabelDTO> findByNameIgnoreCase(String name) {
        List<ServiceLabelDTO> dtoList = _mapper.toDto(_repository.findByNameIgnoreCase(name));
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("Name: '" + name + "'");
        }
        return dtoList;
    }

    public ServiceLabelDTO insert(ServiceLabelDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            try {
                this.findByNameIgnoreCase(obj.getName().trim());
                throw new InvalidAttributeException("The modality must be unique");
            } catch (ResourceNotFondException e) {
                return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
            }
        } else {
            throw new InvalidAttributeException("The modality name can't be empty");
        }
    }

    public ServiceLabelDTO update(Long id, ServiceLabelDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                try {
                    this.findByNameIgnoreCase(obj.getName());
                    throw new InvalidAttributeException("The modality name must be unique");
                } catch (ResourceNotFondException e) {
                    ServiceLabelDTO currentState = this.findById(id);
                    updateData(obj, currentState);
                    return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
                }
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The modality name can not be empty");
        }
    }

    public void delete(Long id) {
        Optional<ServiceLabel> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    private void updateData(ServiceLabelDTO from, ServiceLabelDTO to) {
        to.setName(from.getName());
    }

}
