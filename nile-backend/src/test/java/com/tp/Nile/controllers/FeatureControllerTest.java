package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Feature;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FeatureControllerTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    @Order(1)
    void getAllFeatures() throws Exception {
        this.mockMvc.perform(get("/api/features"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("[]",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(2)
    void addFeature() throws Exception {
        Feature feature = new Feature();
        feature.setName("Bluetooth");


        this.mockMvc.perform(post("/api/features")
                .content(asJsonString(feature))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Bluetooth"));
    }


    @Test
    @Order(3)
    void getFeatureByInvalidFeatureId() throws Exception {
        this.mockMvc.perform(get("/api/features/{featureId}", Integer.MIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Feature with that id does not exist",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(4)
    void getFeatureByFeatureId() throws Exception {
        this.mockMvc.perform(get("/api/features/{featureId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.featureId").exists())
                .andExpect(jsonPath("$.name").value("Bluetooth"));
    }

    @Test
    @Order(6)
    void updateFeature() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/features/{featureId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Feature feature = this.mapper.readValue(result.getResponse().getContentAsString(), Feature.class);
        feature.setName("Wireless");

        this.mockMvc.perform(put("/api/features")
                .content(asJsonString(feature))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.featureId").value(feature.getFeatureId()))
                .andExpect(jsonPath("$.name").value("Wireless"));
    }

    @Order(7)
    @Test
    void deletingAFeatureReturnsExpectedMessageAndStatusCode() throws Exception {
        this.mockMvc.perform(delete("/api/features/{featureId}", 1))
                .andExpect(status().isNoContent())
                .andExpect(result -> assertEquals("Feature 1 deleted",
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
