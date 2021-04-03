package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.services.dto.ServiceLabelDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceLabelMapper {

    ServiceLabelDTO toDto(ServiceLabel entity);

    ServiceLabel toEntity(ServiceLabelDTO dto);

    List<ServiceLabelDTO> toDto(List<ServiceLabel> entityList);

}
