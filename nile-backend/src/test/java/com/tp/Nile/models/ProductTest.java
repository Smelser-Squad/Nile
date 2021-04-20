package com.tp.Nile.models;

import com.tp.Nile.repositories.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProductTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }

    @Test
    public void saveProductGoldenPath() {
        Product product = buildProduct();
        product = productRepository.save(product);
        assertNotNull(product.getProductId());
        assertEquals("sample name", product.getName());
        assertEquals("sample brand", product.getBrand());
        assertEquals("sample description", product.getDescription());
        assertEquals(10, product.getStock());
        assertFalse(product.isPrimeEligible());
        assertEquals(BigDecimal.valueOf(4.99), product.getPrice());
    }

    @Test
    public void saveProductWithNegativeStockThrowsConstraintViolationException() {
        Product product = buildProduct();
        product.setStock(-1);
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithPriceViolatingDecimalConstraintShouldThrowException() {
        Product product = buildProduct();
        product.setPrice(BigDecimal.valueOf(43.932));
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithMoreThan10DigitsFails() {
        Product product = buildProduct();
        product.setPrice(new BigDecimal("322538923244.59"));
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithNullNameThrowsDataIntegrityException() {
        Product product = buildProduct();
        product.setName(null);
        assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithNullDescriptionThrowsDataIntegrityException() {
        Product product = buildProduct();
        product.setDescription(null);
        assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithNullBrandThrowsDataIntegrityException() {
        Product product = buildProduct();
        product.setDescription(null);
        assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithEmptyStringThrowsConstraintException() {
        Product product = buildProduct();
        product.setName("");
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithNameOfFourCharacterStringThrowsConstraintException() {
        Product product = buildProduct();
        product.setName("1234");
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }

    @Test
    public void saveProductWithEmptyDescriptionThrowsConstraintException() {
        Product product = buildProduct();
        product.setDescription("");
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }

    public Product buildProduct() {
        Product product = new Product();
        product.setName("sample name");
        product.setStock(10);
        product.setPrimeEligible(false);
        product.setDescription("sample description");
        product.setBrand("sample brand");
        product.setPrice(new BigDecimal("4.99"));
        return product;
    }

}
