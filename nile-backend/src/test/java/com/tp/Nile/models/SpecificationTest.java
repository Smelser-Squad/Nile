package com.tp.Nile.models;

import com.tp.Nile.repositories.SpecificationRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.ArrayList;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpecificationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpecificationRepository repo;

    Type setupType = new Type(1, "type name", new ArrayList<>());
    @BeforeEach
    public void setup() {
        Specification setupSpec = new Specification(1, "spec name", setupType);
        repo.saveAndFlush(setupSpec);
    }

    @Test
    @Rollback(true)
    @Transactional
    public void testAddSpecGoldenPath() {
        Specification newSpec = new Specification();
        Type newType = new Type(2, "new type", new ArrayList<>());
        newSpec.setSpecName("new spec");
        newSpec.setType(newType);
        Specification savedSpec = repo.saveAndFlush(newSpec);
        assertNotNull(savedSpec);
        assertEquals(2, savedSpec.getSpecId());
        assertEquals("new spec", savedSpec.getSpecName());
        assertEquals(2, savedSpec.getType().getTypeId());
        assertEquals("new type", savedSpec.getType().getTypeName());
    }

    @Test
    @Rollback(true)
    public void testAddSpecNullSpecName() {
        Specification newSpec = new Specification();
        newSpec.setType(setupType);
        try {
            Specification savedSpec = repo.saveAndFlush(newSpec);
            fail();
        } catch (Exception ex) {

        }
    }
}
