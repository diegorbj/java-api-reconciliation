package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.services.dto.FinancialServiceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FinancialServiceMapper {

    FinancialServiceDTO toDto(FinancialService obj);

    FinancialService toEntity(FinancialServiceDTO obj);

    List<FinancialServiceDTO> toDto(List<FinancialService> list);

    List<FinancialService> toEntity(List<FinancialServiceDTO> list);

}
