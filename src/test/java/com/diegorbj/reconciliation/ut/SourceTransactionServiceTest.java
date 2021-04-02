package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.enums.TransactionStatus;
import com.diegorbj.reconciliation.services.InstallmentService;
import com.diegorbj.reconciliation.services.dto.*;
import com.diegorbj.reconciliation.services.SourceTransactionService;
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
class SourceTransactionServiceTest {

    @Autowired
    private SourceTransactionService _service;

    @Autowired
    private InstallmentService _childService;

    private SourceTransactionDTO testObject;

    private InstallmentDTO testChildObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullSourceTransactionService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    public void shouldReturnNotNullInstallmentService() {
        assertNotNull(_childService);
    }

    @Test
    @Order(3)
    public void shouldReturnSourceTransactionCreatedWithSuccess() {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(SourceTransactionDTO.fromJSON(jsonObject));

        assertNotNull(testObject);
        assertNotNull(testObject.getId());
        assertEquals(testObject.getDate(), Instant.parse(jsonObject.get("date").toString()));
        assertEquals(testObject.getUniqueSequentialNumber(), Long.parseLong(jsonObject.get("uniqueSequentialNumber").toString()));
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
    public void shouldReturnSourceTransactionUpdatedWithSuccess() {
        JSONObject jsonObject = setObjectToUpdate();
        System.out.println("whatever::" + jsonObject.toString());
        SourceTransactionDTO updatedObject = SourceTransactionDTO.fromJSON(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertNotNull(updatedObject.getId());
        assertEquals(updatedObject.getDate(), Instant.parse(jsonObject.get("date").toString()));
        assertEquals(updatedObject.getUniqueSequentialNumber(), Long.parseLong(jsonObject.get("uniqueSequentialNumber").toString()));
        assertEquals(updatedObject.getTransactionId(), jsonObject.get("transactionId").toString());
        assertEquals(updatedObject.getAuthorizationCode(), jsonObject.get("authorizationCode").toString());
        assertEquals(updatedObject.getTransactionStatus(), TransactionStatus.valueOf(jsonObject.get("transactionStatus").toString()));
        assertEquals(updatedObject.getNumberOfInstallments(), Integer.parseInt(jsonObject.get("numberOfInstallments").toString()));
        assertEquals(updatedObject.getGrossAmount(), Double.parseDouble(jsonObject.get("grossAmount").toString()));
        assertEquals(updatedObject.getTransactionInformation(), jsonObject.get("transactionInformation"));
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
    public void shouldReturnNotNullSourceTransactionFindAll() {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(6)
    public void shouldReturnNotNullSourceTransactionFindById() {
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(7)
    public void shouldReturnInstallmentCreatedWithSuccess() {
        JSONObject jsonObject = setObjectChildToCreate();

        testChildObject = _childService.insert(InstallmentDTO.fromJSON(jsonObject));

        assertNotNull(testChildObject);
        assertNotNull(testChildObject.getId());
        assertEquals(testChildObject.getQuota(), Integer.parseInt(jsonObject.get("quota").toString()));
        assertEquals(testChildObject.getGrossAmount(), Double.parseDouble(jsonObject.get("grossAmount").toString()));
        assertEquals(testChildObject.getSourceTransaction(), SourceTransactionDTO.fromJSON(jsonObject.get("sourceTransaction").toString()));
    }

    @Test
    @Order(8)
    public void shouldReturnInstallmentUpdatedWithSuccess() {
        JSONObject jsonObject = setObjectChildToUpdate();

        InstallmentDTO updatedObject = InstallmentDTO.fromJSON(jsonObject);
        updatedObject = _childService.update(updatedObject);

        assertNotNull(updatedObject);
        assertNotNull(updatedObject.getId());
        assertEquals(updatedObject.getQuota(), jsonObject.get("quota"));
        assertEquals(updatedObject.getGrossAmount(), jsonObject.get("grossAmount"));
        assertEquals(updatedObject.getSourceTransaction().getId(), SourceTransactionDTO.fromJSON(jsonObject.get("sourceTransaction").toString()).getId());

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
        assertNotNull(_childService.findByQuota(testObject.getId(), testChildObject.getQuota()));
    }

    @Test
    @Order(11)
    public void shouldReturnInstallmentDeletedWithSuccess() {
        _childService.delete(testObject.getId(), testChildObject.getQuota());
    }

    @Test
    @Order(12)
    public void shouldReturnSourceTransactionDeletedWithSuccess() {
        _service.delete(testObject.getId());
    }

    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        JSONObject mapAux = new JSONObject();

        map.put("id", JSONObject.NULL);
        map.put("date", "2021-04-02T00:47:15Z");
        map.put("uniqueSequentialNumber", 1212);
        map.put("transactionId", "");
        map.put("authorizationCode", "987D54");
        map.put("transactionStatus", "APPROVED");
        map.put("numberOfInstallments", 1);
        map.put("grossAmount", 100.0);
        map.put("transactionInformation", "123456******3456");

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Mini 5th Av.");

        map.put("merchant", mapAux);

        mapAux.clear();
        mapAux.put("id", 1);
        mapAux.put("name", "Mint Co.");
        map.put("financialInstitution", mapAux);

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Debit Card");
        map.put("financialService", mapAux);

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Master");
        map.put("serviceLabel", mapAux);

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Platinum");
        map.put("cardType", mapAux);

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Chip");
        map.put("modality", mapAux);

        map.put("installments", "[]");

        return map;
    }

    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        JSONObject mapAux = new JSONObject();

        map.put("id", testObject.getId());
        map.put("date", "2021-04-02T00:47:15Z");
        map.put("uniqueSequentialNumber", 1212);
        map.put("transactionId", "");
        map.put("authorizationCode", "987D54");
        map.put("transactionStatus", "APPROVED");
        map.put("numberOfInstallments", 1);
        map.put("grossAmount", 100.0);
        map.put("transactionInformation", "123456******3456");

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Mini 5th Av.");
        map.put("merchant", mapAux.toString());

        mapAux.clear();
        mapAux.put("id", 1);
        mapAux.put("name", "Mint Co.");
        map.put("financialInstitution", mapAux.toString());

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Debit Card");
        map.put("financialService", mapAux.toString());

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Master");
        map.put("serviceLabel", mapAux.toString());

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Platinum");
        map.put("cardType", mapAux.toString());

        mapAux.clear();
        mapAux.put("id", 2);
        mapAux.put("name", "Chip");
        map.put("modality", mapAux.toString());

        map.put("installments", JSONObject.NULL);

        return map;
    }

    private JSONObject setObjectChildToCreate() {
        JSONObject map = new JSONObject();

        map.put("id", JSONObject.NULL);
        map.put("quota", 1);
        map.put("grossAmount", 95.0);
        map.put("sourceTransaction", new JSONObject(testObject));

        return map;
    }

    private JSONObject setObjectChildToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("quota", 1);
        map.put("grossAmount", 100.0);
        map.put("sourceTransaction", new JSONObject(testObject));
        return map;
    }

}