package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Modality;
import com.diegorbj.reconciliation.repositories.ModalityRepository;
import com.diegorbj.reconciliation.services.dto.ModalityDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModalityService {

    @Autowired
    protected ModalityRepository _repository;

    public List<ModalityDTO> findAll() {
        List<Modality> list = _repository.findAll();
        List<ModalityDTO> listDTO = new ArrayList<>();
        for (Modality obj : list) {
            listDTO.add(ModalityDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public ModalityDTO findById(Long id) {
        Optional<Modality> obj = _repository.findById(id);
        return ModalityDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public ModalityDTO insert(ModalityDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return ModalityDTO.fromDomain(_repository.save(obj.toDomain()));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public ModalityDTO update(Long id, ModalityDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                ModalityDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return ModalityDTO.fromDomain(_repository.save(currentState.toDomain()));
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
