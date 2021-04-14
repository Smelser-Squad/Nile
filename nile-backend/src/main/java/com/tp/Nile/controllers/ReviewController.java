package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.services.ReviewService;
import com.tp.Nile.services.ReviewServiceImpl;
import com.tp.Nile.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    ReviewServiceImpl service;

    @PostMapping("/addReview")
    public ResponseEntity addReview(@RequestBody Review review) {
        return ResponseEntity.ok(service.addReview(review));
    }

    @GetMapping("/reviews")
    public ResponseEntity getReviews() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity getReviewById(@PathVariable Integer reviewId) {
        try {
            return ResponseEntity.ok(service.getReviewById(reviewId));
        } catch (NullReviewIdException | InvalidReviewIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/reviews/{userId}")
    public ResponseEntity getReviewsByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(service.getReviewsByUserId(userId));
        } catch (NullUserIdException | InvalidUserIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateReview")
    public ResponseEntity updateReview(@RequestBody Review review) {
        return ResponseEntity.ok(service.updateReview(review));
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public String deleteReview(@PathVariable Integer reviewId) {
        {
            String toReturn="";
            try {
                if (service.deleteReview(reviewId)) {
                    toReturn ="Product " + reviewId + "deleted";
                }else{
                    toReturn="Product " + reviewId + "not found";
                }
            }catch (InvalidReviewIdException | NullReviewIdException ex){
                ex.getMessage();
            }
            return toReturn;
        }
    }
}


