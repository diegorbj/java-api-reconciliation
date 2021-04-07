package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionSearchParam;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionFilterParamDTO;
import com.diegorbj.reconciliation.repositories.criterias.params.SourceTransactionSearchParamDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SourceTransactionFilterParamMapper {

    public SourceTransactionFilterParamDTO toDto(SourceTransactionFilterParam entity) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        InstallmentMapper _installmentMapper = new InstallmentMapperImpl();

        SourceTransactionFilterParamDTO newObj = new SourceTransactionSearchParamDTO();
        newObj.setDateFrom(entity.getDateFrom());
        newObj.setDateTo(entity.getDateTo());
        newObj.setUniqueSequentialNumber(entity.getUniqueSequentialNumber());
        newObj.setTransactionId(entity.getTransactionId());
        newObj.setAuthorizationCode(entity.getAuthorizationCode());
        newObj.setTransactionStatus(entity.getTransactionStatus());
        newObj.setNumberOfInstallmentsFrom(entity.getNumberOfInstallmentsFrom());
        newObj.setNumberOfInstallmentsTo(entity.getNumberOfInstallmentsTo());
        newObj.setGrossAmountFrom(entity.getGrossAmountFrom());
        newObj.setGrossAmountTo(entity.getGrossAmountTo());
        newObj.setTransactionInformation(entity.getTransactionInformation());
        newObj.setMerchant(_merchantMapper.toDto(entity.getMerchant()));
        newObj.setFinancialInstitution(_financialInstitutionMapper.toDto(entity.getFinancialInstitution()));
        newObj.setFinancialService(_financialServiceMapper.toDto(entity.getFinancialService()));
        newObj.setServiceLabel(_serviceLabelMapper.toDto(entity.getServiceLabel()));
        newObj.setCardType(_cardTypeMapper.toDto(entity.getCardType()));
        newObj.setModality(_modalityMapper.toDto(entity.getModality()));

        return newObj;
    }

    public SourceTransactionFilterParam toEntity(SourceTransactionFilterParamDTO dto) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        InstallmentMapper _installmentMapper = new InstallmentMapperImpl();

        SourceTransactionFilterParam entity = new SourceTransactionSearchParam();
        entity.setDateFrom(dto.getDateFrom());
        entity.setDateTo(dto.getDateTo());
        entity.setUniqueSequentialNumber(dto.getUniqueSequentialNumber());
        entity.setTransactionId(dto.getTransactionId());
        entity.setAuthorizationCode(dto.getAuthorizationCode());
        entity.setTransactionStatus(dto.getTransactionStatus());
        entity.setNumberOfInstallmentsFrom(dto.getNumberOfInstallmentsFrom());
        entity.setNumberOfInstallmentsTo(dto.getNumberOfInstallmentsTo());
        entity.setGrossAmountFrom(dto.getGrossAmountFrom());
        entity.setGrossAmountTo(dto.getGrossAmountTo());
        entity.setTransactionInformation(dto.getTransactionInformation());
        entity.setMerchant(_merchantMapper.toEntity(dto.getMerchant()));
        entity.setFinancialInstitution(_financialInstitutionMapper.toEntity(dto.getFinancialInstitution()));
        entity.setFinancialService(_financialServiceMapper.toEntity(dto.getFinancialService()));
        entity.setServiceLabel(_serviceLabelMapper.toEntity(dto.getServiceLabel()));
        entity.setCardType(_cardTypeMapper.toEntity(dto.getCardType()));
        entity.setModality(_modalityMapper.toEntity(dto.getModality()));

        return entity;
    }

    public List<SourceTransactionFilterParamDTO> toDto(List<SourceTransactionFilterParam> entityList) {
        List<SourceTransactionFilterParamDTO> dtoList = new ArrayList<>();
        for (SourceTransactionFilterParam entity : entityList) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

}
