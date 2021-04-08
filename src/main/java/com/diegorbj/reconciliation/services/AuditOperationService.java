package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.AuditOperationDTO;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.AuditOperationFilterParamDTO;

import java.util.Collection;
import java.util.List;

public interface AuditOperationService {
    List<AuditOperationDTO> findAll();

    AuditOperationDTO findById(Long id);

    List<AuditOperationDTO> getWithFilter(AuditOperationFilterParamDTO params);

    List<AuditOperationDTO> getByCardTypes(Collection<Long> cardTypeIds);

    List<AuditOperationDTO> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo);

    AuditOperationDTO save(AuditOperationDTO obj);

    AuditOperationDTO insert(AuditOperationDTO obj);

    AuditOperationDTO update(Long id, AuditOperationDTO obj);

    void delete(Long id);
}
