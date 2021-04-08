package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.AuditingInstallmentDTO;

import java.util.List;

public interface AuditingInstallmentService {
    List<AuditingInstallmentDTO> findAll();

    AuditingInstallmentDTO findById(Long id);

    List<AuditingInstallmentDTO> findAllInstallments(Long id);

    AuditingInstallmentDTO getByAuditingOperationIdAndQuota(Long id, Integer quota);

    AuditingInstallmentDTO save(AuditingInstallmentDTO obj);

    AuditingInstallmentDTO insert(AuditingInstallmentDTO obj);

    AuditingInstallmentDTO update(AuditingInstallmentDTO obj);

    void delete(Long id, Integer quota);
}
