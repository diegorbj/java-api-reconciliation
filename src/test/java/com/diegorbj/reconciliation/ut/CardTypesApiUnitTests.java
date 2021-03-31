package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.CardTypeService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class CardTypesApiUnitTests {

    @Autowired
    private CardTypeService _service;

    @Test
    @Order(1)
    public void shouldReturnNotNullCardTypeService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnCardTypeCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Black");

        CardType obj = _service.insert(CardTypeService.toCardType(jsonObject));

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnCardTypeUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Platinum");

        CardType obj = CardTypeService.toCardType(jsonObject);
        obj = _service.update(obj.getId(), obj);

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(4)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullCardTypeFindAll() throws Exception {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullCardTypeFindById() throws Exception {
        assertNotNull(_service.findById(1L));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnCardTypeDeletedWithSuccess() throws Exception {
        _service.delete(1L);
        assertNull(_service.findById(1L));
    }

}
