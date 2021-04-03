package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.Modality;

import com.diegorbj.reconciliation.services.dto.ModalityDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModalityMapper {

    ModalityDTO toDto(Modality obj);

    Modality toEntity(ModalityDTO obj);

    List<ModalityDTO> toDto(List<Modality> list);

    List<Modality> toEntity(List<ModalityDTO> list);

}
