package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.ConfirmInstallment;
import com.diegorbj.reconciliation.repositories.ConfirmInstallmentRepository;
import com.diegorbj.reconciliation.services.ConfirmInstallmentService;
import com.diegorbj.reconciliation.services.dto.ConfirmInstallmentDTO;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.ConfirmInstallmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfirmInstallmentServiceImpl implements ConfirmInstallmentService {

    @Autowired
    protected ConfirmInstallmentRepository _repository;

    @Autowired
    private ConfirmInstallmentMapper _mapper;

    @Override
    public List<ConfirmInstallmentDTO> findAll() {
        List<ConfirmInstallmentDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public ConfirmInstallmentDTO findById(Long id) {
        Optional<ConfirmInstallment> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<ConfirmInstallmentDTO> findAllInstallments(Long id) {
        List<ConfirmInstallment> list = _repository.findAllByOperation_Id(id);
        return _mapper.toDto(list);
    }

    @Override
    public ConfirmInstallmentDTO getByOperationIdAndQuota(Long id, Integer quota) {
        Optional<ConfirmInstallment> obj = _repository.getByOperationIdAndQuota(id, quota);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString() + "; Quota: " + quota.toString())));
    }

    @Override
    public ConfirmInstallmentDTO save(ConfirmInstallmentDTO obj) {
        try {
            ConfirmInstallmentDTO currentState = this.getByOperationIdAndQuota(obj.getOperation().getId(), obj.getQuota());
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public ConfirmInstallmentDTO insert(ConfirmInstallmentDTO obj) {
        try {
            this.getByOperationIdAndQuota(obj.getOperation().getId(), obj.getQuota());
            throw new ResourceAlreadyExistsException("Id: " + obj.getOperation().getId().toString() + "; Quota: " + obj.getQuota().toString());
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public ConfirmInstallmentDTO update(ConfirmInstallmentDTO obj) {
        ConfirmInstallmentDTO currentState = this.getByOperationIdAndQuota(obj.getOperation().getId(), obj.getQuota());
        updateData(obj, currentState);
        return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
    }

    @Override
    public void delete(Long id, Integer quota) {
        ConfirmInstallmentDTO obj = this.getByOperationIdAndQuota(id, quota);
        _repository.deleteById(obj.getId());
    }

    private void updateData(ConfirmInstallmentDTO from, ConfirmInstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
    }

}
