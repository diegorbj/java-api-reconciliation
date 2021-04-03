package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.repositories.SourceTransactionRepository;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import com.diegorbj.reconciliation.services.dto.SourceTransactionDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.SourceTransactionMapper;
import com.diegorbj.reconciliation.services.mappers.SourceTransactionMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SourceTransactionService {

    @Autowired
    protected SourceTransactionRepository _repository;

    private SourceTransactionMapper _mapper = new SourceTransactionMapperImpl();

    public List<SourceTransactionDTO> findAll() {
        return _mapper.toDto(_repository.findAll());
    }

    public SourceTransactionDTO findById(Long id) {
        Optional<SourceTransaction> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public SourceTransactionDTO insert(SourceTransactionDTO obj) {
        //TODO - Some validation
        if (true) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new InvalidAttributeException("Some validation failed");
        }
    }

    public SourceTransactionDTO update(Long id, SourceTransactionDTO obj) {
        //TODO - Some validation
        if (true) {
            if (obj.getId().equals(id)) {
                SourceTransactionDTO currentState = this.findById(id);
                updateData(obj, currentState);
                return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
            } else {
                throw new InvalidAttributeException("Inconsistent value for Id");
            }
        } else {
            throw new InvalidAttributeException("Some validation failed");
        }
    }

    public void delete(Long id) {
        Optional<SourceTransaction> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    protected void updateData(SourceTransactionDTO from, SourceTransactionDTO to) {
        to.setDate(from.getDate());
        to.setUniqueSequentialNumber(from.getUniqueSequentialNumber());
        to.setTransactionId(from.getTransactionId());
        to.setAuthorizationCode(from.getAuthorizationCode());
        to.setNumberOfInstallments(from.getNumberOfInstallments());
        to.setGrossAmount(from.getGrossAmount());
        to.setTransactionInformation(from.getTransactionInformation());
        to.setMerchant(from.getMerchant());
        to.setFinancialInstitution(from.getFinancialInstitution());
        to.setFinancialService(from.getFinancialService());
        to.setServiceLabel(from.getServiceLabel());
        to.setCardType(from.getCardType());
        to.setModality(from.getModality());
        if (from.getInstallments() != null) {
            for (InstallmentDTO i : from.getInstallments()) {
                to.getInstallments().add(i);
            }
        }
    }

}
