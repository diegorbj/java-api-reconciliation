package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.repositories.InstallmentRepository;
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
public class InstallmentService {

    @Autowired
    protected InstallmentRepository _repository;

    public List<InstallmentDTO> findAll() {
        List<Installment> list = _repository.findAll();
        List<InstallmentDTO> listDTO = new ArrayList<>();
        for (Installment obj : list) {
            listDTO.add(InstallmentDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public InstallmentDTO findById(Long id) {
        Optional<Installment> obj = _repository.findById(id);
        return InstallmentDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public List<InstallmentDTO> findAllInstallments(Long id) {
        List<Installment> list = _repository.findAllBySourceTransaction_Id(id);
        List<InstallmentDTO> listDTO = new ArrayList<>();
        for (Installment obj : list) {
            listDTO.add(InstallmentDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public InstallmentDTO findByQuota(Long id, Integer quota) {
        List<InstallmentDTO> listDTO = this.findAllInstallments(id);
        for (InstallmentDTO obj : listDTO) {
            if (obj.getQuota().equals(quota)) {
                return obj;
            }
        }
        throw new ResourceNotFondException(id + '/' + quota);
    }

    public InstallmentDTO insert(InstallmentDTO obj) {
        //TODO - Some validation
        if (true) {
            return InstallmentDTO.fromDomain(_repository.save(obj.toDomain()));
        } else {
            throw new InvalidAttributeException("Some validation failed");
        }
    }

    public InstallmentDTO update(InstallmentDTO obj) {
        //TODO - Some validation
        if (true) {
            InstallmentDTO currentState = this.findByQuota(obj.getSourceTransaction().getId(), obj.getQuota());
            updateData(obj, currentState);
            return InstallmentDTO.fromDomain(_repository.save(currentState.toDomain()));
        } else {
            throw new InvalidAttributeException("Some validation failed");
        }
    }

    public void delete(Long id, Integer quota) {
        InstallmentDTO obj = this.findByQuota(id, quota);
        if (obj == null) {
            throw new ResourceNotFondException(id);
        }
        _repository.deleteById(obj.getId());
    }

    private void updateData(InstallmentDTO from, InstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
        //to.setSourceTransaction(from.getSourceTransaction());
    }

}
