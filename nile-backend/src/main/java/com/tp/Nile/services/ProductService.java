package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvaildProductIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.Product;


import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getProductByBrand(String brand);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByVendor(Integer vendorId);
    List<Product> getProductsByType(String Type);
    Product getProductById(Integer productId) throws NullProductIdException, InvaildProductIdException;
    Product addProduct(Product newProduct);
    Product updateProduct(Product updatedProduct);
    boolean deleteProduct(Integer productId) throws NullProductIdException,InvaildProductIdException;



}
