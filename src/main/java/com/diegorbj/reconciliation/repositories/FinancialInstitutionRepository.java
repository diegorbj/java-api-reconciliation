package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialInstitutionRepository extends JpaRepository<FinancialInstitution, Long> {

}
