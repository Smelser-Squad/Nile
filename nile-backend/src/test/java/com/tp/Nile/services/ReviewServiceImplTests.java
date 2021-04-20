package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.*;
import com.tp.Nile.repositories.ReviewRepository;
import com.tp.Nile.repositories.TypeRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReviewServiceImplTests {


    @Mock
    private ReviewRepository repo;

    @InjectMocks
    private ReviewServiceImpl service;

    @Test
    public void testGetAllReviewsGoldenPath() throws NullReviewAttributeException,
            NullReviewIdException, InvalidReviewIdException {

        Feature feature1 = new Feature();
        feature1.setName("sample feature one");

        Review review = new Review();
        review.setReviewId(1);
        review.setHelpful(true);
        review.setRating(4);
        review.setSummary("sample summary");
        review.setTitle("sample title");
        review.setFeature(feature1);
        review.setUser(new User());

        when(repo.findAll()).thenReturn(List.of(review));
        List<Review> reviews = service.getAllReviews();

        org.assertj.core.api.Assertions.assertThat(reviews)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(type -> {
                    org.assertj.core.api.Assertions.assertThat(reviews.get(0).getReviewId()).isEqualTo(1);
                    org.assertj.core.api.Assertions.assertThat(reviews.get(0).getRating()).isEqualTo(4);
                    org.assertj.core.api.Assertions.assertThat(reviews.get(0).getTitle()).isEqualTo("sample title");
                    org.assertj.core.api.Assertions.assertThat(reviews.get(0).getSummary()).isEqualTo("sample summary");
                });
    }

    @Test
    public void testGetReviewByIdGoldenPath() {

        Feature feature1 = new Feature();
        feature1.setName("sample feature one");

        Review review = new Review();
        review.setReviewId(1);
        review.setHelpful(true);
        review.setRating(4);
        review.setSummary("sample summary");
        review.setTitle("sample title");
        review.setFeature(feature1);
        review.setUser(new User());

        when(repo.findById(1)).thenReturn(Optional.of(review));
        Review newReview = null;

        try {
            newReview = service.getReviewById(1);
        } catch (NullReviewIdException | NullReviewAttributeException | InvalidReviewIdException ex)
        {
            fail("Exception was thrown");
        }

        org.assertj.core.api.Assertions.assertThat(newReview)
                .isNotNull()
                .isInstanceOf(Review.class)
                .hasFieldOrPropertyWithValue("reviewId", 1)
                .hasFieldOrPropertyWithValue("summary", "sample summary");
        org.assertj.core.api.Assertions.assertThat(newReview.getUser())
                .isNotNull();

    }

    @Test
    public void testGetReviewIdByNullReviewId() {
        try {
            Review newReview = service.getReviewById(null);
            failBecauseExceptionWasNotThrown(NullReviewIdException.class);
        } catch (InvalidReviewIdException | NullReviewAttributeException ex) {
            fail("Wrong exception was thrown here");
        } catch (NullReviewIdException ex )
        {

        }
    }

    @Test
    public void testGetReviewIdByInvalidReviewId() {
        try {
            Review newReview = service.getReviewById(10000);
            failBecauseExceptionWasNotThrown(InvalidReviewIdException.class);
        } catch (NullReviewIdException | NullReviewAttributeException ex) {
            fail("Wrong exception was thrown here");
        } catch (InvalidReviewIdException ex )
        {

        }
    }

    @Test
    public void testAddReviewGoldenPath() {
        int reviewId = 2;

        Feature feature1 = new Feature();
        feature1.setName("sample feature one");

        Review newReview = new Review();
        newReview.setReviewId(reviewId);
        newReview.setHelpful(true);
        newReview.setRating(4);
        newReview.setSummary("sample summary");
        newReview.setTitle("sample title");
        newReview.setFeature(feature1);
        newReview.setUser(new User());

        when(repo.saveAndFlush(newReview)).thenReturn(newReview);

        Review addedReview = null;

        try {
            addedReview = service.addReview(newReview);
        } catch (InvalidReviewIdException | NullReviewIdException
                | NullReviewAttributeException | InvalidReviewException ex) {
            fail("Exception thrown");
        }

        addedReview.setReviewId(reviewId);
        org.assertj.core.api.Assertions.assertThat(addedReview)
                .isNotNull()
                .isInstanceOf(Review.class)
                .hasFieldOrPropertyWithValue("reviewId", 2)
                .hasFieldOrPropertyWithValue("summary", "sample summary")
                .hasFieldOrPropertyWithValue("rating", 4);
        org.assertj.core.api.Assertions.assertThat(addedReview.getUser())
                .isNotNull();
        org.assertj.core.api.Assertions.assertThat(addedReview.getFeature())
                .isNotNull();


    }

    @Test
    public void testAddReviewInvalidRating() {
        int reviewId = 2;

        Feature feature1 = new Feature();
        feature1.setName("sample feature one");

        Review newReview = new Review();
        newReview.setReviewId(reviewId);
        newReview.setHelpful(true);
        newReview.setRating(12);
        newReview.setSummary("sample summary");
        newReview.setTitle("sample title");
        newReview.setFeature(feature1);
        newReview.setUser(new User());

        try {
            service.addReview(newReview);
            failBecauseExceptionWasNotThrown(InvalidReviewException.class);
        } catch (InvalidReviewIdException | NullReviewIdException | NullReviewAttributeException ex) {
            fail("Wrong exception thrown");
        } catch (InvalidReviewException ex) {

        }
    }

}
