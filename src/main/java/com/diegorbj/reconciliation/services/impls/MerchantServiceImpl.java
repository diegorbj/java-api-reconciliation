package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.repositories.MerchantRepository;
import com.diegorbj.reconciliation.services.MerchantService;
import com.diegorbj.reconciliation.services.dto.MerchantDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.MerchantMapper;
import com.diegorbj.reconciliation.services.mappers.MerchantMapperImpl;
import com.diegorbj.reconciliation.services.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    protected MerchantRepository _repository;

    private MerchantMapper _mapper = new MerchantMapperImpl();

    @Override
    public List<MerchantDTO> findAll() {
        List<MerchantDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public MerchantDTO findById(Long id) {
        Optional<Merchant> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<MerchantDTO> findByNameIgnoreCase(String name) {
        List<MerchantDTO> dtoList = _mapper.toDto(_repository.findByNameIgnoreCase(name));
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("Name: '" + name + "'");
        }
        return dtoList;
    }

    @Override
    public MerchantDTO insert(MerchantDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            try {
                this.findByNameIgnoreCase(obj.getName().trim());
                throw new InvalidAttributeException("The merchant name must be unique");
            } catch (ResourceNotFondException e) {
                return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
            }
        } else {
            throw new InvalidAttributeException("The merchant name can't be empty");
        }
    }

    @Override
    public MerchantDTO update(Long id, MerchantDTO obj) {
        if (ServiceUtil.isValidDescription(obj.getName())) {
            if (obj.getId().equals(id)) {
                try {
                    this.findByNameIgnoreCase(obj.getName());
                    throw new InvalidAttributeException("The merchant name must be unique");
                } catch (ResourceNotFondException e) {
                    MerchantDTO currentState = this.findById(id);
                    updateData(obj, currentState);
                    return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
                }
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("The merchant name can not be empty");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Merchant> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    private void updateData(MerchantDTO from, MerchantDTO to) {
        to.setName(from.getName());
    }

}
