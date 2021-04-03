package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.Modality;
import com.diegorbj.reconciliation.services.dto.ModalityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModalityMapper {

    ModalityDTO toDto(Modality entity);

    Modality toEntity(ModalityDTO dto);

    List<ModalityDTO> toDto(List<Modality> entityList);

}
