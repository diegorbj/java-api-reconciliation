package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialInstitutionRepository extends JpaRepository<FinancialInstitution, Long> {

    List<FinancialInstitution> findByNameIgnoreCase(String name);

}
