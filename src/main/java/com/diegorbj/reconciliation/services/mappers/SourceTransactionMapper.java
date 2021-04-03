package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.services.dto.SourceTransactionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SourceTransactionMapper {

    SourceTransactionDTO toDto(SourceTransaction obj);

    SourceTransaction toEntity(SourceTransactionDTO obj);

    List<SourceTransactionDTO> toDto(List<SourceTransaction> list);

    List<SourceTransaction> toEntity(List<SourceTransactionDTO> list);

}
