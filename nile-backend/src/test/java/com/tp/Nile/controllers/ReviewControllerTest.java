package com.tp.Nile.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tp.Nile.exceptions.InvalidReviewIdException;
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

import java.io.UnsupportedEncodingException;
import java.util.*;

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
                .andExpect(result -> assertNotNull(result.getResponse().getContentAsString()));
    }

    @Test
    @Order(2)
    public void testAddReview() throws Exception {

        List<Review> reviews = new ArrayList<>();

        Review review1 = new Review();
        review1.setReviewId(1);
        review1.setHelpful(true);
        review1.setRating(4);
        review1.setSummary("sample summary");
        review1.setTitle("sample title");

        reviews.add(review1);

        this.mockMvc.perform(post("/api/reviews")
                .content(asJsonString(review1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.reviewId").value(1))
                .andExpect(jsonPath("$.helpful").value(true))
                .andExpect(jsonPath("$.summary").value("sample summary"))
                .andExpect(jsonPath("$.title").value("sample title"))
                .andExpect(jsonPath("$.rating").value(4));

    }

    @Test
    @Order(3)
    public void getReviewByInvalidReviewId() throws Exception {
        this.mockMvc.perform(get("/api/reviews/{reviewId}", Integer.MIN_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertEquals("Review with that id does not exist",
                        result.getResponse().getContentAsString()));
    }

    @Test
    @Order(4)
    public void getReviewByReviewIdGoldenPath() throws Exception {
        this.mockMvc.perform(get("/api/reviews/{reviewId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reviewId").exists())
                .andExpect(jsonPath("$.reviewId").value(1))
                .andExpect(jsonPath("$.summary").value("sample summary"))
                .andExpect(jsonPath("$.title").value("sample title"))
                .andExpect(jsonPath("$.rating").value(4));
    }

//    @Test
//    @Order(5)
//    public void getReviewByUserIdGoldenPath() throws Exception {
//
//        List<Review> newList = new ArrayList<>();
//
//        Review newReview = new Review();
//        newReview.setReviewId(5);
//        newReview.setHelpful(true);
//        newReview.setRating(3);
//        newReview.setSummary("sample summary");
//        newReview.setTitle("sample title");
//
//        newList.add(newReview);
//
//        User newUser = new User();
//        newUser.setUserId(1);
//
//        newReview.setUser(newUser);
//
//        this.mockMvc.perform(get("/api/reviews/by/{userId}", 1)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.reviewId").exists())
//                .andExpect(jsonPath("$.reviewId").value(5))
//                .andExpect(jsonPath("$.summary").value("sample summary"))
//                .andExpect(jsonPath("$.title").value("sample title"))
//                .andExpect(jsonPath("$.rating").value(3));
//
//    }

    @Test
    @Order(5)
    public void updateReviewGoldenPath() throws Exception {
        MvcResult toUpdate = this.mockMvc.perform(get("/api/reviews/{reviewId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Review review = this.mapper.readValue(toUpdate.getResponse().getContentAsString(), Review.class);
        review.setReviewId(2);
        review.setHelpful(true);
        review.setRating(4);
        review.setSummary("sample summary 2");
        review.setTitle("sample title 2");

        this.mockMvc.perform(put("/api/reviews")
                .content(asJsonString(review))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.reviewId").exists())
                .andExpect(jsonPath("$.reviewId").value(2))
                .andExpect(jsonPath("$.summary").value("sample summary 2"))
                .andExpect(jsonPath("$.title").value("sample title 2"))
                .andExpect(jsonPath("$.rating").value(4));


    }

    @Test
    @Order(6)
    public void deletingReviewAndGettingCorrectMessage() throws Exception {
        this.mockMvc.perform(delete("/api/reviews/{reviewId}", 2))
                .andExpect(result -> assertEquals("Review " + 2 + " deleted",
                        result.getResponse().getContentAsString()));
    }

//    @Test
//    @Order(7)
//    public void deletingReviewWithInvalidIdAndGettingIncorrectMessage() throws Exception {
//        this.mockMvc.perform(delete("/api/reviews/{reviewId}", Integer.MIN_VALUE))
//                .andExpect(result -> assertEquals("Review " + Integer.MIN_VALUE + " not found",
//                        result.getResponse().getContentAsString()));
//    }

}
