package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Vendor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendorControllerTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    @Order(1)
    void getAllVendors() throws Exception {
        this.mockMvc.perform(get("/api/vendors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("[]",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(2)
    void addVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName("Best Buy");


        this.mockMvc.perform(post("/api/vendors")
                .content(asJsonString(vendor))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Best Buy"));
    }


    @Test
    @Order(3)
    void getVendorByInvalidVendorId() throws Exception {
        this.mockMvc.perform(get("/api/vendors/{vendorId}", Integer.MIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Vendor with that id does not exist",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(4)
    void getVendorByVendorId() throws Exception {
        this.mockMvc.perform(get("/api/vendors/{vendorId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorId").exists())
                .andExpect(jsonPath("$.name").value("Best Buy"));
    }

    @Test
    @Order(6)
    void updateVendor() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/vendors/{vendorId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Vendor vendor = this.mapper.readValue(result.getResponse().getContentAsString(), Vendor.class);
        vendor.setName("RadioShack");

        this.mockMvc.perform(put("/api/vendors")
                .content(asJsonString(vendor))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorId").value(vendor.getVendorId()))
                .andExpect(jsonPath("$.name").value("RadioShack"));
    }

    @Order(7)
    @Test
    void deletingAVendorReturnsExpectedMessageAndStatusCode() throws Exception {
        this.mockMvc.perform(delete("/api/vendors/{vendorId}", 1))
                .andExpect(status().isNoContent())
                .andExpect(result -> assertEquals("Vendor 1 deleted",
                        result.getResponse().getContentAsString()));
    }

    static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
