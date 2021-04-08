package com.diegorbj.reconciliation.it;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
class AuditOperationResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void contextLoad() {
        assertNotNull(mockMvc);
    }

    @Test
    @Order(2)
    public void shouldReturnInsertSourceTransactions() throws Exception {
        JSONObject mapToCreate = setObjectToCreate();
        this.mockMvc.perform(put("/reconciliation/v1/operations/audits").contentType(MediaType.APPLICATION_JSON)
                .content(mapToCreate.toString())).andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldReturnUpdateSourceTransactions() throws Exception {
        JSONObject mapToUpdate = setObjectToUpdate();
        this.mockMvc.perform(put("/reconciliation/v1/operations/audits/4").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToUpdate.toString())).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void shouldReturnFindByIdSourceTransactions() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/operations/audits/4")).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void shouldReturnFindAllSourceTransactions() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/operations/audits")).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void shouldReturnInsertInstallments() throws Exception {
        JSONObject mapToCreate = setObjectChildToCreate();
        this.mockMvc.perform(put("/reconciliation/v1/operations/audits/4/installments").contentType(MediaType.APPLICATION_JSON)
                .content(mapToCreate.toString())).andExpect(status().isCreated());
    }

    @Test
    @Order(7)
    public void shouldReturnUpdateinstallments() throws Exception {
        JSONObject mapToUpdate = setObjectChildToUpdate();
        this.mockMvc.perform(put("/reconciliation/v1/operations/audits/4/installments/1").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToUpdate.toString())).andExpect(status().isOk());
    }

    @Test
    @Order(9)
    public void shouldReturnFindByQuotaInstallments() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/operations/audits/4/installments/1")).andExpect(status().isOk());
    }

    @Test
    @Order(8)
    public void shouldReturnFindAllInstallments() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/operations/audits/4/installments")).andExpect(status().isOk());
    }

    @Test
    @Order(10)
    public void shouldReturnRemoveByQuotaInstallments() throws Exception {
        this.mockMvc.perform(delete("/reconciliation/v1/operations/audits/4/installments/1")).andExpect(status().isNoContent());
    }

    @Test
    @Order(11)
    public void shouldReturnRemoveByIdSourceTransactions() throws Exception {
        this.mockMvc.perform(delete("/reconciliation/v1/operations/audits/4")).andExpect(status().isNoContent());
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
        map.put("grossAmount", 250.0);
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

        map.put("id", 4L);
        map.put("date", "2021-04-02T00:47:15Z");
        map.put("authorizationId", 1212);
        map.put("pointOfSaleId", JSONObject.NULL);
        map.put("transactionId", "");
        map.put("authorizationCode", "987D54");
        map.put("transactionStatus", "APPROVED");
        map.put("numberOfInstallments", 1);
        map.put("grossAmount", 250.0);
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

        map.put("installments", JSONObject.NULL);

        return map;
    }

    private JSONObject setObjectChildToCreate() throws JSONException {
        JSONObject map = new JSONObject();

        map.put("id", JSONObject.NULL);
        map.put("quota", 1);
        map.put("grossAmount", 95.0);
        map.put("operation", JSONObject.NULL);

        return map;
    }

    private JSONObject setObjectChildToUpdate() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("quota", 1);
        map.put("grossAmount", 250.0);
        map.put("operation", JSONObject.NULL);
        return map;
    }

}