package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.ConfirmInstallmentDTO;

import java.util.List;

public interface ConfirmInstallmentService {
    List<ConfirmInstallmentDTO> findAll();

    ConfirmInstallmentDTO findById(Long id);

    List<ConfirmInstallmentDTO> findAllInstallments(Long id);

    ConfirmInstallmentDTO getByOperationIdAndQuota(Long id, Integer quota);

    ConfirmInstallmentDTO save(ConfirmInstallmentDTO obj);

    ConfirmInstallmentDTO insert(ConfirmInstallmentDTO obj);

    ConfirmInstallmentDTO update(ConfirmInstallmentDTO obj);

    void delete(Long id, Integer quota);
}
