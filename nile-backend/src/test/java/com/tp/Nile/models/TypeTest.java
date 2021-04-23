package com.tp.Nile.models;

import static org.junit.jupiter.api.Assertions.*;
import com.tp.Nile.repositories.TypeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TypeTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TypeRepository repo;

    @BeforeEach
    public void setup() {
        Type setupType = new Type(1, "test type", new ArrayList<>());
        repo.save(setupType);
    }

    @Test
    @Rollback(true)
    public void testAddType() {
        Type newType = new Type();
        newType.setTypeName("new type");
        newType.setProducts(new ArrayList<>());
        Type savedType = repo.save(newType);
        assertNotNull(savedType);
        assertEquals(2, savedType.getTypeId());
        assertEquals("new type", savedType.getTypeName());
        assertEquals(0, savedType.getProducts().size());
    }

    @Test
    public void testGetAllTypes() {
        List<Type> allTypes = repo.findAll();
        assertEquals(1, allTypes.size());
    }

    @Test
    public void testGetTypeByIdGoldenPath() {
        Optional<Type> retrieved = repo.findById(1);
        if (!retrieved.isPresent()) {
            fail();
        } else {
            Type type = retrieved.get();
            assertEquals("test type", type.getTypeName());
            assertEquals(0, type.getProducts().size());
        }
    }

    @Test
    public void testGetTypeByIdNoTypeFound() {
        Optional<Type> retrieved = repo.findById(99);
        if (retrieved.isPresent()) {
            fail();
        }
    }

    @Test
    @Rollback(true)
    public void testDeleteType() {
        Type toDelete = new Type(1, "test type", new ArrayList<>());
        repo.delete(toDelete);
        assertEquals(0, repo.findAll().size());
    }
}
