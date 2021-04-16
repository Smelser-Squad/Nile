package com.tp.Nile.controllers;

import com.tp.Nile.controllers.requests.AddProductRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void getAllProductsShouldReturnEmptyList() {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/api/products", ArrayList.class
        )).isEmpty();
    }

    @Test
    @Order(2)
    void addingProductShouldReturnAProduct() {
        AddProductRequest productRequest = new AddProductRequest();
        productRequest.setBrand("Nike");
        productRequest.setDescription("Athletic shoes");
        productRequest.setName("Air Jordan");
        productRequest.setPrice(249.99);
        productRequest.setPrimeEligible(false);
        productRequest.setStock(2);

        ResponseEntity<String> response = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/api/products",
                productRequest,
                String.class
                );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("productId"));
    }
}
