package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.dto.CardTypeDTO;
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
public class CardTypeService {

    @Autowired
    protected JpaRepository<CardType, Long> _repository;

    public List<CardTypeDTO> findAll() {
        List<CardType> list = _repository.findAll();
        List<CardTypeDTO> newList = new ArrayList<>();
        for (CardType obj : list) {
            newList.add(createToDTO(obj));
        }
        return newList;
    }

    public CardTypeDTO findById(Long id) {
        Optional<CardType> obj = _repository.findById(id);
        return createToDTO(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public CardTypeDTO insert(CardTypeDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            return createToDTO(_repository.save(createFromDTO(obj)));
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    public CardTypeDTO update(Long id, CardTypeDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (id == obj.getId()) {
                CardTypeDTO currentState = this.findById(id);
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
        Optional<CardType> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(CardTypeDTO from, CardTypeDTO to) {
        to.setName(from.getName());
    }

    public static CardTypeDTO createToDTO(CardType obj){
        CardTypeDTO newObj = new CardTypeDTO();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

    public static CardType createFromDTO(CardTypeDTO obj){
        CardType newObj = new CardType();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        return newObj;
    }

}
