package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.Installment;
import com.diegorbj.reconciliation.domain.SourceTransaction;
import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
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

    public SourceTransaction toDomain() {
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
        newObj.setMerchant(this.getMerchant().toDomain());
        newObj.setFinancialInstitution(this.getFinancialInstitution().toDomain());
        newObj.setFinancialService(this.getFinancialService().toDomain());
        newObj.setServiceLabel(this.getServiceLabel().toDomain());
        newObj.setCardType(this.getCardType().toDomain());
        newObj.setModality(this.getModality().toDomain());
        for (InstallmentDTO o : this.getInstallments()){
            newObj.getInstallments().add(o.toDomain());
        }
        return newObj;
    }

    public static SourceTransactionDTO fromDomain(SourceTransaction obj){
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
        newObj.setMerchant(MerchantDTO.fromDomain(obj.getMerchant()));
        newObj.setFinancialInstitution(FinancialInstitutionDTO.fromDomain(obj.getFinancialInstitution()));
        newObj.setFinancialService(FinancialServiceDTO.fromDomain(obj.getFinancialService()));
        newObj.setServiceLabel(ServiceLabelDTO.fromDomain(obj.getServiceLabel()));
        newObj.setCardType(CardTypeDTO.fromDomain(obj.getCardType()));
        newObj.setModality(ModalityDTO.fromDomain(obj.getModality()));
        for (Installment o : obj.getInstallments()){
            newObj.getInstallments().add(InstallmentDTO.fromDomain(o));
        }
        return newObj;
    }

    public static SourceTransactionDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static SourceTransactionDTO fromJSON(JSONObject jsonObject) {
        SourceTransactionDTO obj = new SourceTransactionDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null: Long.parseLong(jsonObject.get("id").toString()));
        //obj.setDate(jsonObject.get("date") == JSONObject.NULL ? null: jsonObject.get("date").toString());
        obj.setUniqueSequentialNumber(jsonObject.get("UniqueSequentialNumber") == JSONObject.NULL ? null: Long.parseLong(jsonObject.get("UniqueSequentialNumber").toString()));
        obj.setTransactionId(jsonObject.get("transactionId") == JSONObject.NULL ? null: jsonObject.get("transactionId").toString());
        obj.setAuthorizationCode(jsonObject.get("authorizationCode") == JSONObject.NULL ? null: jsonObject.get("authorizationCode").toString());
        obj.setTransactionStatus(jsonObject.get("TransactionStatus") == JSONObject.NULL ? null: TransactionStatus.valueOf(Integer.parseInt(jsonObject.get("TransactionStatus").toString())));
        obj.setNumberOfInstallments(jsonObject.get("NumberOfInstallments") == JSONObject.NULL ? null: Integer.parseInt(jsonObject.get("NumberOfInstallments").toString()));
        obj.setGrossAmount(jsonObject.get("GrossAmount") == JSONObject.NULL ? null: Double.parseDouble(jsonObject.get("GrossAmount").toString()));
        obj.setTransactionInformation(jsonObject.get("TransactionInformation") == JSONObject.NULL ? null: jsonObject.get("TransactionInformation").toString());
        obj.setMerchant(MerchantDTO.fromJSON(jsonObject.get("Merchant").toString()));
        obj.setFinancialInstitution(FinancialInstitutionDTO.fromJSON(jsonObject.get("FinancialInstitution").toString()));
        obj.setFinancialService(FinancialServiceDTO.fromJSON(jsonObject.get("FinancialService").toString()));
        obj.setServiceLabel(ServiceLabelDTO.fromJSON(jsonObject.get("ServiceLabel").toString()));
        obj.setCardType(CardTypeDTO.fromJSON(jsonObject.get("CardType").toString()));
        obj.setModality(ModalityDTO.fromJSON(jsonObject.get("Modality").toString()));

        return obj;
    }

}
