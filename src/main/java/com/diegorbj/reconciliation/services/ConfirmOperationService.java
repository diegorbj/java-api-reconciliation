package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.repositories.criterias.params.dto.OperationFilterParamDTO;
import com.diegorbj.reconciliation.services.dto.ConfirmOperationDTO;

import java.util.List;

public interface ConfirmOperationService {
    List<ConfirmOperationDTO> findAll();

    ConfirmOperationDTO findById(Long id);

    List<ConfirmOperationDTO> getWithFilter(OperationFilterParamDTO params);

    ConfirmOperationDTO save(ConfirmOperationDTO obj);

    ConfirmOperationDTO insert(ConfirmOperationDTO obj);

    ConfirmOperationDTO update(Long id, ConfirmOperationDTO obj);

    void delete(Long id);
}
