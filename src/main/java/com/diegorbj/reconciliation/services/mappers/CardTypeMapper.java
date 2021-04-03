package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.dto.CardTypeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {

    CardTypeDTO toDto(CardType obj);

    CardType toEntity(CardTypeDTO obj);

    List<CardTypeDTO> toDto(List<CardType> list);

    List<CardType> toEntity(List<CardTypeDTO> list);

}
