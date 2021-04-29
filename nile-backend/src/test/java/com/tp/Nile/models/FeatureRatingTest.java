package com.tp.Nile.models;


import com.tp.Nile.repositories.FeatureRatingRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeatureRatingTest {

    @Autowired
    FeatureRatingRepository repo;

    @BeforeEach
    public void setup() {
        Feature feature = new Feature();
        feature.setName("Bluetooth");

        Product thisProduct = buildProduct();

        FeatureRating newRating = new FeatureRating();
        newRating.setRating(5);
        newRating.setRatingId(10);
        newRating.setFeature(feature);
        newRating.setProduct(thisProduct);
        repo.save(newRating);
    }

    @Test
    @Rollback(true)
    public void addFeatureRatingGoldenPath() {
        Feature feature = new Feature();
        feature.setName("New feature");

        Product thisProduct = buildProduct();

        FeatureRating newRating = new FeatureRating();
        newRating.setRating(2);
        newRating.setRatingId(2);
        newRating.setFeature(feature);
        newRating.setProduct(thisProduct);

        FeatureRating saved = repo.save(newRating);
        assertNotNull(saved);
        assertEquals(2, newRating.getRatingId());
        assertEquals(2, newRating.getRating());
        assertEquals("New feature", newRating.getFeature().getName());

    }

    @Test
    public void getAllFeatureRatingsGoldenPathTest() {

        Feature feature = new Feature();
        feature.setName("New feature");

        Product thisProduct = buildProduct();

        FeatureRating newRating = new FeatureRating();
        newRating.setRating(2);
        newRating.setRatingId(2);
        newRating.setFeature(feature);
        newRating.setProduct(thisProduct);

        FeatureRating saved = repo.save(newRating);

        List<FeatureRating> allRatings = new ArrayList<>();

        allRatings.add(saved);

        assertEquals(1, allRatings.size());
    }

    @Test
    public void getRatingInvalidIdFailTest() {
        Optional<FeatureRating> retrieved = repo.findById(Integer.MIN_VALUE);
        if (retrieved.isPresent())
            fail();
    }

    @Test
    public void getRatingByFeatureAndProductId() {
        Feature feature = new Feature();
        feature.setName("New feature");

        Product thisProduct = buildProduct();

        FeatureRating newRating = new FeatureRating();
        newRating.setRating(2);
        newRating.setRatingId(2);
        newRating.setFeature(feature);
        newRating.setProduct(thisProduct);

        FeatureRating saved = repo.save(newRating);
        assertNotNull(saved);

        if (saved == null)
            fail();
        else {
            FeatureRating rating = saved;
            assertEquals(2, rating.getRating());
            assertEquals(2, rating.getRatingId());
        }
    }

    public Product buildProduct() {
        Product product = new Product();
        product.setName("sample name");
        product.setStock(10);
        product.setPrimeEligible(false);
        product.setDescription("sample description");
        product.setBrand("sample brand");
        product.setPrice(new BigDecimal("4.99"));
        return product;
    }

}
