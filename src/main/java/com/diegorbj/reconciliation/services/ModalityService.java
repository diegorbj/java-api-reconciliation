package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Modality;
import com.diegorbj.reconciliation.repositories.ModalityRepository;
import com.diegorbj.reconciliation.services.dto.ModalityDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.ModalityMapper;
import com.diegorbj.reconciliation.services.mappers.ModalityMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalityService {

    @Autowired
    protected ModalityRepository _repository;

    private ModalityMapper _mapper = new ModalityMapperImpl();

    public List<ModalityDTO> findAll() {
        return _mapper.toDto(_repository.findAll());
    }

    public ModalityDTO findById(Long id) {
        Optional<Modality> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public ModalityDTO insert(ModalityDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public ModalityDTO update(Long id, ModalityDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                ModalityDTO currentState = this.findById(id);
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
        Optional<Modality> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(ModalityDTO from, ModalityDTO to) {
        to.setName(from.getName());
    }

}
