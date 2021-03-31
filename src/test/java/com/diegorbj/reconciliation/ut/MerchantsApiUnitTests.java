package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.MerchantService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class MerchantsApiUnitTests {

    @Autowired
    private MerchantService _service;

    @Test
    @Order(1)
    public void shouldReturnNotNullMerchantService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnMerchantCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Black");

        Merchant obj = _service.insert(MerchantService.toMerchant(jsonObject));

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnMerchantUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Platinum");

        Merchant obj = MerchantService.toMerchant(jsonObject);
        obj = _service.update(obj.getId(), obj);

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(4)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullMerchantFindAll() throws Exception {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullMerchantFindById() throws Exception {
        assertNotNull(_service.findById(1L));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnMerchantDeletedWithSuccess() throws Exception {
        _service.delete(1L);
        assertNull(_service.findById(1L));
    }

}
