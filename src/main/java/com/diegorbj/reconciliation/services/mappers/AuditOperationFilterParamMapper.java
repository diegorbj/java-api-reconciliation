package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationFilterParam;
import com.diegorbj.reconciliation.repositories.criterias.params.domain.AuditOperationSearchParam;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.AuditOperationFilterParamDTO;
import com.diegorbj.reconciliation.repositories.criterias.params.dto.AuditOperationSearchParamDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuditOperationFilterParamMapper {

    public AuditOperationFilterParamDTO toDto(AuditOperationFilterParam entity) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        AuditInstallmentMapper _auditInstallmentMapper = new AuditInstallmentMapperImpl();

        AuditOperationFilterParamDTO newObj = new AuditOperationSearchParamDTO();
        newObj.setDateFrom(entity.getDateFrom());
        newObj.setDateTo(entity.getDateTo());
        newObj.setAuthorizationId(entity.getAuthorizationId());
        newObj.setTransactionId(entity.getTransactionId());
        newObj.setAuthorizationCode(entity.getAuthorizationCode());
        newObj.setTransactionStatus(entity.getTransactionStatus());
        newObj.setNumberOfInstallmentsFrom(entity.getNumberOfInstallmentsFrom());
        newObj.setNumberOfInstallmentsTo(entity.getNumberOfInstallmentsTo());
        newObj.setGrossAmountFrom(entity.getGrossAmountFrom());
        newObj.setGrossAmountTo(entity.getGrossAmountTo());
        newObj.setTransactionInformation(entity.getTransactionInformation());
        newObj.setRebateInformation(entity.getRebateInformation());
        newObj.setMerchant(_merchantMapper.toDto(entity.getMerchant()));
        newObj.setFinancialInstitution(_financialInstitutionMapper.toDto(entity.getFinancialInstitution()));
        newObj.setFinancialService(_financialServiceMapper.toDto(entity.getFinancialService()));
        newObj.setServiceLabel(_serviceLabelMapper.toDto(entity.getServiceLabel()));
        newObj.setCardType(_cardTypeMapper.toDto(entity.getCardType()));
        newObj.setModality(_modalityMapper.toDto(entity.getModality()));

        return newObj;
    }

    public AuditOperationFilterParam toEntity(AuditOperationFilterParamDTO dto) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        AuditInstallmentMapper _auditInstallmentMapper = new AuditInstallmentMapperImpl();

        AuditOperationFilterParam entity = new AuditOperationSearchParam();
        entity.setDateFrom(dto.getDateFrom());
        entity.setDateTo(dto.getDateTo());
        entity.setAuthorizationId(dto.getAuthorizationId());
        entity.setTransactionId(dto.getTransactionId());
        entity.setAuthorizationCode(dto.getAuthorizationCode());
        entity.setTransactionStatus(dto.getTransactionStatus());
        entity.setNumberOfInstallmentsFrom(dto.getNumberOfInstallmentsFrom());
        entity.setNumberOfInstallmentsTo(dto.getNumberOfInstallmentsTo());
        entity.setGrossAmountFrom(dto.getGrossAmountFrom());
        entity.setGrossAmountTo(dto.getGrossAmountTo());
        entity.setTransactionInformation(dto.getTransactionInformation());
        entity.setRebateInformation(dto.getRebateInformation());
        entity.setMerchant(_merchantMapper.toEntity(dto.getMerchant()));
        entity.setFinancialInstitution(_financialInstitutionMapper.toEntity(dto.getFinancialInstitution()));
        entity.setFinancialService(_financialServiceMapper.toEntity(dto.getFinancialService()));
        entity.setServiceLabel(_serviceLabelMapper.toEntity(dto.getServiceLabel()));
        entity.setCardType(_cardTypeMapper.toEntity(dto.getCardType()));
        entity.setModality(_modalityMapper.toEntity(dto.getModality()));

        return entity;
    }

    public List<AuditOperationFilterParamDTO> toDto(List<AuditOperationFilterParam> entityList) {
        List<AuditOperationFilterParamDTO> dtoList = new ArrayList<>();
        for (AuditOperationFilterParam entity : entityList) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

}