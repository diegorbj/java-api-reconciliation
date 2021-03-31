package com.diegorbj.reconciliation.it;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

class CardTypeResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void contextLoad() {
        assertNotNull(mockMvc);
    }

    @Test
    @Order(2)
    public void shouldReturnInsertCardType() throws Exception {
        JSONObject mapToCreate = setObjectToCreate();
        this.mockMvc.perform(post("/reconciliation/v1/cardtypes").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mapToCreate))).andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldReturnUpdateCardType() throws Exception {
        JSONObject mapToUpdate = setObjectToUpdate();
        this.mockMvc.perform(put("/reconciliation/v1/cardtypes/1").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(mapToUpdate))).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void shouldReturnFindByIdCardType() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/cardtypes/1")).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void shouldReturnFindAllCardTypes() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/cardtypes")).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void shouldReturnRemoveByIdCardType() throws Exception {
        this.mockMvc.perform(delete("/reconciliation/v1/cardtypes/1")).andExpect(status().isNoContent());
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", 1L);
        map.put("name", "Black");
        return map;
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", 1L);
        map.put("name", "Platinum");
        return map;
    }

}