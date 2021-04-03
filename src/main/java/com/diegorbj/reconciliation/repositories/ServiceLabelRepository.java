package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceLabelRepository extends JpaRepository<ServiceLabel, Long> {

    List<ServiceLabel> findByNameIgnoreCase(String name);

}
