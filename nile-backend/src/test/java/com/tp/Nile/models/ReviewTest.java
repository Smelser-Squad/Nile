/*
package com.tp.Nile.models;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewTest {

    @Autowired
    private TestEntityManager entityManager;

    Review test1;

    @BeforeEach
    public void setup() {
        test1 = new Review();

        test1.setReviewId(5);
        test1.setReviewDate(LocalDate.of(2020,4,12));
        test1.setTitle("test title");
        test1.setSummary("test summary");
        test1.setUserId(10);
    }

    @Test
    public void saveReviewId(){

        Review savedReview = this.entityManager.persistAndFlush(test1);

        assertEquals(5,savedReview.getReviewId());
    }

    @Test
    public void getReviewId() {
        Review test = new Review();
        test.setReviewId(10);

        assertEquals(10, test.getReviewId());
    }

    @Test
    public void setReviewId() {
        Review test = new Review();

        assertEquals(null,test.getReviewId());

        test.setReviewId(10);

        assertEquals(10,test.getReviewId());

    }

    @Test
    public void getUserId() {
        Review test = new Review();

        test.setUserId(5);

        assertEquals(5,test.getUserId());
    }

    @Test
    public void setUserId() {
        Review test = new Review();

        assertEquals(null,test.getUserId());

        test.setUserId(5);

        assertEquals(5,test.getUserId());
    }

    @Test
    public void getSummary() {
        Review test = new Review();

        test.setSummary("test summary");

        assertEquals("test summary",test.getSummary());
    }

    @Test
    public void setSummary() {
        Review test = new Review();

        assertEquals(null, test.getSummary());

        test.setSummary("test summary");

        assertEquals("test summary", test.getSummary());
    }

    @Test
    public void getTitle() {
        Review test = new Review();

        test.setTitle("test title");

        assertEquals("test title", test.getTitle());
    }

    @Test
    public void setTitle() {
        Review test = new Review();

        assertEquals(null,test.getTitle());

        test.setTitle("test title");

        assertEquals("test title",test.getTitle());
    }

    @Test
    public void getReviewDate() {
        Review test = new Review();

        test.setReviewDate(LocalDate.of(2020, 4, 12));

        assertEquals(LocalDate.of(2020,4,12),test.getReviewDate());


    }

    @Test
    public void setReviewDate() {
        Review test = new Review();

        assertEquals(null,test.getReviewDate());

        test.setReviewDate(LocalDate.of(2020, 4, 12));

        assertEquals(LocalDate.of(2020,4,12),test.getReviewDate());

    }
}
*/
