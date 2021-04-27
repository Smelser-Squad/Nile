package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
//@ActiveProfiles("test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {

//    @Autowired
//    MockMvc mockMvc;
//
//    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//    @Test
//    @Order(1)
//    void getAllProducts() throws Exception {
//        this.mockMvc.perform(get("/api/products"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(result -> assertEquals("[]",
//                        result.getResponse().getContentAsString()));
//    }
//
//    @Test
//    @Order(2)
//    void addProduct() throws Exception {
//        Product product = new Product();
//        product.setBrand("Nike");
//        product.setDescription("Athletic shoes");
//        product.setName("Air Jordan");
//        product.setPrice(BigDecimal.valueOf(249.99));
//        product.setPrimeEligible(false);
//        product.setStock(2);
//
////        Category category = new Category();
////        category.setName("Sports");
////        product.setCategory(category);
////
////        Type type = new Type();
////        type.setTypeName("Sample Type");
////        product.setType(type);
////
////        Vendor vendor = new Vendor();
////        vendor.setName("Nike Store");
////        product.setVendor(vendor);
//
//        this.mockMvc.perform(post("/api/products")
//                .content(asJsonString(product))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.productId").exists())
//                .andExpect(jsonPath("$.name").value("Air Jordan"))
//                .andExpect(jsonPath("$.description").value("Athletic shoes"))
//                .andExpect(jsonPath("$.brand").value("Nike"))
//                .andExpect(jsonPath("$.price").value(249.99))
//                .andExpect(jsonPath("$.primeEligible").value(false))
//                .andExpect(jsonPath("$.stock").value(2));
//    }
//
//
//    @Test
//    @Order(3)
//    void getProductByInvalidProductId() throws Exception {
//        this.mockMvc.perform(get("/api/products/{productId}", Integer.MIN_VALUE)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(result -> assertEquals("Product with that id does not exist",
//                        result.getResponse().getContentAsString()));
//    }
//
//    @Test
//    @Order(4)
//    void getProductByProductId() throws Exception {
//        this.mockMvc.perform(get("/api/products/{productId}", 1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productId").exists())
//                .andExpect(jsonPath("$.name").value("Air Jordan"))
//                .andExpect(jsonPath("$.brand").value("Nike"))
//                .andExpect(jsonPath("$.description").value("Athletic shoes"))
//                .andExpect(jsonPath("$.price").value(249.99))
//                .andExpect(jsonPath("$.primeEligible").value(false))
//                .andExpect(jsonPath("$.stock").value(2));
//    }
//
//    @Test
//    @Order(5)
//    void getProductsByBrand() throws Exception {
//        this.mockMvc.perform(get("/api/products/brand/{brand}", "Nike")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].price").value(249.99))
//                .andExpect(jsonPath("$.[0].productId").exists())
//                .andExpect(jsonPath("$.[0].description").value("Athletic shoes"))
//                .andExpect(jsonPath("$.[0].brand").value("Nike"))
//                .andExpect(jsonPath("$.[0].name").value("Air Jordan"))
//                .andExpect(jsonPath("$.[0].primeEligible").value(false))
//                .andExpect(jsonPath("$.[0].stock").value(2));
//    }
//
////    @Test
////    @Order(6)
////    void getProductsByCategory() throws Exception {
////        this.mockMvc.perform(get("/api/products/category/{category}", "Sports")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.[0].price").value(249.99))
////                .andExpect(jsonPath("$.[0].productId").exists())
////                .andExpect(jsonPath("$.[0].description").value("Athletic shoes"))
////                .andExpect(jsonPath("$.[0].brand").value("Nike"))
////                .andExpect(jsonPath("$.[0].name").value("Air Jordan"))
////                .andExpect(jsonPath("$.[0].primeEligible").value(false))
////                .andExpect(jsonPath("$.[0].stock").value(2));
////    }
////
////    @Test
////    @Order(7)
////    void getProductsByVendor() throws Exception {
////        this.mockMvc.perform(get("/api/products/vendor/{vendor}", "Nike Store")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.[0].price").value(249.99))
////                .andExpect(jsonPath("$.[0].productId").exists())
////                .andExpect(jsonPath("$.[0].description").value("Athletic shoes"))
////                .andExpect(jsonPath("$.[0].brand").value("Nike"))
////                .andExpect(jsonPath("$.[0].name").value("Air Jordan"))
////                .andExpect(jsonPath("$.[0].primeEligible").value(false))
////                .andExpect(jsonPath("$.[0].stock").value(2));
////    }
////
////    @Test
////    @Order(8)
////    void getProductsByType() throws Exception {
////        this.mockMvc.perform(get("/api/products/type/{type}", "Sample Type")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.[0].price").value(249.99))
////                .andExpect(jsonPath("$.[0].productId").exists())
////                .andExpect(jsonPath("$.[0].description").value("Athletic shoes"))
////                .andExpect(jsonPath("$.[0].brand").value("Nike"))
////                .andExpect(jsonPath("$.[0].name").value("Air Jordan"))
////                .andExpect(jsonPath("$.[0].primeEligible").value(false))
////                .andExpect(jsonPath("$.[0].stock").value(2));
////    }
//
//    @Test
//    @Order(9)
//    void updateProduct() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/api/products/{productId}", 1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        Product product = this.mapper.readValue(result.getResponse().getContentAsString(), Product.class);
//        product.setName("Predator");
//        product.setDescription("Soccer cleats");
//        product.setPrice(BigDecimal.valueOf(159.99));
//        product.setPrimeEligible(true);
//        product.setStock(3);
//        product.setBrand("Adidas");
//
//        this.mockMvc.perform(put("/api/products")
//                .content(asJsonString(product))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.productId").value(product.getProductId()))
//                .andExpect(jsonPath("$.name").value("Predator"))
//                .andExpect(jsonPath("$.brand").value("Adidas"))
//                .andExpect(jsonPath("$.description").value("Soccer cleats"))
//                .andExpect(jsonPath("$.price").value(159.99))
//                .andExpect(jsonPath("$.primeEligible").value(true))
//                .andExpect(jsonPath("$.stock").value(3));
//    }
//
//    @Order(10)
//    @Test
//    void deletingAProductReturnsExpectedMessageAndStatusCode() throws Exception {
//        this.mockMvc.perform(delete("/api/products/{productId}", 1))
//                .andExpect(status().isNoContent())
//                .andExpect(result -> assertEquals("Product 1 deleted",
//                        result.getResponse().getContentAsString()));
//    }
//
//    @Order(11)
//    @Test
//    void deletingAProductWithInvalidIdReturns404AndAppropriateResponse() throws Exception {
//        this.mockMvc.perform(delete("/api/products/{productId}", Integer.MIN_VALUE))
//                .andExpect(status().isNotFound())
//                .andExpect(result -> assertEquals("Product " + Integer.MIN_VALUE + " not found",
//                        result.getResponse().getContentAsString()));
//    }
//
//    static String asJsonString(final Object obj) {
//        try {
//            final ObjectMapper mapper = new ObjectMapper();
//            final String jsonContent = mapper.writeValueAsString(obj);
//            return jsonContent;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
