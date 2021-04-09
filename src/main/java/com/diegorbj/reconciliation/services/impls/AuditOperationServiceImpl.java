package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.AuditOperationRepository;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.OperationFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.OperationFilterParamFactory;
import com.diegorbj.reconciliation.services.AuditOperationService;
import com.diegorbj.reconciliation.services.dto.AuditInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.AuditOperationDTO;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.OperationFilterParamDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.AuditOperationFilterParamMapper;
import com.diegorbj.reconciliation.services.mappers.AuditOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuditOperationServiceImpl implements AuditOperationService {

    @Autowired
    protected AuditOperationRepository _repository;

    @Autowired
    private AuditOperationMapper _mapper;

    @Autowired
    private AuditOperationFilterParamMapper _mapperParams;

    @Override
    public List<AuditOperationDTO> findAll() {
        List<AuditOperationDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public AuditOperationDTO findById(Long id) {
        Optional<AuditOperation> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<AuditOperationDTO> getWithFilter(OperationFilterParamDTO params) {
        return _mapper.toDto(_repository.getWithFilter(_mapperParams.toEntity(params)));
    }

    @Override
    public List<AuditOperationDTO> getByCardTypes(Collection<Long> cardTypeIds) {
        return _mapper.toDto(_repository.getByCardTypes(cardTypeIds));
    }

    @Override
    public List<AuditOperationDTO> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo) {
        return _mapper.toDto(_repository.getByGrossAmountRange(grossAmountFrom, grossAmountTo));
    }

    @Override
    public AuditOperationDTO save(AuditOperationDTO obj) {
        OperationFilterParam key = OperationFilterParamFactory.create(_mapper.toEntity(obj));
        List<AuditOperation> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            AuditOperation st = _repository.save(_mapper.toEntity(obj));
            return _mapper.toDto(st);
        } else {
            if ((long) list.size() == 1L) {
                AuditOperationDTO currentState = _mapper.toDto(list.get(0));
                updateData(obj, currentState);
                return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
            } else {
                throw new InvalidAttributeException("Unexpected error.");
            }
        }
    }

    @Override
    public AuditOperationDTO insert(AuditOperationDTO obj) {
        OperationFilterParam key = OperationFilterParamFactory.create(_mapper.toEntity(obj));
        List<AuditOperation> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new ResourceAlreadyExistsException("");
        }
    }

    @Override
    public AuditOperationDTO update(Long id, AuditOperationDTO obj) {
        if (obj.getId().equals(id)) {
            AuditOperationDTO currentState = this.findById(id);
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } else {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<AuditOperation> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    protected void updateData(AuditOperationDTO from, AuditOperationDTO to) {
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
            for (AuditInstallmentDTO i : from.getInstallments()) {
                to.getInstallments().add(i);
            }
        }
    }

}
