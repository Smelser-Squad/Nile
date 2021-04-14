package com.tp.Nile.models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewTest {

    @Autowired
    private TestEntityManager entityManager;

    private Review newReview;

    @BeforeEach
    public void setup() {
        newReview.setReviewId(1);
        newReview.setReviewDate(LocalDate.of(2020, 4, 14));
        newReview.setSummary("Test Summary");
        newReview.setTitle("Test Title");
        newReview.setUser(new User());

    }

}
