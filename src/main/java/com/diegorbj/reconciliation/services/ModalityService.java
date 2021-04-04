package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.ModalityDTO;

import java.util.List;

public interface ModalityService {
    List<ModalityDTO> findAll();

    ModalityDTO findById(Long id);

    List<ModalityDTO> findByNameIgnoreCase(String name);

    ModalityDTO insert(ModalityDTO obj);

    ModalityDTO update(Long id, ModalityDTO obj);

    void delete(Long id);
}
