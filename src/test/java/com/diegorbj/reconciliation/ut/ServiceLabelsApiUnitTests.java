package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.services.ServiceLabelService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceLabelsApiUnitTests {

    @Autowired
    private ServiceLabelService _service;

    @Test
    @Order(1)
    public void shouldReturnNotNullServiceLabelService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnServiceLabelCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Black");

        ServiceLabel obj = _service.insert(ServiceLabelService.toServiceLabel(jsonObject));

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnServiceLabelUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 1);
        jsonObject.put("name", "Platinum");

        ServiceLabel obj = ServiceLabelService.toServiceLabel(jsonObject);
        obj = _service.update(obj.getId(), obj);

        assertNotNull(obj);
        assertEquals(obj.getId().longValue(), jsonObject.get("id"));
        assertEquals(obj.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(4)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullServiceLabelFindAll() throws Exception {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    @SuppressWarnings("unchecked")
    public void shouldReturnNotNullServiceLabelFindById() throws Exception {
        assertNotNull(_service.findById(1L));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnServiceLabelDeletedWithSuccess() throws Exception {
        _service.delete(1L);
        assertNull(_service.findById(1L));
    }

}
