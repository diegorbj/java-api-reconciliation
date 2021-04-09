package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.ConfirmInstallment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConfirmInstallmentRepository extends JpaRepository<ConfirmInstallment, Long> {

    public List<ConfirmInstallment> findAllByOperation_Id(Long id);

    @Query(value = "SELECT * FROM tb_confirm_installment ci WHERE ci.confirm_operation_id = ?1 and ci.quota = ?2", nativeQuery = true)
    Optional<ConfirmInstallment> getByOperationIdAndQuota(Long operationId, Integer quota);

}
