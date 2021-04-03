package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.dto.MerchantDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantDTO toDto(Merchant obj);

    Merchant toEntity(MerchantDTO obj);

    List<MerchantDTO> toDto(List<Merchant> list);

    List<Merchant> toEntity(List<MerchantDTO> list);

}
