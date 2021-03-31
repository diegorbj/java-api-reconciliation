package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.domain.CardType;
import com.diegorbj.reconciliation.services.CardTypeService;
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
public class CardTypeServiceTest {

    @Autowired
    private CardTypeService _service;

    private CardType testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullCardTypeService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    @SuppressWarnings("unchecked")
    public void shouldReturnCardTypeCreatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToCreate();

        testObject = _service.insert(CardTypeService.toCardType(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    @SuppressWarnings("unchecked")
    public void shouldReturnCardTypeUpdatedWithSuccess() throws Exception {
        JSONObject jsonObject = setObjectToUpdate();

        CardType updatedObject = CardTypeService.toCardType(jsonObject);
        testObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(testObject);
        assertEquals(testObject.getId(), jsonObject.get("id"));
        assertEquals(testObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
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
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    @SuppressWarnings("unchecked")
    public void shouldReturnCardTypeDeletedWithSuccess() throws Exception {
        _service.delete(testObject.getId());
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", 0L); //TODO change to JSONObject.NULL
        map.put("name", "Black");
        return map;
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "Platinum");
        return map;
    }

}
