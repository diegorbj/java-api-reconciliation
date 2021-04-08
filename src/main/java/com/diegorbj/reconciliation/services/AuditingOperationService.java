package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.AuditingOperationDTO;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.AuditingOperationFilterParamDTO;

import java.util.Collection;
import java.util.List;

public interface AuditingOperationService {
    List<AuditingOperationDTO> findAll();

    AuditingOperationDTO findById(Long id);

    List<AuditingOperationDTO> getWithFilter(AuditingOperationFilterParamDTO params);

    List<AuditingOperationDTO> getByCardTypes(Collection<Long> cardTypeIds);

    List<AuditingOperationDTO> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo);

    AuditingOperationDTO save(AuditingOperationDTO obj);

    AuditingOperationDTO insert(AuditingOperationDTO obj);

    AuditingOperationDTO update(Long id, AuditingOperationDTO obj);

    void delete(Long id);
}
