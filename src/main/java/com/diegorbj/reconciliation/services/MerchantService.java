package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.dto.MerchantDTO;
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
public class MerchantService {

    @Autowired
    protected JpaRepository<Merchant, Long> _repository;

    public List<MerchantDTO> findAll() {
        List<Merchant> list = _repository.findAll();
        List<MerchantDTO> newList = new ArrayList<>();
        for (Merchant obj : list) {
            newList.add(createToDTO(obj));
        }
        return newList;
    }

    public MerchantDTO findById(Long id) {
        Optional<Merchant> obj = _repository.findById(id);
        return createToDTO(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public MerchantDTO insert(MerchantDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return createToDTO(_repository.save(createFromDTO(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public MerchantDTO update(Long id, MerchantDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (id == obj.getId()) {
                MerchantDTO currentState = this.findById(id);
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
        Optional<Merchant> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(MerchantDTO from, MerchantDTO to) {
        to.setName(from.getName());
    }

    public static MerchantDTO createToDTO(Merchant obj){
        MerchantDTO newObj = new MerchantDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static Merchant createFromDTO(MerchantDTO obj){
        Merchant newObj = new Merchant();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

}
