package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.Type;
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
public class TypeControllerTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    @Order(1)
    void getAllTypes() throws Exception {
        this.mockMvc.perform(get("/api/types"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("[]",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(2)
    void addType() throws Exception {
        Type type = new Type();
        type.setTypeName("Smart Phone");


        this.mockMvc.perform(post("/api/types")
                .content(asJsonString(type))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.typeName").value("Smart Phone"));
    }


    @Test
    @Order(3)
    void getTypeByInvalidTypeId() throws Exception {
        this.mockMvc.perform(get("/api/types/{typeId}", Integer.MIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Type with that id does not exist",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(4)
    void getTypeByTypeId() throws Exception {
        this.mockMvc.perform(get("/api/types/{typeId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeId").exists())
                .andExpect(jsonPath("$.typeName").value("Smart Phone"));
    }

    @Test
    @Order(6)
    void updateType() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/types/{typeId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Type type = this.mapper.readValue(result.getResponse().getContentAsString(), Type.class);
        type.setTypeName("Cell Phone");

        this.mockMvc.perform(put("/api/types")
                .content(asJsonString(type))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.typeId").value(type.getTypeId()))
                .andExpect(jsonPath("$.typeName").value("Cell Phone"));
    }

    @Order(7)
    @Test
    void deletingATypeReturnsExpectedMessageAndStatusCode() throws Exception {
        this.mockMvc.perform(delete("/api/types/{typeId}", 1))
                .andExpect(status().isNoContent())
                .andExpect(result -> assertEquals("Type 1 deleted",
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
