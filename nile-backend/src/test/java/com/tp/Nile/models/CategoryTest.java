package com.tp.Nile.models;

import com.tp.Nile.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CategoryTest {

    @Autowired
    CategoryRepository repo;

    @BeforeEach
    public void setUp(){
        Category toTest=new Category(1,"Electronics",new ArrayList<>());
        repo.save(toTest);
    }
    @Test
    @Rollback(true)
    public void addCategoryTest() {
        Category newCategory = new Category();
        newCategory.setName("Phone");
        newCategory.setProducts(new ArrayList<>());
        Category added = repo.save(newCategory);
        assertNotNull(added);
        assertEquals(2, added.getCategoryId());
        assertEquals("Phone", added.getName());
        assertEquals(0,added.getProducts().size());

    }
    @Test
    public void getAllCategoriesrGoldenPathTest(){
        List<Category> allCategories = repo.findAll();
        assertEquals(1, allCategories.size());
    }
    @Test
    public void getCategoryNoCategoryFoundTest() {
        Optional<Category> retrieved = repo.findById(Integer.MAX_VALUE);
        if (retrieved.isPresent()) {
            fail();
        }
    }
    @Test
    public void getCategoryByIdGoldenPathTest() {
        Optional<Category> retrieved = repo.findById(1);
        if (!retrieved.isPresent()) {
            fail();
        } else {
            Category category = retrieved.get();
            assertEquals("Electronics", category.getName());
            assertEquals(0,category.getProducts().size());
        }
    }

    @Test
    public void deleteCategoryrTest() {
        Category toDelete =new Category(1,"Electronics", new ArrayList<>());
        repo.delete(toDelete);
        assertEquals(0, repo.findAll().size());
    }
}
