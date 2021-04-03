package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

    List<Modality> findByNameIgnoreCase(String name);

}
