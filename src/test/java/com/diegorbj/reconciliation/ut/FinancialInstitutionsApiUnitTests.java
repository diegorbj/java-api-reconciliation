package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.FinancialInstitution;
import com.diegorbj.reconciliation.services.FinancialInstitutionService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class FinancialInstitutionsApiUnitTests {

    @Autowired
    private FinancialInstitutionService _service;

    @Test
    @Order(1)
    public void shouldReturnNotNullFinancialInstitutionService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialInstitutionCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Mint Co.");

        FinancialInstitution obj = _service.insert(FinancialInstitutionService.toFinancialInstitution(jsonObject));

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialInstitutionUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "PayDay Inc.");

        FinancialInstitution obj = FinancialInstitutionService.toFinancialInstitution(jsonObject);
        obj = _service.update(obj.getId(), obj);

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
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
        assertNotNull(_service.findById(1L));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialInstitutionDeletedWithSuccess() throws Exception {
        _service.delete(1L);
        assertNull(_service.findById(1L));
    }

}
