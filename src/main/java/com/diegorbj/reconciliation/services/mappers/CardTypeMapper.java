package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.dto.CardTypeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    CardTypeDTO toDto(CardType entity);

    CardType toEntity(CardTypeDTO dto);

    List<CardTypeDTO> toDto(List<CardType> entityList);

}
