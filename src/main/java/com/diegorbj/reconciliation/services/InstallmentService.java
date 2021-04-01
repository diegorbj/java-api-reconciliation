package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.repositories.InstallmentRepository;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
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
        for (Installment obj : list){
            listDTO.add(InstallmentDTO.fromDomain(obj));
        }
        return listDTO;
    }

    public InstallmentDTO findById(Long id) {
        Optional<Installment> obj = _repository.findById(id);
        return InstallmentDTO.fromDomain(obj.orElseThrow(() -> new ResourceNotFondException(id)));
    }

    public InstallmentDTO insert(InstallmentDTO obj) {
        return InstallmentDTO.fromDomain(_repository.save(obj.toDomain()));
    }

    public InstallmentDTO update(Long id, InstallmentDTO obj) {
        InstallmentDTO currentState = this.findById(id);
        updateData(obj, currentState);
        return InstallmentDTO.fromDomain(_repository.save(currentState.toDomain()));
    }

    public void delete(Long id) {
        Optional<Installment> obj = _repository.findById(id);
        obj.orElseThrow(() -> new ResourceNotFondException(id));
        _repository.deleteById(id);
    }

    private void updateData(InstallmentDTO from, InstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
    }

}
