package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParam;
import com.diegorbj.reconciliation.services.dto.SourceTransactionDTO;

import java.util.List;

public interface SourceTransactionService {
    List<SourceTransactionDTO> findAll();

    SourceTransactionDTO findById(Long id);

    List<SourceTransactionDTO> getWithFilter(SourceTransactionFilterParam params);

    SourceTransactionDTO insert(SourceTransactionDTO obj);

    SourceTransactionDTO update(Long id, SourceTransactionDTO obj);

    void delete(Long id);
}
