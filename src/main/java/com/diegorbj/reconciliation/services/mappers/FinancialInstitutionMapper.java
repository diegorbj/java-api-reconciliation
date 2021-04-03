package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface FinancialInstitutionMapper {

    FinancialInstitutionDTO toDto(FinancialInstitution obj);

    FinancialInstitution toEntity(FinancialInstitutionDTO obj);

    List<FinancialInstitutionDTO> toDto(List<FinancialInstitution> list);

    List<FinancialInstitution> toEntity(List<FinancialInstitutionDTO> list);

}
