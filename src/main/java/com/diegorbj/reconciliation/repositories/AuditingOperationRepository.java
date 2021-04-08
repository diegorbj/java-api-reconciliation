package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.AuditingOperationFilterParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AuditingOperationRepository extends JpaRepository<AuditingOperation, Long>, AuditingOperationCustomRepository {

    List<AuditingOperation> getWithFilter(AuditingOperationFilterParam params);

    @Query(value = "SELECT * FROM tb_auditing_operation ao WHERE ao.card_type_id in :card_type_ids", nativeQuery = true)
    List<AuditingOperation> getByCardTypes(@Param("card_type_ids") Collection<Long> cardTypeIds);

    @Query(value = "SELECT * FROM tb_auditing_operation ao WHERE ao.gross_amount >= ?1 and st.gross_amount <= ?2", nativeQuery = true)
    List<AuditingOperation> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo);

}
