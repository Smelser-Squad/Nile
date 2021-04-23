package com.tp.Nile.repositories;

import com.tp.Nile.models.Review;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByUserUserId(Integer userId);

    @Query("select p from Review p where p.product.productId=:productId")
    List<Review> findByProductId(Integer productId);
}
