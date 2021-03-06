package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.enums.FinancialInstitutionCode;
import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
import com.diegorbj.reconciliation.services.FinancialInstitutionService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class FinancialInstitutionServiceTest {

    @Autowired
    private FinancialInstitutionService _service;

    private FinancialInstitutionDTO testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullFinancialInstitutionService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    public void shouldReturnFinancialInstitutionCreatedWithSuccess() throws JSONException {
        JSONObject jsonObject = setObjectToCreate();
        testObject = _service.insert(FinancialInstitutionDTO.fromJSON(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    public void shouldReturnFinancialInstitutionUpdatedWithSuccess() throws JSONException {
        JSONObject jsonObject = setObjectToUpdate();

        FinancialInstitutionDTO updatedObject = FinancialInstitutionDTO.fromJSON(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertEquals(updatedObject.getId(), jsonObject.get("id"));
        assertEquals(updatedObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
    }

    @Test
    @Order(4)
    public void shouldReturnNotNullFinancialInstitutionFindAll() {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    public void shouldReturnNotNullFinancialInstitutionFindById() {
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    public void shouldReturnFinancialInstitutionDeletedWithSuccess() {
        _service.delete(testObject.getId());
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
        map.put("id", testObject.getId());
        map.put("name", "RX ltd. Co.");
        map.put("code", FinancialInstitutionCode.RX);
        return map;
    }

}
