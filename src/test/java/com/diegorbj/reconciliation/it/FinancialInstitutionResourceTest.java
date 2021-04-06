package com.diegorbj.reconciliation.it;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
class FinancialInstitutionResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void contextLoad() {
        assertNotNull(mockMvc);
    }

    @Test
    @Order(2)
    public void shouldReturnInsertFinancialInstitution() throws Exception {
        JSONObject mapToCreate = setObjectToCreate();
        this.mockMvc.perform(post("/reconciliation/v1/financialinstitutions").contentType(MediaType.APPLICATION_JSON)
                .content(mapToCreate.toString())).andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldReturnUpdateFinancialInstitution() throws Exception {
        JSONObject mapToUpdate = setObjectToUpdate();
        this.mockMvc.perform(put("/reconciliation/v1/financialinstitutions/3").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToUpdate.toString())).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void shouldReturnFindByIdFinancialInstitution() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/financialinstitutions/3")).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void shouldReturnFindAllFinancialInstitutions() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/financialinstitutions")).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void shouldReturnRemoveByIdFinancialInstitution() throws Exception {
        this.mockMvc.perform(delete("/reconciliation/v1/financialinstitutions/3")).andExpect(status().isNoContent());
    }

    private JSONObject setObjectToCreate() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("name", "RX ltd.");
        map.put("code", FinancialInstitutionCode.RX);
        return map;
    }

    private JSONObject setObjectToUpdate() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("id", 3L);
        map.put("name", "RX ltd. Co.");
        map.put("code", FinancialInstitutionCode.RX);
        return map;
    }

}