package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.services.ReviewServiceImpl;
import com.tp.Nile.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/reviews")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

    @Autowired
    ReviewServiceImpl service;

    @PostMapping
    public ResponseEntity addReview(@RequestBody Review review) {
        try {
            return ResponseEntity.ok(service.addReview(review));
        }
        catch (NullReviewIdException | InvalidReviewIdException | NullReviewAttributeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getReviews() {
        try {
            return ResponseEntity.ok(service.getAllReviews());
        }
        catch (InvalidReviewIdException | NullReviewIdException | NullReviewAttributeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{reviewId}")
    public ResponseEntity getReviewById(@PathVariable Integer reviewId) {
        try {
            return ResponseEntity.ok(service.getReviewById(reviewId));
        } catch (NullReviewIdException | InvalidReviewIdException | NullReviewAttributeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getReviewsByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(service.getReviewsByUserId(userId));
        } catch (NullUserIdException | InvalidUserIdException | NullReviewAttributeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateReview(@RequestBody Review review) {
        try {
            return ResponseEntity.ok(service.updateReview(review));
        }
        catch (InvalidReviewIdException | NullReviewIdException | NullReviewAttributeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("deleteReview/{reviewId}")
    public String deleteReview(@PathVariable Integer reviewId) {
        {
            String toReturn="";
            try {
                if (service.deleteReview(reviewId)) {
                    toReturn ="Product " + reviewId + "deleted";
                }else{
                    toReturn="Product " + reviewId + "not found";
                }
            }catch (InvalidReviewIdException | NullReviewIdException | NullReviewAttributeException ex){
                ex.getMessage();
            }
            return toReturn;
        }
    }
}

