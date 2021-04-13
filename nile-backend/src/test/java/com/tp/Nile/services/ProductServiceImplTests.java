//package com.tp.Nile.services;
//
//import com.tp.Nile.models.Product;
//import com.tp.Nile.models.Review;
//import com.tp.Nile.models.Type;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ProductServiceImplTests {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    private Product product1;
//    private Type type1;
//
//    @Before
//    public void setup() {
//
//        Product product1 = new Product();
//        product1.setCategoryId(1);
//        product1.setVendorId(1);
//        type1 = new Type("test type");
//        product1.setType(type1);
//        product1.setPrice(0.0);
//        product1.setName("test name");
//        product1.setDescription("test description");
//        product1.setBrand("test brand");
//    }
//
//    @Test
//    public void addProductTest() {
//        Product savedProduct = this.entityManager.persistAndFlush(product1);
//        System.out.println(savedProduct.getProductId());
//        assertEquals(savedProduct.getName(), "test name");
//
//    }
//
//}
