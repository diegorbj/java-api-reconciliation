package com.diegorbj.reconciliation.ut;

import com.diegorbj.reconciliation.services.CardTypeService;
import com.diegorbj.reconciliation.services.dto.CardTypeDTO;
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
public class CardTypeServiceTest {

    @Autowired
    private CardTypeService _service;

    private CardTypeDTO testObject;

    @Test
    @Order(1)
    public void shouldReturnNotNullCardTypeService() {
        assertNotNull(_service);
    }

    @Test
    @Order(2)
    public void shouldReturnCardTypeCreatedWithSuccess() {
        JSONObject jsonObject = setObjectToCreate();
        testObject = _service.insert(CardTypeDTO.fromJSON(jsonObject));

        assertNotNull(testObject);
        assertEquals(testObject.getName(), jsonObject.get("name"));
    }

    @Test
    @Order(3)
    public void shouldReturnCardTypeUpdatedWithSuccess() {
        JSONObject jsonObject = setObjectToUpdate();

        CardTypeDTO updatedObject = CardTypeDTO.fromJSON(jsonObject);
        testObject = _service.update(testObject.getId(), updatedObject);

        assertNotNull(testObject);
        assertEquals(testObject.getId(), jsonObject.get("id"));
        assertEquals(testObject.getName(), jsonObject.get("name"));

        testObject = updatedObject;
    }

    @Test
    @Order(4)
    public void shouldReturnNotNullCardTypeFindAll() {
        assertNotNull(_service.findAll());
    }

    @Test
    @Order(5)
    public void shouldReturnNotNullCardTypeFindById() {
        assertNotNull(_service.findById(testObject.getId()));
    }

    @Test
    @Order(6)
    public void shouldReturnCardTypeDeletedWithSuccess() {
        _service.delete(testObject.getId());
    }

    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", JSONObject.NULL);
        map.put("name", "Black");
        return map;
    }

    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", testObject.getId());
        map.put("name", "Platinum");
        return map;
    }

}
