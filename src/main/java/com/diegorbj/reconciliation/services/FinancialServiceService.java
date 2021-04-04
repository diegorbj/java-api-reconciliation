package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.FinancialServiceDTO;

import java.util.List;

public interface FinancialServiceService {
    List<FinancialServiceDTO> findAll();

    FinancialServiceDTO findById(Long id);

    List<FinancialServiceDTO> findByNameIgnoreCase(String name);

    FinancialServiceDTO insert(FinancialServiceDTO obj);

    FinancialServiceDTO update(Long id, FinancialServiceDTO obj);

    void delete(Long id);
}
