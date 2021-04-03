package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    List<Merchant> findByNameIgnoreCase(String name);

}
