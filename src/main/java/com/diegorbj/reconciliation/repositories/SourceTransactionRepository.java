package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceTransactionRepository extends JpaRepository<SourceTransaction, Long> {

}
