package com.diegorbj.reconciliation.services.impls;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.repositories.SourceTransactionRepository;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParamFactory;
import com.diegorbj.reconciliation.services.SourceTransactionService;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import com.diegorbj.reconciliation.services.dto.SourceTransactionDTO;
import com.diegorbj.reconciliation.services.exceptions.InvalidAttributeException;
import com.diegorbj.reconciliation.services.exceptions.ResourceAlreadyExistsException;
import com.diegorbj.reconciliation.services.exceptions.ResourceNotFondException;
import com.diegorbj.reconciliation.services.mappers.SourceTransactionMapper;
import com.diegorbj.reconciliation.services.mappers.SourceTransactionMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SourceTransactionServiceImpl implements SourceTransactionService {

    @Autowired
    protected SourceTransactionRepository _repository;

    private SourceTransactionMapper _mapper = new SourceTransactionMapperImpl();

    @Override
    public List<SourceTransactionDTO> findAll() {
        List<SourceTransactionDTO> dtoList = _mapper.toDto(_repository.findAll());
        if (dtoList.isEmpty()) {
            throw new ResourceNotFondException("{All}");
        }
        return dtoList;
    }

    @Override
    public SourceTransactionDTO findById(Long id) {
        Optional<SourceTransaction> obj = _repository.findById(id);
        return _mapper.toDto(obj.orElseThrow(() -> new ResourceNotFondException("Id: " + id.toString())));
    }

    @Override
    public List<SourceTransactionDTO> getWithFilter(SourceTransactionFilterParam params) {
        return _mapper.toDto(_repository.getWithFilter(params));
    }

    @Override
    public List<SourceTransactionDTO> getByCardTypes(Collection<Long> cardTypeIds) {
        return _mapper.toDto(_repository.getByCardTypes(cardTypeIds));
    }

    @Override
    public List<SourceTransactionDTO> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo) {
        return _mapper.toDto(_repository.getByGrossAmountRange(grossAmountFrom, grossAmountTo));
    }

    @Override
    public SourceTransactionDTO save(SourceTransactionDTO obj) {
        SourceTransactionFilterParam key = SourceTransactionFilterParamFactory.create(_mapper.toEntity(obj));
        List<SourceTransaction> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            SourceTransaction st = _repository.save(_mapper.toEntity(obj));
            return _mapper.toDto(st);
        } else {
            if ((long) list.size() == 1L) {
                SourceTransactionDTO currentState = _mapper.toDto(list.get(0));
                updateData(obj, currentState);
                return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
            } else {
                throw new InvalidAttributeException("Unexpected error.");
            }
        }
    }

    @Override
    public SourceTransactionDTO insert(SourceTransactionDTO obj) {
        SourceTransactionFilterParam key = SourceTransactionFilterParamFactory.create(_mapper.toEntity(obj));
        List<SourceTransaction> list = _repository.getWithFilter(key);
        if (list.isEmpty()) {
            return _mapper.toDto(_repository.save(_mapper.toEntity(obj)));
        } else {
            throw new ResourceAlreadyExistsException("");
        }
    }

    @Override
    public SourceTransactionDTO update(Long id, SourceTransactionDTO obj) {
        if (obj.getId().equals(id)) {
            SourceTransactionDTO currentState = this.findById(id);
            updateData(obj, currentState);
            return _mapper.toDto(_repository.save(_mapper.toEntity(currentState)));
        } else {
            throw new InvalidAttributeException("Inconsistent value for Id");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<SourceTransaction> obj = _repository.findById(id);
        _repository.deleteById(id);
    }

    protected void updateData(SourceTransactionDTO from, SourceTransactionDTO to) {
        to.setDate(from.getDate());
        to.setUniqueSequentialNumber(from.getUniqueSequentialNumber());
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
            for (InstallmentDTO i : from.getInstallments()) {
                to.getInstallments().add(i);
            }
        }
    }

}
