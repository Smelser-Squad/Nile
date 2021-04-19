package com.tp.Nile.models;

import static org.junit.jupiter.api.Assertions.*;

import com.tp.Nile.repositories.ReviewRepository;
import com.tp.Nile.repositories.TypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReviewTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository repo;

    @BeforeEach
    public void setup() {
        Review setupReview = new Review();
        setupReview.setReviewId(10);
        setupReview.setTitle("Test Title");
        setupReview.setUser(new User());
        setupReview.setRating(5);
        setupReview.setSummary("Test Summary");
        setupReview.setReviewDate(LocalDate.of(2020, 4, 19));

        repo.save(setupReview);
    }

    @Test
    @Rollback(true)
    public void testAddReview() {
        Review newReview = new Review();
        newReview.setReviewId(12);
        newReview.setTitle("Test Title 2");
        newReview.setUser(new User());
        newReview.setRating(3);
        newReview.setSummary("Test Summary 2");
        newReview.setReviewDate(LocalDate.of(1999, 4, 20));

        Review savedReview = repo.save(newReview);

        assertNotNull(savedReview);
        assertEquals(2, savedReview.getReviewId());
        assertEquals("Test Title 2", savedReview.getTitle());
        assertEquals("Test Summary 2", savedReview.getSummary());
        assertEquals(3, savedReview.getRating());
        assertEquals(LocalDate.of(1999, 4, 20), savedReview.getReviewDate());
    }

    @Test
    public void testGetAllReviews() {
        List<Review> allReviews = repo.findAll();
        assertEquals(1, allReviews.size());
    }

    @Test
    public void testGetReviewByIdGoldenPath() {
        Optional<Review> isRetrieved = repo.findById(1);
        if(!isRetrieved.isPresent())
            fail();
        else
        {
            Review review = isRetrieved.get();
            assertEquals("Test Summary", review.getSummary());
            assertEquals(1, review.getReviewId());
            assertEquals(LocalDate.of(2020, 4, 19), review.getReviewDate());
        }
    }

    @Test
    public void testGetReviewByIdNoReviewFound() {
        Optional<Review> isRetrieved = repo.findById(100);
        if (isRetrieved.isPresent())
            fail();
    }

    @Test
    public void testUpdateReview() {
        Review beforeUpdate = new Review();
        beforeUpdate.setReviewId(12);
        beforeUpdate.setTitle("Test Title 2");
        beforeUpdate.setUser(new User());
        beforeUpdate.setRating(3);
        beforeUpdate.setSummary("Test Summary 2");
        beforeUpdate.setReviewDate(LocalDate.of(1999, 4, 20));

        assertEquals(12, beforeUpdate.getReviewId());
        assertEquals("Test Title 2", beforeUpdate.getTitle());
        assertEquals("Test Summary 2", beforeUpdate.getSummary());
        assertEquals(3, beforeUpdate.getRating());

        Review toSave = repo.save(beforeUpdate);

        assertEquals(2, toSave.getReviewId());

        Review toUpdate = new Review();
        toUpdate.setReviewId(15);
        toUpdate.setTitle("Test Title 29");
        toUpdate.setUser(new User());
        toUpdate.setRating(4);
        toUpdate.setSummary("Test Summary 29");
        toUpdate.setReviewDate(LocalDate.of(2020, 4, 19));

        toSave = repo.saveAndFlush(toUpdate);

        assertEquals("Test Title 29", toSave.getTitle());
        assertEquals(4, toSave.getRating());

    }

    @Test
    public void testDeleteReview() {
        Review toDelete = new Review();
        toDelete.setReviewId(12);
        toDelete.setTitle("Test Title 2");
        toDelete.setUser(new User());
        toDelete.setRating(3);
        toDelete.setSummary("Test Summary 2");
        toDelete.setReviewDate(LocalDate.of(1999, 4, 20));

        repo.save(toDelete);

        assertEquals(2, repo.findAll().size());

        repo.delete(toDelete);

        assertEquals(1, repo.findAll().size());

    }
}
