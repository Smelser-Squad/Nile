package com.tp.Nile.repositories;

import com.tp.Nile.models.Feature;
import com.tp.Nile.models.FeatureRating;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile({ "dev", "test" })
public interface FeatureRatingRepository extends JpaRepository<FeatureRating,Integer> {

    @Query("SELECT fr.rating from FeatureRating fr where fr.feature.featureId =:featureId and fr.product.productId =:productId")
    Optional<FeatureRating> getRatingByFeatureAndProductId
            (@Param("featureId")Integer featureId,
             @Param("productId")Integer productId);

}
