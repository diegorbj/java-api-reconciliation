package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    public List<Installment> findAllBySourceTransaction_Id(Long id);

}
