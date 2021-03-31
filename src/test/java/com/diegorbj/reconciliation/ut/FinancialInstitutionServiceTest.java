package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.FinancialInstitutionService;
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
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class FinancialInstitutionServiceTest {

    @Autowired
    private FinancialInstitutionService _service;

    private FinancialInstitution testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullFinancialInstitutionService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialInstitutionCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(FinancialInstitutionService.toFinancialInstitution(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialInstitutionUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToUpdate();

        FinancialInstitution updatedObject = FinancialInstitutionService.toFinancialInstitution(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertEquals(updatedObject.getId(), jsonObject.get("id"));
        assertEquals(updatedObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
    }

    @Test
    @Order(4)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullFinancialInstitutionFindAll() throws Exception {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullFinancialInstitutionFindById() throws Exception {
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialInstitutionDeletedWithSuccess() throws Exception {
        _service.delete(testObject.getId());
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", 0L); //TODO change to JSONObject.NULL
        map.put("name", "Mint Co.");
        return map;
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "PayDay Inc.");
        return map;
    }

}
