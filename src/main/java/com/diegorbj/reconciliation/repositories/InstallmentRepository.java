package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

}
