package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidVendorIdException;
import com.tp.Nile.exceptions.NullVendorIdException;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.repositories.VendorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VendorServiceImplTests {

    @Mock
    private VendorRepository repo;

    @InjectMocks
    private VendorServiceImpl service;

    @Test
    public void testGetAllVendorsGoldenPath() {
        when(repo.findAll()).thenReturn(List.of(new Vendor(1, "Best Buy")));
        List<Vendor> vendors = service.getAllVendors();

        assertThat(vendors)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(vendor -> {
                    assertThat(vendor.getVendorId()).isEqualTo(1);
                    assertThat(vendor.getName()).isEqualTo("Best Buy");
                });
    }

    @Test
    public void testGetVendorByIdGoldenPath() {
        when(repo.findById(1)).thenReturn(Optional.of(new Vendor(1, "Best Buy")));
        Vendor vendor = null;
        try {
            vendor = service.getVendorById(1);
        } catch (NullVendorIdException | InvalidVendorIdException ex) {
            fail("Exception thrown");
        }
        assertThat(vendor)
                .isNotNull()
                .isInstanceOf(Vendor.class)
                .hasFieldOrPropertyWithValue("vendorId", 1)
                .hasFieldOrPropertyWithValue("name", "Best Buy");
    }

    @Test
    public void testGetVendorByIdNullVendorId() {
        try {
            Vendor vendor = service.getVendorById(null);
            failBecauseExceptionWasNotThrown(NullVendorIdException.class);
        } catch (InvalidVendorIdException ex) {
            fail("Wrong exception thrown");
        } catch (NullVendorIdException ex) {

        }
    }

    @Test
    public void testGetVendorByIdInvalidVendorId() {
        when(repo.findById(Integer.MIN_VALUE)).thenReturn(Optional.empty());
        try {
            Vendor vendor = service.getVendorById(Integer.MIN_VALUE);
            failBecauseExceptionWasNotThrown(InvalidVendorIdException.class);
        } catch (NullVendorIdException ex) {
            fail("Wrong exception thrown");
        } catch (InvalidVendorIdException ex) {

        }
    }

    @Test
    public void testAddVendorGoldenPath() {
        int id = 1;
        Vendor newVendor = new Vendor();
        newVendor.setName("Best Buy");
        when(repo.saveAndFlush(newVendor)).thenReturn(newVendor);
        Vendor addedVendor = null;
        try {
            addedVendor = service.addVendor(newVendor);
        } catch (Exception ex) {
            fail("Exception thrown");
        }
        addedVendor.setVendorId(id);
        assertThat(addedVendor)
                .isNotNull()
                .isInstanceOf(Vendor.class)
                .hasFieldOrPropertyWithValue("vendorId", 1)
                .hasFieldOrPropertyWithValue("name", "Best Buy");
    }

}
