package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.AuditInstallment;
import com.diegorbj.reconciliation.repositories.AuditInstallmentRepository;
import com.diegorbj.reconciliation.services.AuditInstallmentService;
import com.diegorbj.reconciliation.services.dto.AuditInstallmentDTO;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.AuditInstallmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditInstallmentServiceImpl implements AuditInstallmentService {

    @Autowired
    protected AuditInstallmentRepository _repository;

    @Autowired
    private AuditInstallmentMapper _mapper;

    @Override
    public List<AuditInstallmentDTO> findAll() {
        List<AuditInstallmentDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public AuditInstallmentDTO findById(Long id) {
        Optional<AuditInstallment> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<AuditInstallmentDTO> findAllInstallments(Long id) {
        List<AuditInstallment> list = _repository.findAllByOperation_Id(id);
        return _mapper.toDto(list);
    }

    @Override
    public AuditInstallmentDTO getByAuditingOperationIdAndQuota(Long id, Integer quota) {
        Optional<AuditInstallment> obj = _repository.getByAuditingOperationIdAndQuota(id, quota);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString() + "; Quota: " + quota.toString())));
    }

    @Override
    public AuditInstallmentDTO save(AuditInstallmentDTO obj) {
        try {
            AuditInstallmentDTO currentState = this.getByAuditingOperationIdAndQuota(obj.getAuditingOperation().getId(), obj.getQuota());
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public AuditInstallmentDTO insert(AuditInstallmentDTO obj) {
        try {
            this.getByAuditingOperationIdAndQuota(obj.getAuditingOperation().getId(), obj.getQuota());
            throw new ResourceAlreadyExistsException("Id: " + obj.getAuditingOperation().getId().toString() + "; Quota: " + obj.getQuota().toString());
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public AuditInstallmentDTO update(AuditInstallmentDTO obj) {
        AuditInstallmentDTO currentState = this.getByAuditingOperationIdAndQuota(obj.getAuditingOperation().getId(), obj.getQuota());
        updateData(obj, currentState);
        return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
    }

    @Override
    public void delete(Long id, Integer quota) {
        AuditInstallmentDTO obj = this.getByAuditingOperationIdAndQuota(id, quota);
        _repository.deleteById(obj.getId());
    }

    private void updateData(AuditInstallmentDTO from, AuditInstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
    }

}
