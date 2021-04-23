package com.tp.Nile.services;


import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.*;
import com.tp.Nile.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceImplTests {

    @Mock
    UserRepository repo;

    @InjectMocks
    UserServiceImpl service;

    @Test
    public void testGetAllUsersGoldenPath() throws NullUserException,
            InvalidUserIdException, NullUserIdException {

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

        User newUser = new User();
        newUser.setUserId(1);
        newUser.setUsername("Bdeyo");
        newUser.setEnabled(true);
        newUser.setRole("Owner");
        newUser.setPassword("1234");

        when(repo.findAll()).thenReturn((List.of(newUser)));
        List<User> users = service.getAllUsers();

        assertThat(users)
                .isNotEmpty()
                .isNotNull();
    }

    @Test
    public void testGetUserByIdGoldenPath() {
        User newUser = new User();
        newUser.setUserId(1);
        newUser.setUsername("Bdeyo");
        newUser.setEnabled(true);
        newUser.setRole("Owner");
        newUser.setPassword("1234");

        when(repo.findById(1)).thenReturn(Optional.of(newUser));
        User thisUser = null;
        try {
            thisUser = service.getUserById(1);
        } catch (NullUserIdException e) {
            fail("Exception thrown");
        } catch (NullUserException e) {
            fail("Exception thrown");
        } catch (InvalidUserIdException e) {
            fail("Exception thrown");
        }
        assertThat(thisUser)
                .isNotNull()
                .isInstanceOf(User.class)
                .hasFieldOrPropertyWithValue("userId", 1);
    }

    @Test
    public void testGetUserByIdNullUserId() {
        try {
            User user = service.getUserById(null);
            failBecauseExceptionWasNotThrown(NullUserIdException.class);
        } catch (InvalidUserIdException ex) {
            fail("Wrong exception was thrown");
        } catch (NullUserException ex) {
            fail("Wrong exception was thrown");
        } catch (NullUserIdException ex) {
        }
    }

    @Test
    public void testGetUserByIdInvalidUserId() {
        when(repo.findById(-1)).thenReturn(Optional.empty());
        try {
            User user = service.getUserById(-1);
            failBecauseExceptionWasNotThrown(InvalidUserIdException.class);
        } catch (NullUserIdException ex) {
            fail("Wrong exception was thrown");
        } catch (NullUserException ex) {
            fail("Wrong exception was thrown");
        } catch (InvalidUserIdException ex) {
        }
    }

    @Test
    public void testAddUserGoldenPath() {

        int userId = 10;
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

        User newTestUser = new User();
        newTestUser.setUserId(userId);
        newTestUser.setCarts(cartList);
        newTestUser.setAnswers(answerSet);

        when(repo.saveAndFlush(newTestUser)).thenReturn(newTestUser);

        User addedUser = null;

        try {
            addedUser = service.addUser(newTestUser);
        } catch (NullUserIdException | NullUserException | InvalidUserIdException ex) {
            fail("Exception was thrown");
        }

        addedUser.setUserId(userId);
        assertThat(addedUser)
                .isNotNull()
                .isInstanceOf(User.class)
                .hasFieldOrPropertyWithValue("userId", 10);

        assertThat(addedUser.getCarts().get(0))
                .isNotNull()
                .isInstanceOf(Cart.class);

    }

    @Test
    public void testAddUserNullId() {

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

        User newTestUser = new User();
        newTestUser.setUserId(null);
        newTestUser.setCarts(cartList);
        newTestUser.setAnswers(answerSet);

        User addedUser = null;

        try {
            addedUser = service.addUser(newTestUser);
            failBecauseExceptionWasNotThrown(NullUserIdException.class);
        } catch (NullUserException | InvalidUserIdException ex) {
            fail("Wrong exception was thrown");
        } catch (NullUserIdException ex) {

        }

    }

    @Test
    public void testAddUserInvalidId() {

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

        User newTestUser = new User();
        newTestUser.setUserId(-1);
        newTestUser.setCarts(cartList);
        newTestUser.setAnswers(answerSet);

        User addedUser = null;

        try {
            addedUser = service.addUser(newTestUser);
            failBecauseExceptionWasNotThrown(InvalidUserIdException.class);
        } catch (NullUserException | NullUserIdException ex) {
            fail("Wrong exception was thrown");
        } catch (InvalidUserIdException ex) {

        }

    }
}
