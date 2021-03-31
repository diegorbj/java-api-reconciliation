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

class MerchantResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @org.junit.Test
    @Order(1)
    public void contextLoad() {
        assertNotNull(mockMvc);
    }

    @Test
    @Order(2)
    public void shouldReturnInsertMerchant() throws Exception {
        JSONObject mapToCreate = setObjectToCreate();
        this.mockMvc.perform(post("/reconciliation/v1/merchants").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mapToCreate))).andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void shouldReturnUpdateMerchant() throws Exception {
        JSONObject mapToUpdate = setObjectToUpdate();
        this.mockMvc.perform(put("/reconciliation/v1/merchants/1").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(mapToUpdate))).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void shouldReturnFindByIdMerchant() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/merchants/1")).andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void shouldReturnFindAllMerchants() throws Exception {
        this.mockMvc.perform(get("/reconciliation/v1/merchants")).andExpect(status().isOk());
    }

    @Test
    @Order(6)
    public void shouldReturnRemoveByIdMerchant() throws Exception {
        this.mockMvc.perform(delete("/reconciliation/v1/merchants/1")).andExpect(status().isNoContent());
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToCreate() {
        JSONObject map = new JSONObject();
        map.put("id", 1L);
        map.put("name", "Magazine 25th Av.");
        return map;
    }

    @SuppressWarnings("unchecked")
    private JSONObject setObjectToUpdate() {
        JSONObject map = new JSONObject();
        map.put("id", 1L);
        map.put("name", "Mini 5th Av.");
        return map;
    }

}