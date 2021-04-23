package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.User;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

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
public class CartControllerTest {

    @Autowired
    MockMvc mockMvc;


    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    @Order(1)
    void getAllCarts() throws Exception {
        this.mockMvc.perform(get("/api/carts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()));
    }

//    @Test
//    @Order(2)
//    void addCart() throws Exception {
//        Cart cart = new Cart();
//        User user = new User();
//        user.setUsername("username");
//        user.setPassword("password");
//        user.setRole("admin");
//        user.setEnabled(true);
//        cart.setUser(user);
//
//        LocalDate date = LocalDate.of(2021, 4, 20);
//        cart.setPurchaseDate(date);
//        cart.setStatus("Saved");
//        cart.setCartProducts(new ArrayList<>());
//
//
//        this.mockMvc.perform(post("/api/carts")
//                .content(asJsonString(cart))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.user.username").value("username"))
//                .andExpect(jsonPath("$.user.password").value("password"))
//                .andExpect(jsonPath("$.user.role").value("admin"))
//                .andExpect(jsonPath("$.user.enabled").value(true))
//                .andExpect(jsonPath("$.purchaseDate").value("2021-04-21"))
//                .andExpect(jsonPath("$.status").value("Saved"));
//
//    }
//
//
//    @Test
//    @Order(3)
//    void getCartByInvalidCartId() throws Exception {
//        this.mockMvc.perform(get("/api/carts/{cartId}", Integer.MIN_VALUE)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(result -> assertEquals("Cart with that id does not exist",
//                        result.getResponse().getContentAsString()));
//    }
//
//    @Test
//    @Order(4)
//    void getCartByCartId() throws Exception {
//        this.mockMvc.perform(get("/api/carts/{cartId}", 1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.cartId").exists())
//                .andExpect(jsonPath("$.user.username").value("username"))
//                .andExpect(jsonPath("$.user.password").value("password"))
//                .andExpect(jsonPath("$.user.role").value("admin"))
//                .andExpect(jsonPath("$.user.enabled").value(true))
//                .andExpect(jsonPath("$.purchaseDate").value("2021-04-21"))
//                .andExpect(jsonPath("$.status").value("Saved"));
//    }
//
//    @Test
//    @Order(6)
//    void updateCart() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/api/carts/{cartId}", 1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        Cart cart = this.mapper.readValue(result.getResponse().getContentAsString(), Cart.class);
//        cart.setStatus("Delivered");
//
//        this.mockMvc.perform(put("/api/carts")
//                .content(asJsonString(cart))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.cartId").value(cart.getCartId()))
//                .andExpect(jsonPath("$.user.username").value("username"))
//                .andExpect(jsonPath("$.user.password").value("password"))
//                .andExpect(jsonPath("$.user.role").value("admin"))
//                .andExpect(jsonPath("$.user.enabled").value(true))
//                .andExpect(jsonPath("$.purchaseDate").value("2021-04-21"))
//                .andExpect(jsonPath("$.status").value("Delivered"));
//
//
//
//    }
//
//    @Order(7)
//    @Test
//    void deletingACartReturnsExpectedMessageAndStatusCode() throws Exception {
//        this.mockMvc.perform(delete("/api/carts/{cartId}", 1))
//                .andExpect(status().isNoContent())
//                .andExpect(result -> assertEquals("Cart 1 deleted",
//                        result.getResponse().getContentAsString()));
//    }

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
