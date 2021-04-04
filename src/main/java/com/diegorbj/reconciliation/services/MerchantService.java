package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.MerchantDTO;

import java.util.List;

public interface MerchantService {
    List<MerchantDTO> findAll();

    MerchantDTO findById(Long id);

    List<MerchantDTO> findByNameIgnoreCase(String name);

    MerchantDTO insert(MerchantDTO obj);

    MerchantDTO update(Long id, MerchantDTO obj);

    void delete(Long id);
}
