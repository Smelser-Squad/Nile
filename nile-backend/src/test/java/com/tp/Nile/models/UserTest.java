package com.tp.Nile.models;


import com.tp.Nile.repositories.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;
import com.tp.Nile.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    public User buildUser() {

        List<Cart> cartList = new ArrayList<>();
        Cart newCart = new Cart();
        newCart.setCartId(1);
        newCart.setPurchaseDate(LocalDate.of(2020, 4, 21));
        newCart.setStatus("Order pending");
        cartList.add(newCart);

        Set<Answer> answerSet = new HashSet<>();
        Answer newAnswer = new Answer();
        newAnswer.setAnswerId(1);
        answerSet.add(newAnswer);

        User setupUser =  new User();

        setupUser.setUserId(1);
        setupUser.setAnswers(answerSet);
        setupUser.setCarts(cartList);
        return setupUser;
    }

    @BeforeEach
    public void setup() {
        repo.deleteAll();

        List<Cart> cartList = new ArrayList<>();
        Cart newCart = new Cart();
        newCart.setCartId(5);
        newCart.setPurchaseDate(LocalDate.of(2020, 4, 21));
        newCart.setStatus("Order pending");
        cartList.add(newCart);

        Set<Answer> answerSet = new HashSet<>();
        Answer newAnswer = new Answer();
        newAnswer.setAnswerId(5);
        answerSet.add(newAnswer);

        User setupUser =  new User();

        setupUser.setUserId(5);
        setupUser.setAnswers(answerSet);
        setupUser.setCarts(cartList);

        repo.save(setupUser);

    }

    @Test
    @Rollback(true)
    public void testAddUser() {

        List<Cart> newList = new ArrayList<>();
        Cart cart = new Cart();
        cart.setCartId(10);
        cart.setPurchaseDate(LocalDate.of(2022, 4, 21));
        cart.setStatus("Order pending 2");
        newList.add(cart);

        Set<Answer> newSet = new HashSet<>();
        Answer answer = new Answer();
        answer.setAnswerId(2);
        newSet.add(answer);

        User setupUser =  new User();

        setupUser.setUserId(2);
        setupUser.setAnswers(newSet);
        setupUser.setCarts(newList);

        User savedUser = repo.save(setupUser);

        assertNotNull(savedUser);
        assertEquals(2, savedUser.getUserId());
        assertEquals(1, savedUser.getAnswers().size());

    }

    @Test
    public void testGetAllUsers() {
        User newUser = buildUser();
        List<User> allUsers = repo.findAll();
        allUsers.add(newUser);
        assertEquals(2, allUsers.size());
    }

//    @Test
//    public void testGetUserByUserIdGoldenPath() {
//        User thisUser = repo.findById(1).get();
//        if(thisUser == null)
//            fail();
//        else
//        {
//            User user = thisUser;
//            assertEquals(1, user.getUserId());
//            assertEquals("Order pending", user.getCarts().get(0).getStatus());
//        }
//    }

}
