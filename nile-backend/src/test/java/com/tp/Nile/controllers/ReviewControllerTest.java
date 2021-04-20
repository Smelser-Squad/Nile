package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.models.*;
import lombok.ToString;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    public void testGetAllReviews() throws Exception {
        this.mockMvc.perform(get("/api/reviews"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("[]",
                        result.getResponse().getContentAsString()));
    }

//    @Test
//    @Order(2)
//    public void testAddReview() throws Exception {
//
//        Feature feature1 = new Feature();
//        feature1.setName("sample feature one");
//
//        List<Review> reviews = new ArrayList<>();
//
//        Review review1 = new Review();
//        review1.setHelpful(true);
//        review1.setRating(4);
//        review1.setSummary("sample summary");
//        review1.setTitle("sample title");
//        review1.setFeature(feature1);
//        review1.setUser(new User());
//
//        reviews.add(review1);
//
//        this.mockMvc.perform(post("/api/reviews")
//                .content(asJsonString(review1))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.reviews").isArray())
//                .andExpect(jsonPath("$.reviews[0].helpful").value(true))
//                .andExpect(jsonPath("$.reviews[0].title").value("sample title"))
//                .andExpect(jsonPath("$.reviews[0].summary").value("sample summary"))
//                .andExpect(jsonPath("$.reviews[0].rating").value(4))
//                .andExpect(jsonPath("$.reviews[0].reviewDate").exists())
//                .andExpect(jsonPath("$.reviews[0].feature.featureId").exists())
//                .andExpect(jsonPath("$.reviews[0].user.userId").exists());
//
//
//    }

}
