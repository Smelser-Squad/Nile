package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidVendorIdException;
import com.tp.Nile.exceptions.NullVendorIdException;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    VendorRepository repo;
    @Override
    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }

    @Override
    public Vendor getVendorById(Integer typeId) throws NullVendorIdException, InvalidVendorIdException {
        return null;
    }
}
