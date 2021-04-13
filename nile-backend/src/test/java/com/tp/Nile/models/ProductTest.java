package com.tp.Nile.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTest {

    @Autowired
    private TestEntityManager entityManager;

    Product test1;

    @BeforeEach
    public void setup() {
        test1 = new Product();
        test1.setProductId(1);
        test1.setCategoryId(1);
        test1.setVendorId(1);
        Type type = new Type("test type");
        test1.setType(type);
        test1.setPrice(0.0);
        test1.setName("test name");
        test1.setDescription("test description");
        test1.setBrand("test brand");
    }

    @Test
    public void saveProductId(){

        Product savedProduct = this.entityManager.persistAndFlush(test1);

        assertEquals(1,savedProduct.getProductId());
    }

    @Test
    public void getProductId() {
        Product test = new Product();
        test.setProductId(10);

        assertEquals(10, test.getProductId());
    }

    @Test
    public void setReviewId() {
        Product test = new Product();

        assertEquals(null,test.getProductId());

        test.setProductId(10);

        assertEquals(10,test.getProductId());

    }


}
