package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.repositories.MerchantRepository;
import com.diegorbj.reconciliation.services.dto.MerchantDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {

    @Autowired
    protected MerchantRepository _repository;

    public List<MerchantDTO> findAll() {
        List<Merchant> list = _repository.findAll();
        List<MerchantDTO> listDTO = new ArrayList<>();
        for (Merchant obj : list) {
            listDTO.add(MerchantDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public MerchantDTO findById(Long id) {
        Optional<Merchant> obj = _repository.findById(id);
        return MerchantDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public MerchantDTO insert(MerchantDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return MerchantDTO.fromDomain(_repository.save(obj.toDomain()));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public MerchantDTO update(Long id, MerchantDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                MerchantDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return MerchantDTO.fromDomain(_repository.save(currentState.toDomain()));
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The card type name can not be empty");
        }
    }

    public void delete(Long id) {
        Optional<Merchant> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(MerchantDTO from, MerchantDTO to) {
        to.setName(from.getName());
    }

}
