package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select p from Review p where p.user_id=:userId")
    List<Review> getReviewsByUserId(@Param("user_id") Integer userId);

    @Query("select p from Review p where p.review_id=:reviewId")
    Review getReviewById(@Param("review_id") Integer reviewId);

}
