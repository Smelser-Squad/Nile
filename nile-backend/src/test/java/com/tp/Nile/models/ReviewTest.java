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

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ReviewTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewRepository repo;

    @Test
    @Rollback(false)
    public void testCreateReview() {
        Review newReview = new Review();
        newReview.setReviewId(1);
        newReview.setReviewDate(LocalDate.of(2020, 4, 14));
        newReview.setTitle("This is a test title");
        newReview.setSummary("This is a test summary");
        newReview.setUser(new User(10, null, null));

        Review savedReview = repo.save(newReview);

        assertNotNull(savedReview);
    }

//    @Test
//    public void findReviewById() {
//
//        int id = 1;
//        LocalDate testDate = LocalDate.of(2020, 4, 14);
//        String testTitle = "This is a test title";
//        String testSummary = "This is a test summary";
//        int userId = 10;
//
//        Review testReview = repo.findByReviewId(id);
//
//        assertEquals(testReview.getReviewId(), id);
//
//        assertEquals(testReview.getTitle(), testTitle);
//
//        assertEquals(testReview.getSummary(), testSummary);
//
//        assertEquals(testReview.getUser().getUserId(), userId);
//
//    }




}
