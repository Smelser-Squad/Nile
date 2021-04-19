package com.tp.Nile.services;


import com.tp.Nile.exceptions.*;

import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    Vendor getVendorById(Integer typeId) throws NullVendorIdException, InvalidVendorIdException;
    Vendor addVendor(Vendor newVendor);
    Vendor upddateVendor(Vendor updatedVendor);
    boolean deleteVendor(Integer vendorId) throws NullVendorIdException, InvalidVendorIdException;
}
