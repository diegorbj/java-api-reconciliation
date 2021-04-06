package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.InstallmentDTO;

import java.util.List;

public interface InstallmentService {
    List<InstallmentDTO> findAll();

    InstallmentDTO findById(Long id);

    List<InstallmentDTO> findAllInstallments(Long id);

    InstallmentDTO getBySourceTransactionIdAndQuota(Long id, Integer quota);

    InstallmentDTO save(InstallmentDTO obj);

    InstallmentDTO insert(InstallmentDTO obj);

    InstallmentDTO update(InstallmentDTO obj);

    void delete(Long id, Integer quota);
}
