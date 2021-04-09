package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.AuditInstallment;
import com.diegorbj.reconciliation.domain.AuditOperation;
import com.diegorbj.reconciliation.services.dto.AuditInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.AuditOperationDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class AuditInstallmentMapper {

    public AuditInstallmentDTO toDto(AuditInstallment entity) {
        AuditInstallmentDTO dto = new AuditInstallmentDTO();
        dto.setId(entity.getId());
        dto.setQuota(entity.getQuota());
        dto.setGrossAmount(entity.getGrossAmount());
        if (entity.getOperation() != null) {
            AuditOperationDTO stDTO = new AuditOperationDTO();
            stDTO.setId(entity.getOperation().getId());
            dto.setOperation(stDTO);
        }
        return dto;
    }

    public AuditInstallment toEntity(AuditInstallmentDTO dto) {
        AuditInstallment entity = new AuditInstallment();
        entity.setId(dto.getId());
        entity.setQuota(dto.getQuota());
        entity.setGrossAmount(dto.getGrossAmount());
        if (dto.getOperation() != null) {
            AuditOperation st = new AuditOperation();
            st.setId(dto.getOperation().getId());
            entity.setOperation(st);
        }
        return entity;
    }

    public List<AuditInstallmentDTO> toDto(List<AuditInstallment> entityList) {
        List<AuditInstallmentDTO> listDTO = new ArrayList<>();
        for (AuditInstallment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<AuditInstallmentDTO> toDto(Set<AuditInstallment> entityList) {
        Set<AuditInstallmentDTO> listDTO = new HashSet<>();
        for (AuditInstallment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<AuditInstallment> toEntity(Set<AuditInstallmentDTO> dtoList) {
        Set<AuditInstallment> listEntity = new HashSet<>();
        for (AuditInstallmentDTO dto : dtoList) {
            listEntity.add(this.toEntity(dto));
        }
        return listEntity;
    }

}
