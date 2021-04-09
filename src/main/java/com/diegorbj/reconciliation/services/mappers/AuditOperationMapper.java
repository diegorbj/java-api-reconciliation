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
public abstract class AuditOperationMapper {

    public AuditOperationDTO toDto(AuditOperation entity) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        AuditInstallmentMapper _auditInstallmentMapper = new AuditInstallmentMapperImpl();

        AuditOperationDTO newObj = new AuditOperationDTO();
        newObj.setId(entity.getId());
        newObj.setDate(entity.getDate());
        newObj.setAuthorizationId(entity.getAuthorizationId());
        newObj.setPointOfSaleId(entity.getPointOfSaleId());
        newObj.setTransactionId(entity.getTransactionId());
        newObj.setAuthorizationCode(entity.getAuthorizationCode());
        newObj.setTransactionStatus(entity.getTransactionStatus());
        newObj.setNumberOfInstallments(entity.getNumberOfInstallments());
        newObj.setGrossAmount(entity.getGrossAmount());
        newObj.setTransactionInformation(entity.getTransactionInformation());
        newObj.setRebateInformation(entity.getRebateInformation());
        newObj.setMerchant(_merchantMapper.toDto(entity.getMerchant()));
        newObj.setFinancialInstitution(_financialInstitutionMapper.toDto(entity.getFinancialInstitution()));
        newObj.setFinancialService(_financialServiceMapper.toDto(entity.getFinancialService()));
        newObj.setServiceLabel(_serviceLabelMapper.toDto(entity.getServiceLabel()));
        newObj.setCardType(_cardTypeMapper.toDto(entity.getCardType()));
        newObj.setModality(_modalityMapper.toDto(entity.getModality()));
        newObj.setInstallments(_auditInstallmentMapper.toDto((Set<AuditInstallment>) entity.getInstallments()));

        return newObj;
    }

    public AuditOperation toEntity(AuditOperationDTO dto) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        AuditInstallmentMapper _auditInstallmentMapper = new AuditInstallmentMapperImpl();

        AuditOperation entity = new AuditOperation();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setAuthorizationId(dto.getAuthorizationId());
        entity.setPointOfSaleId(dto.getPointOfSaleId());
        entity.setTransactionId(dto.getTransactionId());
        entity.setAuthorizationCode(dto.getAuthorizationCode());
        entity.setTransactionStatus(dto.getTransactionStatus());
        entity.setNumberOfInstallments(dto.getNumberOfInstallments());
        entity.setGrossAmount(dto.getGrossAmount());
        entity.setTransactionInformation(dto.getTransactionInformation());
        entity.setRebateInformation(dto.getRebateInformation());
        entity.setMerchant(_merchantMapper.toEntity(dto.getMerchant()));
        entity.setFinancialInstitution(_financialInstitutionMapper.toEntity(dto.getFinancialInstitution()));
        entity.setFinancialService(_financialServiceMapper.toEntity(dto.getFinancialService()));
        entity.setServiceLabel(_serviceLabelMapper.toEntity(dto.getServiceLabel()));
        entity.setCardType(_cardTypeMapper.toEntity(dto.getCardType()));
        entity.setModality(_modalityMapper.toEntity(dto.getModality()));
        entity.setInstallments(_auditInstallmentMapper.toEntity((HashSet<AuditInstallmentDTO>) dto.getInstallments()));

        return entity;
    }

    public List<AuditOperationDTO> toDto(List<AuditOperation> entityList) {
        List<AuditOperationDTO> dtoList = new ArrayList<>();
        for (AuditOperation entity : entityList) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

}
