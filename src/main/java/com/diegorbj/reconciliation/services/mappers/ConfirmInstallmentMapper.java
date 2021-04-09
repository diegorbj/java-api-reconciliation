package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.ConfirmInstallment;
import com.diegorbj.reconciliation.domain.ConfirmOperation;
import com.diegorbj.reconciliation.services.dto.ConfirmInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.ConfirmOperationDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ConfirmInstallmentMapper {

    public ConfirmInstallmentDTO toDto(ConfirmInstallment entity) {
        ConfirmInstallmentDTO dto = new ConfirmInstallmentDTO();
        dto.setId(entity.getId());
        dto.setQuota(entity.getQuota());
        dto.setGrossAmount(entity.getGrossAmount());
        if (entity.getOperation() != null) {
            ConfirmOperationDTO stDTO = new ConfirmOperationDTO();
            stDTO.setId(entity.getOperation().getId());
            dto.setOperation(stDTO);
        }
        return dto;
    }

    public ConfirmInstallment toEntity(ConfirmInstallmentDTO dto) {
        ConfirmInstallment entity = new ConfirmInstallment();
        entity.setId(dto.getId());
        entity.setQuota(dto.getQuota());
        entity.setGrossAmount(dto.getGrossAmount());
        if (dto.getOperation() != null) {
            ConfirmOperation st = new ConfirmOperation();
            st.setId(dto.getOperation().getId());
            entity.setOperation(st);
        }
        return entity;
    }

    public List<ConfirmInstallmentDTO> toDto(List<ConfirmInstallment> entityList) {
        List<ConfirmInstallmentDTO> listDTO = new ArrayList<>();
        for (ConfirmInstallment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<ConfirmInstallmentDTO> toDto(Set<ConfirmInstallment> entityList) {
        Set<ConfirmInstallmentDTO> listDTO = new HashSet<>();
        for (ConfirmInstallment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<ConfirmInstallment> toEntity(Set<ConfirmInstallmentDTO> dtoList) {
        Set<ConfirmInstallment> listEntity = new HashSet<>();
        for (ConfirmInstallmentDTO dto : dtoList) {
            listEntity.add(this.toEntity(dto));
        }
        return listEntity;
    }

}
