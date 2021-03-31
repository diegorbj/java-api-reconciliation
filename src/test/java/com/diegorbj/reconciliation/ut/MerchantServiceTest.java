package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.Merchant;
import com.diegorbj.reconciliation.services.MerchantService;
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
public class MerchantServiceTest {

    @Autowired
    private MerchantService _service;

    private Merchant testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullMerchantService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnMerchantCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(MerchantService.toMerchant(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnMerchantUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToUpdate();

        Merchant updatedObject = MerchantService.toMerchant(jsonObject);
        updatedObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(updatedObject);
        assertEquals(updatedObject.getId(), jsonObject.get("id"));
        assertEquals(updatedObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
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
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnMerchantDeletedWithSuccess() throws Exception {
        _service.delete(testObject.getId());
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", 0L); //TODO change to JSONObject.NULL
        map.put("name", "Magazine 25th Av.");
        return map;
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "Mini 5th Av.");
        return map;
    }

}
