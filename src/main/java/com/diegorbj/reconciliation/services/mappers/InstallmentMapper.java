package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstallmentMapper {

    InstallmentDTO toDto(Installment obj);

    Installment toEntity(InstallmentDTO obj);

    List<InstallmentDTO> toDto(List<Installment> list);

    List<Installment> toEntity(List<InstallmentDTO> list);

}
