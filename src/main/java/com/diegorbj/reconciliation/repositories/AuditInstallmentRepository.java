package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditInstallment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuditInstallmentRepository extends JpaRepository<AuditInstallment, Long> {

    public List<AuditInstallment> findAllByOperation_Id(Long id);

    @Query(value = "SELECT * FROM tb_audit_installment ai WHERE ai.audit_operation_id = ?1 and ai.quota = ?2", nativeQuery = true)
    Optional<AuditInstallment> getByAuditingOperationIdAndQuota(Long auditingOperationId, Integer quota);

}
