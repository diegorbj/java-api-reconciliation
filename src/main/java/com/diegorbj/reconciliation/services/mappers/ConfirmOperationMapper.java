package com.diegorbj.reconciliation.services.mappers;

import com.diegorbj.reconciliation.domain.ConfirmInstallment;
import com.diegorbj.reconciliation.domain.ConfirmOperation;
import com.diegorbj.reconciliation.services.dto.ConfirmInstallmentDTO;
import com.diegorbj.reconciliation.services.dto.ConfirmOperationDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class ConfirmOperationMapper {

    public ConfirmOperationDTO toDto(ConfirmOperation entity) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        ConfirmInstallmentMapper _ConfirmInstallmentMapper = new ConfirmInstallmentMapperImpl();

        ConfirmOperationDTO newObj = new ConfirmOperationDTO();
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
        newObj.setInstallments(_ConfirmInstallmentMapper.toDto((Set<ConfirmInstallment>) entity.getInstallments()));

        return newObj;
    }

    public ConfirmOperation toEntity(ConfirmOperationDTO dto) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();
        ConfirmInstallmentMapper _ConfirmInstallmentMapper = new ConfirmInstallmentMapperImpl();

        ConfirmOperation entity = new ConfirmOperation();
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
        entity.setInstallments(_ConfirmInstallmentMapper.toEntity((HashSet<ConfirmInstallmentDTO>) dto.getInstallments()));

        return entity;
    }

    public List<ConfirmOperationDTO> toDto(List<ConfirmOperation> entityList) {
        List<ConfirmOperationDTO> dtoList = new ArrayList<>();
        for (ConfirmOperation entity : entityList) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

}
