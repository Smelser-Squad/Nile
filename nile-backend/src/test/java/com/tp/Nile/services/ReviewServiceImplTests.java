//package com.tp.Nile.services;
//
//import com.tp.Nile.exceptions.InvalidReviewIdException;
//import com.tp.Nile.exceptions.NullReviewAttributeException;
//import com.tp.Nile.exceptions.NullReviewIdException;
//import com.tp.Nile.models.Product;
//import com.tp.Nile.models.Review;
//import com.tp.Nile.models.Type;
//import com.tp.Nile.models.User;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.*;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class ReviewServiceImplTests {
//
//    @Autowired
//    ReviewServiceImpl service;
//
//    @Test
//    public void addReviewNullIdAttribute() {
//
//        Review test = new Review();
//        test.setReviewId(null);
//        test.setRating(4);
//        test.setReviewDate(LocalDate.of(2020, 4, 14));
//        test.setTitle("Title");
//        test.setSummary("Summary");
//        test.setUser(new User());
//        try {
//            service.addReview(test);
//            fail();
//        } catch (NullReviewIdException | InvalidReviewIdException | NullReviewAttributeException ex) {
//            ex.getMessage();
//            fail();
//        }
//    }
//
//    @Test
//    public void addReviewNonNullableAttribute() {
//
//        Review newTest = new Review();
//        newTest.setReviewId(5);
//        newTest.setRating(null);
//        newTest.setReviewDate(LocalDate.of(2020, 4, 14));
//        newTest.setTitle("Title");
//        newTest.setSummary("Summary");
//        newTest.setUser(new User());
//        try {
//            service.addReview(newTest);
//            fail();
//        } catch (NullReviewIdException | InvalidReviewIdException | NullReviewAttributeException ex) {
//            ex.getMessage();
//            fail();
//        }
//
//    }
//
//    @Test
//    public void addReviewGoldenPath() throws NullReviewAttributeException, NullReviewIdException, InvalidReviewIdException {
//        Review newTest = new Review();
//        newTest.setReviewId(5);
//        newTest.setRating(5);
//        newTest.setReviewDate(LocalDate.of(2020, 4, 14));
//        newTest.setTitle("Title");
//        newTest.setSummary("Summary");
//        newTest.setUser(new User());
//        try {
//            service.addReview(newTest);
//            System.out.println(service.getReviewById(5).getReviewId());
//
//        } catch (NullReviewIdException | InvalidReviewIdException | NullReviewAttributeException ex) {
//            ex.getMessage();
//            fail();
//        }
//
//        assertEquals(5, service.getReviewById(5).getReviewId());
//        assertEquals(LocalDate.of(2020,4,14), service.getReviewById(5));
//        assertEquals("Summary", service.getReviewById(5).getSummary());
//        assertEquals(5, service.getReviewById(5).getRating());
//        assertEquals("Title", service.getReviewById(5).getTitle());
//    }
//
//}
