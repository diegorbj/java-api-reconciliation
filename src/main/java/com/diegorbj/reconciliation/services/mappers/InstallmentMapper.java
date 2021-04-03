package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.services.dto.InstallmentDTO;
import com.diegorbj.reconciliation.services.dto.SourceTransactionDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class InstallmentMapper {

    public InstallmentDTO toDto(Installment entity) {
        InstallmentDTO dto = new InstallmentDTO();
        dto.setId(entity.getId());
        dto.setQuota(entity.getQuota());
        dto.setGrossAmount(entity.getGrossAmount());
        if (entity.getSourceTransaction() != null) {
            SourceTransactionDTO stDTO = new SourceTransactionDTO();
            stDTO.setId(entity.getSourceTransaction().getId());
            dto.setSourceTransaction(stDTO);
        }
        return dto;
    }

    public Installment toEntity(InstallmentDTO dto) {
        Installment entity = new Installment();
        entity.setId(dto.getId());
        entity.setQuota(dto.getQuota());
        entity.setGrossAmount(dto.getGrossAmount());
        if (dto.getSourceTransaction() != null) {
            SourceTransaction st = new SourceTransaction();
            st.setId(dto.getSourceTransaction().getId());
            entity.setSourceTransaction(st);
        }
        return entity;
    }

    public List<InstallmentDTO> toDto(List<Installment> entityList) {
        List<InstallmentDTO> listDTO = new ArrayList<>();
        for (Installment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<InstallmentDTO> toDto(Set<Installment> entityList) {
        Set<InstallmentDTO> listDTO = new HashSet<>();
        for (Installment dto : entityList) {
            listDTO.add(this.toDto(dto));
        }
        return listDTO;
    }

    public Set<Installment> toEntity(Set<InstallmentDTO> dtoList) {
        Set<Installment> listEntity = new HashSet<>();
        for (InstallmentDTO dto : dtoList) {
            listEntity.add(this.toEntity(dto));
        }
        return listEntity;
    }

}
