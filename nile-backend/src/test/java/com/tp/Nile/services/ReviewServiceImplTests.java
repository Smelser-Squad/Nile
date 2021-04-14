package com.tp.Nile.services;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.Review;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewServiceImplTests {

    @Autowired
    private TestEntityManager entityManager;

    private Review review1;
    private List<Review> reviewList;

    @Before
    public void setup() {

        review1 = new Review();
        review1.setReviewId(1);
        review1.setReviewDate(LocalDate.of(2017,5,15));
        review1.setSummary("This is a test summary");
        review1.setTitle("This Product Is Awesome!");
        review1.setUser(new User());
        reviewList.add(review1);

    }

    @Test
    public void addReviewTest() {

        Review newReview = new Review();
        newReview.setReviewId(2);
        review1.setReviewDate(LocalDate.of(2018,1,12));
        review1.setSummary("This is a test 2 summary");
        review1.setTitle("This Product Is Not Good!");

        Review savedReview = this.entityManager.persistAndFlush(newReview);

        assertEquals(savedReview.getReviewId(), 2);
        assertEquals(savedReview.getSummary(), "This is a test 2 summary");
        assertEquals(savedReview.getTitle(), "This Product Is Not Good!");
        assertEquals(savedReview.getReviewDate(), LocalDate.of(2018,1,12));

    }

    @Test
    public void editReviewTest() {



    }

    @Test
    public void deleteReviewTest() {

    }

}
