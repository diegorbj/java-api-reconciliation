package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.repositories.InstallmentRepository;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.InstallmentMapper;
import com.diegorbj.reconciliation.services.mappers.InstallmentMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstallmentService {

    @Autowired
    protected InstallmentRepository _repository;

    private InstallmentMapper _mapper = new InstallmentMapperImpl();

    public List<InstallmentDTO> findAll() {
        List<InstallmentDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    public InstallmentDTO findById(Long id) {
        Optional<Installment> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    public List<InstallmentDTO> findAllInstallments(Long id) {
        List<Installment> list = _repository.findAllBySourceTransaction_Id(id);
        List<InstallmentDTO> listDTO = new ArrayList<>();
        for (Installment obj : list) {
            listDTO.add(_mapper.toDto(obj));
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
        throw new ResourceNotFondException("Id: " + id.toString() + "; Quota: " + quota.toString());
    }

    public InstallmentDTO insert(InstallmentDTO obj) {
        //TODO - Some validation
        if (true) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new InvalidAttributeException("Some validation failed");
        }
    }

    public InstallmentDTO update(InstallmentDTO obj) {
        //TODO - Some validation
        if (true) {
            InstallmentDTO currentState = this.findByQuota(obj.getSourceTransaction().getId(), obj.getQuota());
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } else {
            throw new InvalidAttributeException("Some validation failed");
        }
    }

    public void delete(Long id, Integer quota) {
        InstallmentDTO obj = this.findByQuota(id, quota);
        _repository.deleteById(obj.getId());
    }

    private void updateData(InstallmentDTO from, InstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
        //to.setSourceTransaction(from.getSourceTransaction());
    }

}
