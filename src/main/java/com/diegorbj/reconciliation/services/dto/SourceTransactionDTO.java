package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.diegorbj.reconciliation.services.mappers.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SourceTransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant date;
    private Long uniqueSequentialNumber;
    private String transactionId;
    private String authorizationCode;
    private TransactionStatus transactionStatus;
    private Integer numberOfInstallments;
    private Double grossAmount;
    private String transactionInformation;
    private MerchantDTO merchant;
    private FinancialInstitutionDTO financialInstitution;
    private FinancialServiceDTO financialService;
    private ServiceLabelDTO serviceLabel;
    private CardTypeDTO cardType;
    private ModalityDTO modality;
    private Set<InstallmentDTO> installments = new HashSet<>();

    public SourceTransactionDTO(Long id, Instant date, Long uniqueSequentialNumber, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, MerchantDTO merchant, FinancialInstitutionDTO financialInstitution, FinancialServiceDTO financialService, ServiceLabelDTO serviceLabel, CardTypeDTO cardType, ModalityDTO modality) {
        this.id = id;
        this.date = date;
        this.uniqueSequentialNumber = uniqueSequentialNumber;
        this.transactionId = transactionId;
        this.authorizationCode = authorizationCode;
        this.transactionStatus = transactionStatus;
        this.numberOfInstallments = numberOfInstallments;
        this.grossAmount = grossAmount;
        this.transactionInformation = transactionInformation;
        this.merchant = merchant;
        this.financialInstitution = financialInstitution;
        this.financialService = financialService;
        this.serviceLabel = serviceLabel;
        this.cardType = cardType;
        this.modality = modality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceTransactionDTO that = (SourceTransactionDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public SourceTransaction toEntity() {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();

        SourceTransaction newObj = new SourceTransaction();
        newObj.setId(this.getId());
        newObj.setDate(this.getDate());
        newObj.setUniqueSequentialNumber(this.getUniqueSequentialNumber());
        newObj.setTransactionId(this.getTransactionId());
        newObj.setAuthorizationCode(this.getAuthorizationCode());
        newObj.setTransactionStatus(this.getTransactionStatus());
        newObj.setNumberOfInstallments(this.getNumberOfInstallments());
        newObj.setGrossAmount(this.getGrossAmount());
        newObj.setTransactionInformation(this.getTransactionInformation());
        newObj.setMerchant(_merchantMapper.toEntity(this.getMerchant()));
        newObj.setFinancialInstitution(_financialInstitutionMapper.toEntity(this.getFinancialInstitution()));
        newObj.setFinancialService(_financialServiceMapper.toEntity(this.getFinancialService()));
        newObj.setServiceLabel(_serviceLabelMapper.toEntity(this.getServiceLabel()));
        newObj.setCardType(_cardTypeMapper.toEntity(this.getCardType()));
        newObj.setModality(_modalityMapper.toEntity(this.getModality()));
        for (InstallmentDTO o : this.getInstallments()) {
            newObj.getInstallments().add(o.toEntity());
        }
        return newObj;
    }

    public static SourceTransactionDTO toDto(SourceTransaction obj) {
        MerchantMapper _merchantMapper = new MerchantMapperImpl();
        FinancialInstitutionMapper _financialInstitutionMapper = new FinancialInstitutionMapperImpl();
        FinancialServiceMapper _financialServiceMapper = new FinancialServiceMapperImpl();
        ServiceLabelMapper _serviceLabelMapper = new ServiceLabelMapperImpl();
        CardTypeMapper _cardTypeMapper = new CardTypeMapperImpl();
        ModalityMapper _modalityMapper = new ModalityMapperImpl();

        SourceTransactionDTO newObj = new SourceTransactionDTO();
        newObj.setId(obj.getId());
        newObj.setDate(obj.getDate());
        newObj.setUniqueSequentialNumber(obj.getUniqueSequentialNumber());
        newObj.setTransactionId(obj.getTransactionId());
        newObj.setAuthorizationCode(obj.getAuthorizationCode());
        newObj.setTransactionStatus(obj.getTransactionStatus());
        newObj.setNumberOfInstallments(obj.getNumberOfInstallments());
        newObj.setGrossAmount(obj.getGrossAmount());
        newObj.setTransactionInformation(obj.getTransactionInformation());
        newObj.setMerchant(_merchantMapper.toDto(obj.getMerchant()));
        newObj.setFinancialInstitution(_financialInstitutionMapper.toDto(obj.getFinancialInstitution()));
        newObj.setFinancialService(_financialServiceMapper.toDto(obj.getFinancialService()));
        newObj.setServiceLabel(_serviceLabelMapper.toDto(obj.getServiceLabel()));
        newObj.setCardType(_cardTypeMapper.toDto(obj.getCardType()));
        newObj.setModality(_modalityMapper.toDto(obj.getModality()));
        for (Installment o : obj.getInstallments()) {
            newObj.getInstallments().add(InstallmentDTO.toDto(o));
        }
        return newObj;
    }

    public static SourceTransactionDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static SourceTransactionDTO fromJSON(JSONObject jsonObject) {
        SourceTransactionDTO obj = new SourceTransactionDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setDate(jsonObject.get("date") == JSONObject.NULL ? null : Instant.parse(jsonObject.get("date").toString()));
        obj.setUniqueSequentialNumber(jsonObject.get("uniqueSequentialNumber") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("uniqueSequentialNumber").toString()));
        obj.setTransactionId(jsonObject.get("transactionId") == JSONObject.NULL ? null : jsonObject.get("transactionId").toString());
        obj.setAuthorizationCode(jsonObject.get("authorizationCode") == JSONObject.NULL ? null : jsonObject.get("authorizationCode").toString());
        obj.setTransactionStatus(jsonObject.get("transactionStatus") == JSONObject.NULL ? null : TransactionStatus.valueOf(jsonObject.get("transactionStatus").toString()));
        obj.setNumberOfInstallments(jsonObject.get("numberOfInstallments") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("numberOfInstallments").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setTransactionInformation(jsonObject.get("transactionInformation") == JSONObject.NULL ? null : jsonObject.get("transactionInformation").toString());
        obj.setMerchant(MerchantDTO.fromJSON(jsonObject.get("merchant").toString()));
        obj.setFinancialInstitution(FinancialInstitutionDTO.fromJSON(jsonObject.get("financialInstitution").toString()));
        obj.setFinancialService(FinancialServiceDTO.fromJSON(jsonObject.get("financialService").toString()));
        obj.setServiceLabel(ServiceLabelDTO.fromJSON(jsonObject.get("serviceLabel").toString()));
        obj.setCardType(CardTypeDTO.fromJSON(jsonObject.get("cardType").toString()));
        obj.setModality(ModalityDTO.fromJSON(jsonObject.get("modality").toString()));
        return obj;
    }
}
