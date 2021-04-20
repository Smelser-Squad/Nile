package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    VendorRepository repo;
    @Override
    public List<Vendor> getAllVendors() {
        return repo.findAll();
    }

    @Override
    public Vendor getVendorById(Integer vendorId) throws NullVendorIdException, InvalidVendorIdException {
        if(vendorId==null){
                throw new NullVendorIdException("Cannot get vendor with null id");
            }
            Vendor retrieved = null;
            Optional<Vendor> vendor=repo.findById(vendorId);
            if(vendor.isPresent()){
                retrieved=vendor.get();
                return retrieved;
            }else{
                throw new InvalidVendorIdException("Vendor with that id does not exist");
            }
        }


    @Override
    public Vendor addVendor(Vendor newVendor) {
        return repo.saveAndFlush(newVendor);
    }

    @Override
    public Vendor updateVendor(Vendor updatedVendor) {
        return repo.saveAndFlush(updatedVendor);
    }

    @Override
    public boolean deleteVendor(Integer vendorId) throws NullVendorIdException, InvalidVendorIdException {
        if(vendorId == null){
            throw new NullVendorIdException("Cannot delete vendor with null id");
        }
        Vendor retrieved = repo.findById(vendorId).get();
        if(retrieved != null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidVendorIdException("Vendor with that id does not exist");
        }
    }
    }

