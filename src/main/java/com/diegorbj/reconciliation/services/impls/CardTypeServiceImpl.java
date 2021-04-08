package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.repositories.CardTypeRepository;
import com.diegorbj.reconciliation.services.CardTypeService;
import com.diegorbj.reconciliation.services.dto.CardTypeDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.CardTypeMapper;
import com.diegorbj.reconciliation.services.mappers.CardTypeMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardTypeServiceImpl implements CardTypeService {

    @Autowired
    protected CardTypeRepository _repository;

    @Autowired
    private CardTypeMapper _mapper;

    @Override
    public List<CardTypeDTO> findAll() {
        List<CardTypeDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public CardTypeDTO findById(Long id) {
        Optional<CardType> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<CardTypeDTO> findByNameIgnoreCase(String name) {
        List<CardTypeDTO> dtoList = _mapper.toDto(_repository.findByNameIgnoreCase(name));
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("Name: '" + name + "'");
        }
        return dtoList;
    }

    @Override
    public CardTypeDTO insert(CardTypeDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            try {
                this.findByNameIgnoreCase(obj.getName().trim());
                throw new InvalidAttributeException("The card type name must be unique");
            } catch (ResourceNotFondException e) {
                return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
            }
        } else {
            throw new InvalidAttributeException("The card type name can't be empty");
        }
    }

    @Override
    public CardTypeDTO update(Long id, CardTypeDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                try {
                    this.findByNameIgnoreCase(obj.getName());
                    throw new InvalidAttributeException("The card type name must be unique");
                } catch (ResourceNotFondException e) {
                    CardTypeDTO currentState = this.findById(id);
                    updateData(obj, currentState);
                    return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
                }
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The card type name can not be empty");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<CardType> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    private void updateData(CardTypeDTO from, CardTypeDTO to) {
        to.setName(from.getName());
    }

}
