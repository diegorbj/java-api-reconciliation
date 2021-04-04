package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;

import java.util.List;

public interface FinancialInstitutionService {
    List<FinancialInstitutionDTO> findAll();

    FinancialInstitutionDTO findById(Long id);

    List<FinancialInstitutionDTO> findByNameIgnoreCase(String name);

    FinancialInstitutionDTO insert(FinancialInstitutionDTO obj);

    FinancialInstitutionDTO update(Long id, FinancialInstitutionDTO obj);

    void delete(Long id);
}
