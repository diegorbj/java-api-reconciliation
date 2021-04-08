package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.repositories.InstallmentRepository;
import com.diegorbj.reconciliation.services.InstallmentService;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.InstallmentMapper;
import com.diegorbj.reconciliation.services.mappers.InstallmentMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallmentServiceImpl implements InstallmentService {

    @Autowired
    protected InstallmentRepository _repository;

    @Autowired
    private InstallmentMapper _mapper;

    @Override
    public List<InstallmentDTO> findAll() {
        List<InstallmentDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public InstallmentDTO findById(Long id) {
        Optional<Installment> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<InstallmentDTO> findAllInstallments(Long id) {
        List<Installment> list = _repository.findAllBySourceTransaction_Id(id);
        return _mapper.toDto(list);
    }

    @Override
    public InstallmentDTO getBySourceTransactionIdAndQuota(Long id, Integer quota) {
        Optional<Installment> obj = _repository.getBySourceTransactionIdAndQuota(id, quota);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString() + "; Quota: " + quota.toString())));
    }

    @Override
    public InstallmentDTO save(InstallmentDTO obj) {
        try {
            InstallmentDTO currentState = this.getBySourceTransactionIdAndQuota(obj.getSourceTransaction().getId(), obj.getQuota());
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public InstallmentDTO insert(InstallmentDTO obj) {
        try {
            this.getBySourceTransactionIdAndQuota(obj.getSourceTransaction().getId(), obj.getQuota());
            throw new ResourceAlreadyExistsException("Id: " + obj.getSourceTransaction().getId().toString() + "; Quota: " + obj.getQuota().toString());
        } catch (ResourceNotFondException e) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        }
    }

    @Override
    public InstallmentDTO update(InstallmentDTO obj) {
        InstallmentDTO currentState = this.getBySourceTransactionIdAndQuota(obj.getSourceTransaction().getId(), obj.getQuota());
        updateData(obj, currentState);
        return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
    }

    @Override
    public void delete(Long id, Integer quota) {
        InstallmentDTO obj = this.getBySourceTransactionIdAndQuota(id, quota);
        _repository.deleteById(obj.getId());
    }

    private void updateData(InstallmentDTO from, InstallmentDTO to) {
        to.setGrossAmount(from.getGrossAmount());
    }

}
