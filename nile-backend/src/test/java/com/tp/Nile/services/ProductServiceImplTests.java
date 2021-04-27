package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductServiceImpl service;

    @InjectMocks
    TypeServiceImpl typeService;

    @InjectMocks
    VendorServiceImpl vendorService;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Test
    public void testGetAllProductsGoldenPath() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setName("Electronic");
        Type type = new Type();
        type.setTypeId(1);
        type.setTypeName("Test Type");
        Vendor vendor = new Vendor();
        vendor.setVendorId(1);
        vendor.setName("Best Buy");

        when(repo.findAll()).thenReturn(List.of(new Product(1, category, vendor, type, BigDecimal.valueOf(50.0), "Echo Dot", "description", "Amazon", 20, true,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())));


        List<Product> products = service.getAllProducts();

        assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(product -> {
                    assertThat(product.getProductId()).isEqualTo(1);
                    assertThat(product.getName()).isEqualTo("Echo Dot");
                    assertThat(product.getBrand()).isEqualTo("Amazon");
                    assertThat(product.getDescription()).isEqualTo("description");
                    assertThat(product.getCategory().getCategoryId()).isEqualTo(1);
                    assertThat(product.getCategory().getName()).isEqualTo("Electronic");
                    assertThat(product.getType().getTypeId()).isEqualTo(1);
                    assertThat(product.getType().getTypeName()).isEqualTo("Test Type");
                    assertThat(product.getVendor().getVendorId()).isEqualTo(1);
                    assertThat(product.getVendor().getName()).isEqualTo("Best Buy");
                    assertThat(product.getPrice()).isEqualTo(BigDecimal.valueOf(50.0));
                    assertThat(product.isPrimeEligible()).isEqualTo(true);
                    assertThat(product.getStock()).isEqualTo(20);
                });
    }

    @Test
    public void testGetProductByIdGoldenPath() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setName("Electronic");
        Type type = new Type();
        type.setTypeId(1);
        type.setTypeName("Test Type");
        Vendor vendor = new Vendor();
        vendor.setVendorId(1);
        vendor.setName("Best Buy");

        when(repo.findById(1)).thenReturn(Optional.of(new Product(1, category, vendor, type, BigDecimal.valueOf(50.0), "Echo Dot", "description", "Amazon", 20, true,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())));


        Product product = null;
        try {
            product = service.getProductById(1);
        } catch (InvalidProductIdException ex) {
            fail("Exception thrown");
        }
        assertThat(product)
                .isNotNull()
                .isInstanceOf(Product.class)
                .hasFieldOrPropertyWithValue("productId", 1)
                .hasFieldOrPropertyWithValue("name", "Echo Dot")
                .hasFieldOrPropertyWithValue("description", "description")
                .hasFieldOrPropertyWithValue("brand", "Amazon")
                .hasFieldOrPropertyWithValue("stock", 20)
                .hasFieldOrPropertyWithValue("price", BigDecimal.valueOf(50.0));

    }


    @Test
    public void testGetProductByIdInvalidProductId() {
        when(repo.findById(Integer.MIN_VALUE)).thenReturn(Optional.empty());
        try {
            Product product = service.getProductById(Integer.MIN_VALUE);
            failBecauseExceptionWasNotThrown(InvalidProductIdException.class);
        } catch (InvalidProductIdException ex) {

        } catch (Exception ex) {
            fail("Wrong exception thrown");
        }
    }

//    @Test
//    public void testAddProductGoldenPath() {
//        int id = 1;
//        Category category = new Category();
//        category.setName("Electronic");
//        Type type = new Type();
//        type.setTypeName("Test Type");
//        Vendor vendor = new Vendor();
//        vendor.setName("Best Buy");
//        Product newProduct = new Product();
//        newProduct.setName("Echo Dot");
//        newProduct.setDescription("description");
//        newProduct.setBrand("Amazon");
//        newProduct.setPrice(BigDecimal.valueOf(50.0));
//        newProduct.setStock(20);
//        newProduct.setPrimeEligible(true);
//        newProduct.setType(type);
//        newProduct.setCategory(category);
//        newProduct.setVendor(vendor);
//        when(repo.saveAndFlush(newProduct)).thenReturn(newProduct);
//        Product addedProduct = null;
//        try {
//            addedProduct = service.addProduct(newProduct);
//        } catch (Exception ex) {
//            fail("Exception thrown: " + ex);
//        }
//        addedProduct.setProductId(id);
//        assertThat(addedProduct)
//                .isNotNull()
//                .isInstanceOf(Product.class)
//                .hasFieldOrPropertyWithValue("productId", 1)
//                .hasFieldOrPropertyWithValue("name", "Echo Dot")
//                .hasFieldOrPropertyWithValue("description", "description")
//                .hasFieldOrPropertyWithValue("brand", "Amazon")
//                .hasFieldOrPropertyWithValue("stock", 20)
//                .hasFieldOrPropertyWithValue("price", BigDecimal.valueOf(50.0));
//    }


}
