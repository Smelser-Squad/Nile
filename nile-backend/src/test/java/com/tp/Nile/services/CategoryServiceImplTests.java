package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTests {

    @Mock
    private CategoryRepository repo;

    @InjectMocks
    private CategoryServiceImpl service;

    @Test
    public void testGetAllCategoriesGoldenPath() {
        when(repo.findAll()).thenReturn(List.of(new Category(1, "Electronic", new ArrayList<>())));
        List<Category> categories = service.getAllCategories();

        assertThat(categories)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(category -> {
                    assertThat(category.getCategoryId()).isEqualTo(1);
                    assertThat(category.getName()).isEqualTo("Electronic");
                    assertThat(category.getProducts().size()).isEqualTo(0);
                });
    }

    @Test
    public void testGetCategoryByIdGoldenPath() {
        when(repo.findById(1)).thenReturn(Optional.of(new Category(1, "Electronic", new ArrayList<>())));
        Category category = null;
        try {
            category = service.getCategoryById(1);
        } catch (NullCategoryIdException | InvalidCategoryIdException ex) {
            fail("Exception thrown");
        }
        assertThat(category)
                .isNotNull()
                .isInstanceOf(Category.class)
                .hasFieldOrPropertyWithValue("categoryId", 1)
                .hasFieldOrPropertyWithValue("name", "Electronic");
        assertThat(category.getProducts())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testGetCategoryByIdNullCategoryId() {
        try {
            Category category = service.getCategoryById(null);
            failBecauseExceptionWasNotThrown(NullCategoryIdException.class);
        } catch (InvalidCategoryIdException ex) {
            fail("Wrong exception thrown");
        } catch (NullCategoryIdException ex) {

        }
    }

    @Test
    public void testGetCategoryByIdInvalidCategoryId() {
        when(repo.findById(Integer.MIN_VALUE)).thenReturn(Optional.empty());
        try {
            Category category = service.getCategoryById(Integer.MIN_VALUE);
            failBecauseExceptionWasNotThrown(InvalidCategoryIdException.class);
        } catch (NullCategoryIdException ex) {
            fail("Wrong exception thrown");
        } catch (InvalidCategoryIdException ex) {

        }
    }

    @Test
    public void testAddCategoryGoldenPath() {
        int id = 1;
        Category newCategory = new Category();
        newCategory.setName("Electronic");
        when(repo.saveAndFlush(newCategory)).thenReturn(newCategory);
        Category addedCategory = null;
        try {
            addedCategory = service.addCategory(newCategory);
        } catch (Exception ex) {
            fail("Exception thrown");
        }
        addedCategory.setCategoryId(id);
        assertThat(addedCategory)
                .isNotNull()
                .isInstanceOf(Category.class)
                .hasFieldOrPropertyWithValue("categoryId", 1)
                .hasFieldOrPropertyWithValue("name", "Electronic");
        assertThat(addedCategory.getProducts())
                .isNotNull()
                .isEmpty();
    }

}
