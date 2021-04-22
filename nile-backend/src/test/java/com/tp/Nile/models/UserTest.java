package com.tp.Nile.models;


import static org.junit.jupiter.api.Assertions.*;
import com.tp.Nile.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @BeforeEach
    public void setup() {
        repo.deleteAll();

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

    }

}
