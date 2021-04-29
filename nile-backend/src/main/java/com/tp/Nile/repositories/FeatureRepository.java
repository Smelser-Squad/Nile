package com.tp.Nile.repositories;

import com.tp.Nile.models.*;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface FeatureRepository extends JpaRepository<Feature,Integer> {

    @Query("select p.features from Product p where p.productId=:productId")
    List<Feature> getFeaturesByProductId(@Param("productId") Integer productId);

}
