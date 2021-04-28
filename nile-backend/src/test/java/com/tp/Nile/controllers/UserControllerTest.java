package com.tp.Nile.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

//    @Autowired
//    MockMvc mockMvc;
//
//    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
//    static String asJsonString(final Object obj) {
//        try {
//            final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());;
//            final String jsonContent = mapper.writeValueAsString(obj);
//            return jsonContent;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    @Order(1)
//    public void testGetAllUsers() throws Exception {
//        this.mockMvc.perform(get("/api/users"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()));
//    }
//
//    @Test
//    @Order(2)
//    public void testAddUser() throws Exception {
//
//        User thisUser = new User();
//        thisUser.setEnabled(true);
//        thisUser.setPassword("password");
//        thisUser.getRoles().add(new Role(RoleName.ROLE_ADMIN));
//        thisUser.setUsername("username");
//
//        this.mockMvc.perform(post("/api/users")
//                .content(asJsonString(thisUser))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.userId").value(1))
//                .andExpect(jsonPath("$.enabled").value(true))
//                .andExpect(jsonPath("$.password").value("password"))
//                .andExpect(jsonPath("$.role").value("admin"))
//                .andExpect(jsonPath("$.username").value("username"));
//
//
//    }
//
//    @Test
//    @Order(3)
//    public void getUserById() throws Exception {
//        this.mockMvc.perform(get("/api/users/{userId}", 1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").exists())
//                .andExpect(jsonPath("$.userId").value(1));
//    }
//
////    @Test
////    @Order(4)
////    public void getUserByInvalidUserId() throws Exception {
////        this.mockMvc.perform(get("/api/users/{userId}", Integer.MIN_VALUE)
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(result -> assertEquals("User with that id does not exist",
////                        result.getResponse().getContentAsString()));
////    }
//
//    @Test
//    @Order(5)
//    public void updateUser() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/api/users/{userId}", 1 )
//                .contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        User newUser = this.mapper.readValue(result.getResponse().getContentAsString(), User.class);
//
//        newUser.getRoles().add(new Role(RoleName.ROLE_ADMIN));
//        newUser.setEnabled(false);
//
//
//        this.mockMvc.perform(put("/api/users")
//                .content(asJsonString(newUser))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.userId").value(1))
//                .andExpect(jsonPath("$.role").value("user"))
//                .andExpect(jsonPath("$.enabled").value(false));
//    }

}
