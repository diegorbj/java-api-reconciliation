package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.services.dto.FinancialInstitutionDTO;
import com.diegorbj.reconciliation.resources.FinancialInstitutionResource;
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
    public void shouldReturnFinancialInstitutionCreatedWithSuccess() {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(FinancialInstitutionResource.toFinancialInstitution(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    public void shouldReturnFinancialInstitutionUpdatedWithSuccess() {
        JSONObject jsonObject = setObjectToUpdate();

        FinancialInstitutionDTO updatedObject = FinancialInstitutionResource.toFinancialInstitution(jsonObject);
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

    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("name", "Mint Co.");
        return map;
    }

    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "PayDay Inc.");
        return map;
    }

}
