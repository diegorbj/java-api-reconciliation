package com.diegorbj.reconciliation.services.dto;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import lombok.*;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ConfirmOperationDTO extends OperationDTO {

    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private Set<ConfirmInstallmentDTO> installments = new HashSet<>();

    public ConfirmOperationDTO(Long id, Instant date, Long authorizationId, Long pointOfSaleId, String transactionId, String authorizationCode, TransactionStatus transactionStatus, Integer numberOfInstallments, Double grossAmount, String transactionInformation, String rebateInformation, MerchantDTO merchant, FinancialInstitutionDTO financialInstitution, FinancialServiceDTO financialService, ServiceLabelDTO serviceLabel, CardTypeDTO cardType, ModalityDTO modality) {
        super(id, date, authorizationId, pointOfSaleId, transactionId, authorizationCode, transactionStatus, numberOfInstallments, grossAmount, transactionInformation, rebateInformation, merchant, financialInstitution, financialService, serviceLabel, cardType, modality);
    }

    public static ConfirmOperationDTO fromJSON(String jsonString) {
        return fromJSON(new JSONObject(jsonString));
    }

    public static ConfirmOperationDTO fromJSON(JSONObject jsonObject) {
        ConfirmOperationDTO obj = new ConfirmOperationDTO();
        obj.setId(jsonObject.get("id") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("id").toString()));
        obj.setDate(jsonObject.get("date") == JSONObject.NULL ? null : Instant.parse(jsonObject.get("date").toString()));
        obj.setAuthorizationId(jsonObject.get("authorizationId") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("authorizationId").toString()));
        obj.setPointOfSaleId(jsonObject.get("pointOfSaleId") == JSONObject.NULL ? null : Long.parseLong(jsonObject.get("pointOfSaleId").toString()));
        obj.setTransactionId(jsonObject.get("transactionId") == JSONObject.NULL ? null : jsonObject.get("transactionId").toString());
        obj.setAuthorizationCode(jsonObject.get("authorizationCode") == JSONObject.NULL ? null : jsonObject.get("authorizationCode").toString());
        obj.setTransactionStatus(jsonObject.get("transactionStatus") == JSONObject.NULL ? null : TransactionStatus.valueOf(jsonObject.get("transactionStatus").toString()));
        obj.setNumberOfInstallments(jsonObject.get("numberOfInstallments") == JSONObject.NULL ? null : Integer.parseInt(jsonObject.get("numberOfInstallments").toString()));
        obj.setGrossAmount(jsonObject.get("grossAmount") == JSONObject.NULL ? null : Double.parseDouble(jsonObject.get("grossAmount").toString()));
        obj.setTransactionInformation(jsonObject.get("transactionInformation") == JSONObject.NULL ? null : jsonObject.get("transactionInformation").toString());
        obj.setRebateInformation(jsonObject.get("rebateInformation") == JSONObject.NULL ? null : jsonObject.get("rebateInformation").toString());
        obj.setMerchant(MerchantDTO.fromJSON(jsonObject.get("merchant").toString()));
        obj.setFinancialInstitution(FinancialInstitutionDTO.fromJSON(jsonObject.get("financialInstitution").toString()));
        obj.setFinancialService(FinancialServiceDTO.fromJSON(jsonObject.get("financialService").toString()));
        obj.setServiceLabel(ServiceLabelDTO.fromJSON(jsonObject.get("serviceLabel").toString()));
        obj.setCardType(CardTypeDTO.fromJSON(jsonObject.get("cardType").toString()));
        obj.setModality(ModalityDTO.fromJSON(jsonObject.get("modality").toString()));
        return obj;
    }

}
