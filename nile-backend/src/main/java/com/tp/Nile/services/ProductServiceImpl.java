package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;

    @Autowired
    TypeServiceImpl typeService;

    @Autowired
    VendorServiceImpl vendorService;

    @Autowired
    CategoryServiceImpl categoryService;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getProductByBrand(String brand) {
        return repo.findProductsByBrand(brand);
    }
    @Override
    public List<Product> getProductsByCategory(String category) {
        return repo.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByVendor(String vendor) {
        return repo.getProductsByVendor(vendor);
    }

    @Override
    public List<Product> getProductsByType(String type) {
        return repo.getProductsByType(type);
    }

    public Product getProductById(Integer productId) throws InvalidProductIdException {
        Product retrieved=null;
        
        Optional<Product> product=repo.findById(productId);
        if(product.isPresent()){
            retrieved=product.get();
            return retrieved;
        }else{
            throw new InvalidProductIdException("Product with that id does not exist");
        }

    }

    public Product addProduct(Product product) throws MissingPropertyException {
        if (product.getCategory() != null) {
            Category category = product.getCategory();
            if (category.getCategoryId() != null) {
                try {

                    category = categoryService.getCategoryById(category.getCategoryId());
                    product.setCategory(category);
                } catch (NullCategoryIdException | InvalidCategoryIdException ex) {
                    throw new MissingPropertyException("Invalid category provided");
                }
            } else {
                category = categoryService.addCategory(category);
                product.setCategory(category);
            }
        } else {
            throw new MissingPropertyException("No category provided");
        }

        if (product.getVendor() != null) {
            Vendor vendor = product.getVendor();
            if (vendor.getVendorId() != null) {
                try {
                    vendor = vendorService.getVendorById(vendor.getVendorId());
                    product.setVendor(vendor);
                } catch (NullVendorIdException | InvalidVendorIdException ex) {
                    throw new MissingPropertyException("Invalid vendor provided");
                }
            } else {
                vendor = vendorService.addVendor(vendor);
                product.setVendor(vendor);
            }
        } else {
            throw new MissingPropertyException("No vendor provided");
        }

        if (product.getType() != null) {
            Type type = product.getType();
            if (type.getTypeId() != null) {
                try {
                    type = typeService.getTypeById(type.getTypeId());
                    product.setType(type);
                } catch (NullTypeIdException | InvalidTypeIdException ex) {
                    throw new MissingPropertyException("Invalid type provided");
                }
            } else {
                try {
                    type = typeService.addType(type);
                    product.setType(type);
                } catch (NullTypeNameException | EmptyTypeNameException ex) {
                    throw new MissingPropertyException("Invalid type provided");
                }
            }
        } else {
            throw new MissingPropertyException("No type provided");
        }
        return repo.saveAndFlush(product);
    }

    public Product updateProduct(Product updatedProduct) {
        return repo.save(updatedProduct);
    }

    public void deleteProduct(Integer productId) throws InvalidProductIdException {
        try {
            repo.deleteById(productId);
        } catch(Exception e) {
            throw new InvalidProductIdException("Product with that id does not exist");
        }
    }


}
