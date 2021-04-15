package com.tp.Nile.models;

import static org.junit.jupiter.api.Assertions.*;

import com.tp.Nile.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ReviewTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository repo;

    @BeforeEach
    public void setup() {
        Review setupReview = new Review();
        setupReview.setReviewId(8);
        setupReview.setReviewDate(LocalDate.of(2020, 4, 14));
        setupReview.setTitle("This is a test title");
        setupReview.setSummary("This is a test summary");
        setupReview.setUser(new User(100, null, null));
        setupReview.setRating(4);

        repo.save(setupReview);
    }

    @Test
    @Rollback(false)
    public void testCreateReview() {
        Review newReview = new Review();
        newReview.setReviewId(2);
        newReview.setReviewDate(LocalDate.of(2020, 4, 14));
        newReview.setTitle("This is a test title");
        newReview.setSummary("This is a test summary");
        newReview.setUser(new User(100, null, null));
        newReview.setRating(3);

        Review savedReview = repo.save(newReview);

        assertNotNull(savedReview);
    }

//    @Test
//    public void findReviewById() {
//
//        int id = 8;
//        LocalDate testDate = LocalDate.of(2020, 4, 14);
//        String testTitle = "This is a test title";
//        String testSummary = "This is a test summary";
//        int userId = 100;
//        int rating = 4;
//
//        Review testReview = repo.findById(id).isPresent() ?
//                repo.findById(id).get(): null;
//
//        assertEquals(testReview.getReviewId(), id);
//
//        assertEquals(testReview.getTitle(), testTitle);
//
//        assertEquals(testReview.getSummary(), testSummary);
//
//        assertEquals(testReview.getUser().getUserId(), userId);
//
//        assertEquals(testReview.getRating(), rating);
//
//    }

    @Test
    public void findReviewByUser() {

    }

    @Test
    public void deleteReview() {

    }

    @Test
    public void findAllReviews() {

    }




}
