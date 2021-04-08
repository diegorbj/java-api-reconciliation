package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.diegorbj.reconciliation.services.AuditInstallmentService;
import com.diegorbj.reconciliation.services.dto.*;
import com.diegorbj.reconciliation.services.AuditOperationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
class AuditOperationServiceTest {

    @Autowired
    private AuditOperationService _service;

    @Autowired
    private AuditInstallmentService _childService;

    private AuditOperationDTO testObject;

    private AuditInstallmentDTO testChildObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullAuditingOperationService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    public void shouldReturnNotNullInstallmentService() {
        assertNotNull(_childService);
    }

    @Test
    @Order(3)
    public void shouldReturnAuditingOperationCreatedWithSuccess() throws JSONException {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(AuditOperationDTO.fromJSON(jsonObject));

        assertNotNull(testObject);
        assertNotNull(testObject.getId());
        assertEquals(testObject.getDate(), Instant.parse(jsonObject.get("date").toString()));
        assertEquals(testObject.getAuthorizationId(), Long.parseLong(jsonObject.get("authorizationId").toString()));
        assertNull(testObject.getPointOfSaleId());
        assertEquals(testObject.getTransactionId(), jsonObject.get("transactionId").toString());
        assertEquals(testObject.getAuthorizationCode(), jsonObject.get("authorizationCode").toString());
        assertEquals(testObject.getTransactionStatus(), TransactionStatus.valueOf(jsonObject.get("transactionStatus").toString()));
        assertEquals(testObject.getNumberOfInstallments(), Integer.parseInt(jsonObject.get("numberOfInstallments").toString()));
        assertEquals(testObject.getGrossAmount(), Double.parseDouble(jsonObject.get("grossAmount").toString()));
        assertEquals(testObject.getTransactionInformation(), jsonObject.get("transactionInformation"));
        assertEquals(testObject.getMerchant(), MerchantDTO.fromJSON(jsonObject.get("merchant").toString()));
        assertEquals(testObject.getFinancialInstitution(), FinancialInstitutionDTO.fromJSON(jsonObject.get("financialInstitution").toString()));
        assertEquals(testObject.getFinancialService(), FinancialServiceDTO.fromJSON(jsonObject.get("financialService").toString()));
        assertEquals(testObject.getServiceLabel(), ServiceLabelDTO.fromJSON(jsonObject.get("serviceLabel").toString()));
        assertEquals(testObject.getCardType(), CardTypeDTO.fromJSON(jsonObject.get("cardType").toString()));
        assertEquals(testObject.getModality(), ModalityDTO.fromJSON(jsonObject.get("modality").toString()));
    }

    @Test
    @Order(4)
    public void shouldReturnAuditingOperationUpdatedWithSuccess() throws JSONException {
        JSONObject jsonObject = setObjectToUpdate();
        AuditOperationDTO updatedObject = AuditOperationDTO.fromJSON(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertNotNull(updatedObject.getId());
        assertEquals(updatedObject.getDate(), Instant.parse(jsonObject.get("date").toString()));
        assertEquals(updatedObject.getAuthorizationId(), Long.parseLong(jsonObject.get("authorizationId").toString()));
        assertEquals(updatedObject.getTransactionId(), jsonObject.get("transactionId").toString());
        assertNull(updatedObject.getPointOfSaleId());
        assertEquals(updatedObject.getAuthorizationCode(), jsonObject.get("authorizationCode").toString());
        assertEquals(updatedObject.getTransactionStatus(), TransactionStatus.valueOf(jsonObject.get("transactionStatus").toString()));
        assertEquals(updatedObject.getNumberOfInstallments(), Integer.parseInt(jsonObject.get("numberOfInstallments").toString()));
        assertEquals(updatedObject.getGrossAmount(), Double.parseDouble(jsonObject.get("grossAmount").toString()));
        assertEquals(updatedObject.getTransactionInformation(), jsonObject.get("transactionInformation"));
        assertEquals(updatedObject.getRebateInformation(), jsonObject.get("rebateInformation"));
        assertEquals(updatedObject.getMerchant(), MerchantDTO.fromJSON(jsonObject.get("merchant").toString()));
        assertEquals(updatedObject.getFinancialInstitution(), FinancialInstitutionDTO.fromJSON(jsonObject.get("financialInstitution").toString()));
        assertEquals(updatedObject.getFinancialService(), FinancialServiceDTO.fromJSON(jsonObject.get("financialService").toString()));
        assertEquals(updatedObject.getServiceLabel(), ServiceLabelDTO.fromJSON(jsonObject.get("serviceLabel").toString()));
        assertEquals(updatedObject.getCardType(), CardTypeDTO.fromJSON(jsonObject.get("cardType").toString()));
        assertEquals(updatedObject.getModality(), ModalityDTO.fromJSON(jsonObject.get("modality").toString()));

        testObject = updatedObject;
    }

    @Test
    @Order(5)
    public void shouldReturnNotNullAuditingOperationFindAll() {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(6)
    public void shouldReturnNotNullAuditingOperationFindById() {
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(7)
    public void shouldReturnInstallmentCreatedWithSuccess() throws JSONException, JsonProcessingException {
        JSONObject jsonObject = setObjectChildToCreate();

        testChildObject = _childService.save(AuditInstallmentDTO.fromJSON(jsonObject));

        assertNotNull(testChildObject);
        assertNotNull(testChildObject.getId());
        assertEquals(testChildObject.getQuota(), Integer.parseInt(jsonObject.get("quota").toString()));
        assertEquals(testChildObject.getGrossAmount(), Double.parseDouble(jsonObject.get("grossAmount").toString()));
        assertEquals(testChildObject.getAuditingOperation(), AuditOperationDTO.fromJSON(jsonObject.get("operation").toString()));
    }

    @Test
    @Order(8)
    public void shouldReturnInstallmentUpdatedWithSuccess() throws JSONException, JsonProcessingException {
        JSONObject jsonObject = setObjectChildToUpdate();

        AuditInstallmentDTO updatedObject = AuditInstallmentDTO.fromJSON(jsonObject);
        updatedObject = _childService.update(updatedObject);

        assertNotNull(updatedObject);
        assertNotNull(updatedObject.getId());
        assertEquals(updatedObject.getQuota(), jsonObject.get("quota"));
        assertEquals(updatedObject.getGrossAmount(), jsonObject.get("grossAmount"));
        assertEquals(updatedObject.getAuditingOperation().getId(), AuditOperationDTO.fromJSON(jsonObject.get("operation").toString()).getId());

        testChildObject = updatedObject;
    }

    @Test
    @Order(9)
    public void shouldReturnNotNullInstallmentFindAll() {
        assertNotNull(_childService.findAllInstallments(testObject.getId()));
    }

    @Test
    @Order(10)
    public void shouldReturnNotNullInstallmentFindById() {
        assertNotNull(_childService.getByAuditingOperationIdAndQuota(testObject.getId(), testChildObject.getQuota()));
    }

    @Test
    @Order(11)
    public void shouldReturnInstallmentDeletedWithSuccess() {
        _childService.delete(testObject.getId(), testChildObject.getQuota());
    }

    @Test
    @Order(12)
    public void shouldReturnAuditingOperationDeletedWithSuccess() {
        _service.delete(testObject.getId());
    }

    private JSONObject setObjectToCreate() throws JSONException {
        JSONObject map = new JSONObject();
        JSONObject mapAux = new JSONObject();

        map.put("id", JSONObject.NULL);
        map.put("date", "2021-04-02T00:47:15Z");
        map.put("authorizationId", 1212);
        map.put("pointOfSaleId", JSONObject.NULL);
        map.put("transactionId", "");
        map.put("authorizationCode", "987D54");
        map.put("transactionStatus", "APPROVED");
        map.put("numberOfInstallments", 1);
        map.put("grossAmount", 100.0);
        map.put("transactionInformation", "123456******3456");
        map.put("rebateInformation", "1234-ABC-56789");

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Mini 5th Av.");

        map.put("merchant", mapAux);

        mapAux = new JSONObject();
        mapAux.put("id", 1);
        mapAux.put("name", "G-Force Co.");
        mapAux.put("code", FinancialInstitutionCode.GFORCE);
        map.put("financialInstitution", mapAux);

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Debit Card");
        map.put("financialService", mapAux);

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Master");
        map.put("serviceLabel", mapAux);

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Platinum");
        map.put("cardType", mapAux);

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Chip");
        map.put("modality", mapAux);

        map.put("installments", "[]");

        return map;
    }

    private JSONObject setObjectToUpdate() throws JSONException {
        JSONObject map = new JSONObject();
        JSONObject mapAux = new JSONObject();

        map.put("id", testObject.getId());
        map.put("date", "2021-04-02T00:47:15Z");
        map.put("authorizationId", 1212);
        map.put("pointOfSaleId", JSONObject.NULL);
        map.put("transactionId", "");
        map.put("authorizationCode", "987D54");
        map.put("transactionStatus", "APPROVED");
        map.put("numberOfInstallments", 1);
        map.put("grossAmount", 100.0);
        map.put("transactionInformation", "123456******3456");
        map.put("rebateInformation", "1234-ABC-56789");

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Mini 5th Av.");
        map.put("merchant", mapAux.toString());

        mapAux = new JSONObject();
        mapAux.put("id", 1);
        mapAux.put("name", "G-Force Co.");
        mapAux.put("code", FinancialInstitutionCode.GFORCE);
        map.put("financialInstitution", mapAux.toString());

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Debit Card");
        map.put("financialService", mapAux.toString());

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Master");
        map.put("serviceLabel", mapAux.toString());

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Platinum");
        map.put("cardType", mapAux.toString());

        mapAux = new JSONObject();
        mapAux.put("id", 2);
        mapAux.put("name", "Chip");
        map.put("modality", mapAux.toString());

        map.put("installments", JSONObject.NULL);

        return map;
    }

    private JSONObject setObjectChildToCreate() throws JSONException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        JSONObject map = new JSONObject();

        map.put("id", JSONObject.NULL);
        map.put("quota", 1);
        map.put("grossAmount", 95.0);
        map.put("operation", mapper.writeValueAsString(testObject));

        return map;
    }

    private JSONObject setObjectChildToUpdate() throws JSONException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("quota", 1);
        map.put("grossAmount", 100.0);
        map.put("operation", mapper.writeValueAsString(testObject));
        return map;
    }

}