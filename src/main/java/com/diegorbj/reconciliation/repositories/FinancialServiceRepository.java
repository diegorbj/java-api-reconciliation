package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.FinancialService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialServiceRepository extends JpaRepository<FinancialService, Long> {

}
