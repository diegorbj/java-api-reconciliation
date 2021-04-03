package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.FinancialService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialServiceRepository extends JpaRepository<FinancialService, Long> {

    List<FinancialService> findByNameIgnoreCase(String name);

}
