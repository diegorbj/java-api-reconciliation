package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.AuditInstallmentDTO;

import java.util.List;

public interface AuditInstallmentService {
    List<AuditInstallmentDTO> findAll();

    AuditInstallmentDTO findById(Long id);

    List<AuditInstallmentDTO> findAllInstallments(Long id);

    AuditInstallmentDTO getByAuditingOperationIdAndQuota(Long id, Integer quota);

    AuditInstallmentDTO save(AuditInstallmentDTO obj);

    AuditInstallmentDTO insert(AuditInstallmentDTO obj);

    AuditInstallmentDTO update(AuditInstallmentDTO obj);

    void delete(Long id, Integer quota);
}
