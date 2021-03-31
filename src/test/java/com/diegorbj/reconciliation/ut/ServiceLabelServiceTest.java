package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.ServiceLabel;
import com.diegorbj.reconciliation.services.ServiceLabelService;
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
public class ServiceLabelServiceTest {

    @Autowired
    private ServiceLabelService _service;

    private ServiceLabel testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullServiceLabelService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnServiceLabelCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(ServiceLabelService.toServiceLabel(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnServiceLabelUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToUpdate();

        ServiceLabel updatedObject = ServiceLabelService.toServiceLabel(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertEquals(updatedObject.getId(), jsonObject.get("id"));
        assertEquals(updatedObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
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
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnServiceLabelDeletedWithSuccess() throws Exception {
        _service.delete(testObject.getId());
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", 0L); //TODO change to JSONObject.NULL
        map.put("name", "Visa");
        return map;
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "Master");
        return map;
    }

}
