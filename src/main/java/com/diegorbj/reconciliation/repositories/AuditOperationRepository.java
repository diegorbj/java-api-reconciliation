package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationFilterParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface AuditOperationRepository extends JpaRepository<AuditOperation, Long>, AuditOperationCustomRepository {

    List<AuditOperation> getWithFilter(AuditOperationFilterParam params);

    @Query(value = "SELECT * FROM tb_audit_operation ao WHERE ao.card_type_id in :card_type_ids", nativeQuery = true)
    List<AuditOperation> getByCardTypes(@Param("card_type_ids") Collection<Long> cardTypeIds);

    @Query(value = "SELECT * FROM tb_audit_operation ao WHERE ao.gross_amount >= ?1 and st.gross_amount <= ?2", nativeQuery = true)
    List<AuditOperation> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo);

}
