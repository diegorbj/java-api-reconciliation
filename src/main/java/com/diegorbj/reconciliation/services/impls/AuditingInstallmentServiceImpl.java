package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.AuditingInstallment;
import com.diegorbj.reconciliation.repositories.AuditingInstallmentRepository;
import com.diegorbj.reconciliation.services.AuditingInstallmentService;
import com.diegorbj.reconciliation.services.dto.AuditingInstallmentDTO;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.AuditingInstallmentMapper;
import com.diegorbj.reconciliation.services.mappers.AuditingInstallmentMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditingInstallmentServiceImpl implements AuditingInstallmentService {

    @Autowired
    protected AuditingInstallmentRepository _repository;

    @Autowired
    private AuditingInstallmentMapper _mapper;

    @Override
    public List<AuditingInstallmentDTO> findAll() {
        List<AuditingInstallmentDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public AuditingInstallmentDTO findById(Long id) {
        Optional<AuditingInstallment> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<AuditingInstallmentDTO> findAllInstallments(Long id) {
        List<AuditingInstallment> list = _repository.findAllByOperation_Id(id);
        return _mapper.toDto(list);
    }

    @Override
    public AuditingInstallmentDTO getByAuditingOperationIdAndQuota(Long id, Integer quota) {
        Optional<AuditingInstallment> obj = _repository.getByAuditingOperationIdAndQuota(id, quota);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString() + "; Quota: " + quota.toString())));
    }

    @Override
    public AuditingInstallmentDTO save(AuditingInstallmentDTO obj) {
        try {
            AuditingInstallmentDTO currentState = this.getByAuditingOperationIdAndQuota(obj.getAuditingOperation().getId(), obj.getQuota());
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public AuditingInstallmentDTO insert(AuditingInstallmentDTO obj) {
        try {
            this.getByAuditingOperationIdAndQuota(obj.getAuditingOperation().getId(), obj.getQuota());
            throw new ResourceAlreadyExistsException("Id: " + obj.getAuditingOperation().getId().toString() + "; Quota: " + obj.getQuota().toString());
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public AuditingInstallmentDTO update(AuditingInstallmentDTO obj) {
        AuditingInstallmentDTO currentState = this.getByAuditingOperationIdAndQuota(obj.getAuditingOperation().getId(), obj.getQuota());
        updateData(obj, currentState);
        return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
    }

    @Override
    public void delete(Long id, Integer quota) {
        AuditingInstallmentDTO obj = this.getByAuditingOperationIdAndQuota(id, quota);
        _repository.deleteById(obj.getId());
    }

    private void updateData(AuditingInstallmentDTO from, AuditingInstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
    }

}
