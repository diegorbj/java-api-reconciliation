package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.FinancialService;
import com.diegorbj.reconciliation.services.FinancialServiceService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class FinancialServicesApiUnitTests {

    @Autowired
    private FinancialServiceService _service;

    @Test
    @Order(1)
    public void shouldReturnNotNullFinancialServiceService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialServiceCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Credit Card");

        FinancialService obj = _service.insert(FinancialServiceService.toFinancialService(jsonObject));

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialServiceUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Debit Card");

        FinancialService obj = FinancialServiceService.toFinancialService(jsonObject);
        obj = _service.update(obj.getId(), obj);

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(4)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullFinancialServiceFindAll() throws Exception {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullFinancialServiceFindById() throws Exception {
        assertNotNull(_service.findById(1L));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnFinancialServiceDeletedWithSuccess() throws Exception {
        _service.delete(1L);
        assertNull(_service.findById(1L));
    }

}
