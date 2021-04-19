package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;



import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getProductByBrand(String brand);
    List<Product> getProductsByCategory(String categoryId);
    List<Product> getProductsByVendor(String vendor);
    List<Product> getProductsByType(String Type);
    Product getProductById(Integer productId) throws NullProductIdException, InvalidProductIdException;
    Product addProduct(Product product);
    Product updateProduct(Product updatedProduct);
    void deleteProduct(Integer productId) throws InvalidProductIdException;



}
