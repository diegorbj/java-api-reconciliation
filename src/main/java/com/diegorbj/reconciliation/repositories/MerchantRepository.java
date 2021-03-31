package com.diegorbj.reconciliation.repositories;

import com.diegorbj.reconciliation.domain.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
