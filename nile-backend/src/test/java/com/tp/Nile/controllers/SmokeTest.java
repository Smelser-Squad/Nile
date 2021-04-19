package com.tp.Nile.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "test" })
public class SmokeTest {

    @Autowired
    CategoryController categoryController;

    @Autowired
    FeatureController featureController;

    @Autowired
    OrderController orderController;

    @Autowired
    ProductController productController;

    @Autowired
    QAController qaController;

    @Autowired
    ReviewController reviewController;

    @Test
    void contextLoads() throws Exception {
       assertThat(categoryController).isNotNull();
       assertThat(featureController).isNotNull();
       assertThat(orderController).isNotNull();
       assertThat(productController).isNotNull();
       assertThat(qaController).isNotNull();
       assertThat(reviewController).isNotNull();
    }
}
