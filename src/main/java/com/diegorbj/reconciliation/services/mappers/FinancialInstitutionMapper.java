package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface FinancialInstitutionMapper {

    FinancialInstitutionDTO toDto(FinancialInstitution entity);

    FinancialInstitution toEntity(FinancialInstitutionDTO dto);

    List<FinancialInstitutionDTO> toDto(List<FinancialInstitution> entityList);

}
