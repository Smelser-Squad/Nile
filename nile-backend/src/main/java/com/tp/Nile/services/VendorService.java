package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.InvalidVendorIdException;
import com.tp.Nile.exceptions.NullTypeIdException;
import com.tp.Nile.exceptions.NullVendorIdException;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    Vendor getVendorById(Integer typeId) throws NullVendorIdException, InvalidVendorIdException;
}
