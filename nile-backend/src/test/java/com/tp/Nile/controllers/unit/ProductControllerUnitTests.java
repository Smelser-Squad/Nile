package com.tp.Nile.controllers.unit;

import com.tp.Nile.controllers.ProductController;
import com.tp.Nile.models.Product;
import com.tp.Nile.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static com.tp.Nile.controllers.helpers.JsonStringMapper.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductServiceImpl productService;

    @Test
    void addProduct() throws Exception {
        Product product = buildSampleProduct();

        Product toReturn = buildSampleProduct();
        toReturn.setProductId(1);

        Mockito.when(productService.addProduct(product)).thenReturn(toReturn);
        this.mockMvc.perform(post("/api/products")
                .content(asJsonString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.name").value("sample name"))
                .andExpect(jsonPath("$.brand").value("sample brand"))
                .andExpect(jsonPath("$.stock").value(4))
                .andExpect(jsonPath("$.primeEligible").value(true))
                .andExpect(jsonPath("$.description").value("sample description"));
    }

    private Product buildSampleProduct() {
        Product product = new Product();
        product.setPrice(new BigDecimal("5.99"));
        product.setBrand("sample brand");
        product.setStock(4);
        product.setPrimeEligible(true);
        product.setDescription("sample description");
        product.setName("sample name");
        return product;
    }
}
