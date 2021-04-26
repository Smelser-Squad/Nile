package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.*;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;


    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    @Order(1)
    void getAllCategories() throws Exception {
        this.mockMvc.perform(get("/api/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()));
    }

    @Test
    @Order(2)
    void addCategory() throws Exception {
        Category category = new Category();
        category.setName("Electronic");


        this.mockMvc.perform(post("/api/categories")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Electronic"));
    }


    @Test
    @Order(3)
    void getCategoryByInvalidCategoryId() throws Exception {
        this.mockMvc.perform(get("/api/categories/{categoryId}", Integer.MIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Category with that id does not exist",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(4)
    void getCategoryByCategoryId() throws Exception {
        this.mockMvc.perform(get("/api/categories/{categoryId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").exists());
    }

    @Test
    @Order(5)
    void updateCategory() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/api/categories/{categoryId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Category category = this.mapper.readValue(result.getResponse().getContentAsString(), Category.class);
        String name = category.getName();
        category.setName("Clothing");

        this.mockMvc.perform(put("/api/categories")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(category.getCategoryId()))
                .andExpect(jsonPath("$.name").value("Clothing"));

        category.setName(name);

        this.mockMvc.perform(put("/api/categories")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId").value(category.getCategoryId()))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Order(6)
    @Test
    void deletingACategoryReturnsExpectedMessageAndStatusCode() throws Exception {
        this.mockMvc.perform(delete("/api/categories/{categoryId}", 1))
                .andExpect(status().isNoContent())
                .andExpect(result -> assertEquals("Category 1 deleted",
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
