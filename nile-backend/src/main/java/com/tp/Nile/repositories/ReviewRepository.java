package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> getReviewsByUserId(@Param("user_id") Integer userId);

    Review getReviewById(@Param("review_id") Integer reviewId);

}
