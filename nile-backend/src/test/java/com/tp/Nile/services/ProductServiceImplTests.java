package com.tp.Nile.services;

import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductServiceImplTests {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductServiceImpl service;

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


}
