package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvaildProductIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;


import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getProductByBrand(String brand);
    List<Product> getProductsByCategory(Category categoryId);
    List<Product> getProductsByVendor(Vendor vendorId);
    List<Product> getProductsByType(Type Type);
    Product getProductById(Integer productId) throws NullProductIdException, InvaildProductIdException;
    Product addProduct(Product newProduct);
    Product updateProduct(Product updatedProduct);
    boolean deleteProduct(Integer productId) throws NullProductIdException,InvaildProductIdException;



}
