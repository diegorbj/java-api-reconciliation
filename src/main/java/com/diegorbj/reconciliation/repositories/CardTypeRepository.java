package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardTypeRepository extends JpaRepository<CardType, Long> {

    List<CardType> findByNameIgnoreCase(String name);

}
