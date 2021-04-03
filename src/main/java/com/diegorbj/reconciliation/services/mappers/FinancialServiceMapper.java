package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.services.dto.FinancialServiceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinancialServiceMapper {

    FinancialServiceDTO toDto(FinancialService entity);

    FinancialService toEntity(FinancialServiceDTO dto);

    List<FinancialServiceDTO> toDto(List<FinancialService> entityList);

}
