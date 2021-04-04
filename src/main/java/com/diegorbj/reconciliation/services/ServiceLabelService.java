package com.diegorbj.reconciliation.services;

import com.diegorbj.reconciliation.services.dto.ServiceLabelDTO;

import java.util.List;

public interface ServiceLabelService {
    List<ServiceLabelDTO> findAll();

    ServiceLabelDTO findById(Long id);

    List<ServiceLabelDTO> findByNameIgnoreCase(String name);

    ServiceLabelDTO insert(ServiceLabelDTO obj);

    ServiceLabelDTO update(Long id, ServiceLabelDTO obj);

    void delete(Long id);
}
