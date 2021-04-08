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
public abstract class AuditingOperationMapper {

    public AuditingOperationDTO toDto(AuditingOperation entity) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        AuditingInstallmentMapper _auditingInstallmentMapper = new AuditingInstallmentMapperImpl();

        AuditingOperationDTO newObj = new AuditingOperationDTO();
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
        newObj.setInstallments(_auditingInstallmentMapper.toDto((Set<AuditingInstallment>) entity.getAuditingInstallments()));

        return newObj;
    }

    public AuditingOperation toEntity(AuditingOperationDTO dto) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        AuditingInstallmentMapper _auditingInstallmentMapper = new AuditingInstallmentMapperImpl();

        AuditingOperation entity = new AuditingOperation();
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
        entity.setAuditingInstallments(_auditingInstallmentMapper.toEntity((HashSet<AuditingInstallmentDTO>) dto.getInstallments()));

        return entity;
    }

    public List<AuditingOperationDTO> toDto(List<AuditingOperation> entityList) {
        List<AuditingOperationDTO> dtoList = new ArrayList<>();
        for (AuditingOperation entity : entityList) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

}
