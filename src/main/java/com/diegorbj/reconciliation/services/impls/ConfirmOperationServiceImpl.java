package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.ConfirmOperation;
import com.diegorbj.reconciliation.repositories.ConfirmOperationRepository;
import com.diegorbj.reconciliation.repositories.criterias.params.OperationFilterParamFactory;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.OperationFilterParamDTO;
import com.diegorbj.reconciliation.services.ConfirmOperationService;
import com.diegorbj.reconciliation.services.dto.ConfirmInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.ConfirmOperationDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.ConfirmOperationMapper;
import com.diegorbj.reconciliation.services.mappers.OperationFilterParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfirmOperationServiceImpl implements ConfirmOperationService {

    @Autowired
    protected ConfirmOperationRepository _repository;

    @Autowired
    private ConfirmOperationMapper _mapper;

    @Autowired
    private OperationFilterParamMapper _mapperParams;

    @Override
    public List<ConfirmOperationDTO> findAll() {
        List<ConfirmOperationDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public ConfirmOperationDTO findById(Long id) {
        Optional<ConfirmOperation> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<ConfirmOperationDTO> getWithFilter(OperationFilterParamDTO params) {
        return _mapper.toDto(_repository.getWithFilter(_mapperParams.toEntity(params)));
    }

    @Override
    public ConfirmOperationDTO save(ConfirmOperationDTO obj) {
        OperationFilterParam key = OperationFilterParamFactory.create(_mapper.toEntity(obj));
        List<ConfirmOperation> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            ConfirmOperation st = _repository.save(_mapper.toEntity(obj));
            return _mapper.toDto(st);
        } else {
            if ((long) list.size() == 1L) {
                ConfirmOperationDTO currentState = _mapper.toDto(list.get(0));
                updateData(obj, currentState);
                return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
            } else {
                throw new InvalidAttributeException("Unexpected error.");
            }
        }
    }

    @Override
    public ConfirmOperationDTO insert(ConfirmOperationDTO obj) {
        OperationFilterParam key = OperationFilterParamFactory.create(_mapper.toEntity(obj));
        List<ConfirmOperation> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new ResourceAlreadyExistsException("");
        }
    }

    @Override
    public ConfirmOperationDTO update(Long id, ConfirmOperationDTO obj) {
        if (obj.getId().equals(id)) {
            ConfirmOperationDTO currentState = this.findById(id);
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } else {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<ConfirmOperation> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    protected void updateData(ConfirmOperationDTO from, ConfirmOperationDTO to) {
        to.setDate(from.getDate());
        to.setAuthorizationId(from.getAuthorizationId());
        to.setPointOfSaleId(from.getPointOfSaleId());
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
            for (ConfirmInstallmentDTO i : from.getInstallments()) {
                to.getInstallments().add(i);
            }
        }
    }

}
