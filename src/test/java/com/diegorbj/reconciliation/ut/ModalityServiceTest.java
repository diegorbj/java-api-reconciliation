package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.services.dto.ModalityDTO;
import com.diegorbj.reconciliation.services.ModalityService;
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
public class ModalityServiceTest {

    @Autowired
    private ModalityService _service;

    private ModalityDTO testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullModalityService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    public void shouldReturnModalityCreatedWithSuccess() throws JSONException {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(ModalityDTO.fromJSON(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    public void shouldReturnModalityUpdatedWithSuccess() throws JSONException {
        JSONObject jsonObject = setObjectToUpdate();

        ModalityDTO updatedObject = ModalityDTO.fromJSON(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertEquals(updatedObject.getId(), jsonObject.get("id"));
        assertEquals(updatedObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
    }

    @Test
    @Order(4)
    public void shouldReturnNotNullModalityFindAll() {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    public void shouldReturnNotNullModalityFindById() {
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    public void shouldReturnModalityDeletedWithSuccess() {
        _service.delete(testObject.getId());
    }

    private JSONObject setObjectToCreate() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("name", "Fingerprint");
        return map;
    }

    private JSONObject setObjectToUpdate() throws JSONException {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "Biometric - Fingerprint");
        return map;
    }

}
