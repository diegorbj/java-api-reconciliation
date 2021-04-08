package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.repositories.AuditingOperationRepository;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditingOperationFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.AuditingOperationFilterParamFactory;
import com.diegorbj.reconciliation.services.AuditingOperationService;
import com.diegorbj.reconciliation.services.dto.AuditingInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.AuditingOperationDTO;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.AuditingOperationFilterParamDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.AuditingOperationFilterParamMapper;
import com.diegorbj.reconciliation.services.mappers.AuditingOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuditingOperationServiceImpl implements AuditingOperationService {

    @Autowired
    protected AuditingOperationRepository _repository;

    @Autowired
    private AuditingOperationMapper _mapper;

    @Autowired
    private AuditingOperationFilterParamMapper _mapperParams;

    @Override
    public List<AuditingOperationDTO> findAll() {
        List<AuditingOperationDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public AuditingOperationDTO findById(Long id) {
        Optional<AuditingOperation> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<AuditingOperationDTO> getWithFilter(AuditingOperationFilterParamDTO params) {
        return _mapper.toDto(_repository.getWithFilter(_mapperParams.toEntity(params)));
    }

    @Override
    public List<AuditingOperationDTO> getByCardTypes(Collection<Long> cardTypeIds) {
        return _mapper.toDto(_repository.getByCardTypes(cardTypeIds));
    }

    @Override
    public List<AuditingOperationDTO> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo) {
        return _mapper.toDto(_repository.getByGrossAmountRange(grossAmountFrom, grossAmountTo));
    }

    @Override
    public AuditingOperationDTO save(AuditingOperationDTO obj) {
        AuditingOperationFilterParam key = AuditingOperationFilterParamFactory.create(_mapper.toEntity(obj));
        List<AuditingOperation> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            AuditingOperation st = _repository.save(_mapper.toEntity(obj));
            return _mapper.toDto(st);
        } else {
            if ((long) list.size() == 1L) {
                AuditingOperationDTO currentState = _mapper.toDto(list.get(0));
                updateData(obj, currentState);
                return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
            } else {
                throw new InvalidAttributeException("Unexpected error.");
            }
        }
    }

    @Override
    public AuditingOperationDTO insert(AuditingOperationDTO obj) {
        AuditingOperationFilterParam key = AuditingOperationFilterParamFactory.create(_mapper.toEntity(obj));
        List<AuditingOperation> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new ResourceAlreadyExistsException("");
        }
    }

    @Override
    public AuditingOperationDTO update(Long id, AuditingOperationDTO obj) {
        if (obj.getId().equals(id)) {
            AuditingOperationDTO currentState = this.findById(id);
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } else {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<AuditingOperation> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    protected void updateData(AuditingOperationDTO from, AuditingOperationDTO to) {
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
            for (AuditingInstallmentDTO i : from.getInstallments()) {
                to.getInstallments().add(i);
            }
        }
    }

}
