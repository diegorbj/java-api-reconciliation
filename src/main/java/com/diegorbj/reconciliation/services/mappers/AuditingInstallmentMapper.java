package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.AuditingInstallment;
import com.diegorbj.reconciliation.domain.AuditingOperation;
import com.diegorbj.reconciliation.services.dto.AuditingInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.AuditingOperationDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class AuditingInstallmentMapper {

    public AuditingInstallmentDTO toDto(AuditingInstallment entity) {
        AuditingInstallmentDTO dto = new AuditingInstallmentDTO();
        dto.setId(entity.getId());
        dto.setQuota(entity.getQuota());
        dto.setGrossAmount(entity.getGrossAmount());
        if (entity.getOperation() != null) {
            AuditingOperationDTO stDTO = new AuditingOperationDTO();
            stDTO.setId(entity.getOperation().getId());
            dto.setAuditingOperation(stDTO);
        }
        return dto;
    }

    public AuditingInstallment toEntity(AuditingInstallmentDTO dto) {
        AuditingInstallment entity = new AuditingInstallment();
        entity.setId(dto.getId());
        entity.setQuota(dto.getQuota());
        entity.setGrossAmount(dto.getGrossAmount());
        if (dto.getAuditingOperation() != null) {
            AuditingOperation st = new AuditingOperation();
            st.setId(dto.getAuditingOperation().getId());
            entity.setOperation(st);
        }
        return entity;
    }

    public List<AuditingInstallmentDTO> toDto(List<AuditingInstallment> entityList) {
        List<AuditingInstallmentDTO> listDTO = new ArrayList<>();
        for (AuditingInstallment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<AuditingInstallmentDTO> toDto(Set<AuditingInstallment> entityList) {
        Set<AuditingInstallmentDTO> listDTO = new HashSet<>();
        for (AuditingInstallment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<AuditingInstallment> toEntity(Set<AuditingInstallmentDTO> dtoList) {
        Set<AuditingInstallment> listEntity = new HashSet<>();
        for (AuditingInstallmentDTO dto : dtoList) {
            listEntity.add(this.toEntity(dto));
        }
        return listEntity;
    }

}
