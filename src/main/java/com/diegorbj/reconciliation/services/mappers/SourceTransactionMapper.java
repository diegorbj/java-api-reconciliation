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
public abstract class SourceTransactionMapper {

    public SourceTransactionDTO toDto(SourceTransaction entity) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        InstallmentMapper _installmentMapper = new InstallmentMapperImpl();

        SourceTransactionDTO newObj = new SourceTransactionDTO();
        newObj.setId(entity.getId());
        newObj.setDate(entity.getDate());
        newObj.setUniqueSequentialNumber(entity.getUniqueSequentialNumber());
        newObj.setTransactionId(entity.getTransactionId());
        newObj.setAuthorizationCode(entity.getAuthorizationCode());
        newObj.setTransactionStatus(entity.getTransactionStatus());
        newObj.setNumberOfInstallments(entity.getNumberOfInstallments());
        newObj.setGrossAmount(entity.getGrossAmount());
        newObj.setTransactionInformation(entity.getTransactionInformation());
        newObj.setMerchant(_merchantMapper.toDto(entity.getMerchant()));
        newObj.setFinancialInstitution(_financialInstitutionMapper.toDto(entity.getFinancialInstitution()));
        newObj.setFinancialService(_financialServiceMapper.toDto(entity.getFinancialService()));
        newObj.setServiceLabel(_serviceLabelMapper.toDto(entity.getServiceLabel()));
        newObj.setCardType(_cardTypeMapper.toDto(entity.getCardType()));
        newObj.setModality(_modalityMapper.toDto(entity.getModality()));
        newObj.setInstallments(_installmentMapper.toDto((Set<Installment>) entity.getInstallments()));

        return newObj;
    }

    public SourceTransaction toEntity(SourceTransactionDTO dto) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        InstallmentMapper _installmentMapper = new InstallmentMapperImpl();

        SourceTransaction entity = new SourceTransaction();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setUniqueSequentialNumber(dto.getUniqueSequentialNumber());
        entity.setTransactionId(dto.getTransactionId());
        entity.setAuthorizationCode(dto.getAuthorizationCode());
        entity.setTransactionStatus(dto.getTransactionStatus());
        entity.setNumberOfInstallments(dto.getNumberOfInstallments());
        entity.setGrossAmount(dto.getGrossAmount());
        entity.setTransactionInformation(dto.getTransactionInformation());
        entity.setMerchant(_merchantMapper.toEntity(dto.getMerchant()));
        entity.setFinancialInstitution(_financialInstitutionMapper.toEntity(dto.getFinancialInstitution()));
        entity.setFinancialService(_financialServiceMapper.toEntity(dto.getFinancialService()));
        entity.setServiceLabel(_serviceLabelMapper.toEntity(dto.getServiceLabel()));
        entity.setCardType(_cardTypeMapper.toEntity(dto.getCardType()));
        entity.setModality(_modalityMapper.toEntity(dto.getModality()));
        entity.setInstallments(_installmentMapper.toEntity((HashSet<InstallmentDTO>) dto.getInstallments()));

        return entity;
    }

    public List<SourceTransactionDTO> toDto(List<SourceTransaction> entityList) {
        List<SourceTransactionDTO> dtoList = new ArrayList<>();
        for (SourceTransaction entity : entityList) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

}
