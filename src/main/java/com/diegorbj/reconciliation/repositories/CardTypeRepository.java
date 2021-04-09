package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CardTypeRepository extends JpaRepository<CardType, Long> {

    List<CardType> findByNameIgnoreCase(String name);

    @Query(value = "SELECT * FROM tb_card_type ct WHERE ct.card_type_id in :card_type_ids", nativeQuery = true)
    List<CardType> getByCardTypes(@Param("card_type_ids") Collection<Long> cardTypeIds);

}
