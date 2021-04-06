package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    public List<Installment> findAllBySourceTransaction_Id(Long id);

    @Query(value = "SELECT * FROM tb_installment i WHERE i.source_transaction_id = ?1 and i.quota = ?2", nativeQuery = true)
    Optional<Installment> getBySourceTransactionIdAndQuota(Long sourceTransactionId, Integer quota);

}
