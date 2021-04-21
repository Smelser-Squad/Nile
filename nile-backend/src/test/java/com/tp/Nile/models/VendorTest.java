package com.tp.Nile.models;

import com.tp.Nile.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VendorTest {

    @Autowired
    VendorRepository repo;

    @BeforeEach
    public void setUp(){
        Vendor toTest=new Vendor(1,"Amazon");
        repo.save(toTest);
    }
    @Test
    @Rollback(true)
    public void addVendorTest() {
        Vendor newVendor = new Vendor();
        newVendor.setName("new vendor");
//        newVendor.setProducts(new ArrayList<>());
        Vendor added = repo.save(newVendor);
        assertNotNull(added);
        assertEquals(2, added.getVendorId());
        assertEquals("new vendor", added.getName());
//        assertEquals(0, added.getProducts().size());
    }

    @Test
    public void getAllVendorGoldenPathTest(){
    List<Vendor> allVendors = repo.findAll();
    assertEquals(1, allVendors.size());
}
    @Test
    public void getVendorNoVendorFoundTest() {
        Optional<Vendor> retrieved = repo.findById(Integer.MAX_VALUE);
        if (retrieved.isPresent()) {
            fail();
        }
    }

    @Test
    public void deleteVendorTest() {
        Vendor toDelete =new Vendor(1,"Amazon");
        repo.delete(toDelete);
        assertEquals(0, repo.findAll().size());
    }
}
