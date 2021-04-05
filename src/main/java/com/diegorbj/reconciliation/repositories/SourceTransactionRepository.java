package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface SourceTransactionRepository extends JpaRepository<SourceTransaction, Long>, SourceTransactionCustomRepository {

    List<SourceTransaction> getWithFilter(SourceTransactionFilterParam params);

    @Query(value = "SELECT * FROM tb_source_transaction st WHERE st.card_type_id in :card_type_ids", nativeQuery = true)
    List<SourceTransaction> getByCardTypes(@Param("card_type_ids") Collection<Long> cardTypeIds);

    @Query(value = "SELECT * FROM tb_source_transaction st WHERE st.gross_amount >= ?1 and st.gross_amount <= ?2", nativeQuery = true)
    List<SourceTransaction> getByGrossAmountRange(Double grossAmountFrom, Double grossAmountTo);

}
