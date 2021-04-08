package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditingInstallment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuditingInstallmentRepository extends JpaRepository<AuditingInstallment, Long> {

    public List<AuditingInstallment> findAllByOperation_Id(Long id);

    @Query(value = "SELECT * FROM tb_auditing_installment ai WHERE ai.auditing_operation_id = ?1 and ai.quota = ?2", nativeQuery = true)
    Optional<AuditingInstallment> getByAuditingOperationIdAndQuota(Long auditingOperationId, Integer quota);

}
