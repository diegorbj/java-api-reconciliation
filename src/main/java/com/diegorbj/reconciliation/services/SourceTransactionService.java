package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.repositories.SourceTransactionRepository;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import com.diegorbj.reconciliation.services.dto.SourceTransactionDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SourceTransactionService {

    @Autowired
    protected SourceTransactionRepository _repository;

    public List<SourceTransactionDTO> findAll() {
        List<SourceTransaction> list = _repository.findAll();
        List<SourceTransactionDTO> listDTO = new ArrayList<>();
        for (SourceTransaction obj : list) {
            listDTO.add(SourceTransactionDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public SourceTransactionDTO findById(Long id) {
        Optional<SourceTransaction> obj = _repository.findById(id);
        return SourceTransactionDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public SourceTransactionDTO insert(SourceTransactionDTO obj) {
        //TODO - Some validation
        if (true) {
            return SourceTransactionDTO.fromDomain(_repository.save(obj.toDomain()));
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
                return SourceTransactionDTO.fromDomain(_repository.save(currentState.toDomain()));
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
        for (InstallmentDTO i : from.getInstallments()) {
            to.getInstallments().add(i);
        }
    }

}
