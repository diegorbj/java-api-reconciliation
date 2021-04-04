package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.CardTypeDTO;

import java.util.List;

public interface CardTypeService {
    List<CardTypeDTO> findAll();

    CardTypeDTO findById(Long id);

    List<CardTypeDTO> findByNameIgnoreCase(String name);

    CardTypeDTO insert(CardTypeDTO obj);

    CardTypeDTO update(Long id, CardTypeDTO obj);

    void delete(Long id);
}
