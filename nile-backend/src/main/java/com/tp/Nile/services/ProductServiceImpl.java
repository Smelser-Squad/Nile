package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvaildProductIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repo;
    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return repo.getProductsByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return repo.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByVendor(Integer vendorId) {
        return repo.getProductsByVendor(vendorId);
    }

    @Override
    public List<Product> getProductsByType(Type type) {
        return repo.getProductsByType(type);
    }

    @Override
    public Product getProductById(Integer productId) throws NullProductIdException,InvaildProductIdException {
        if(productId==null){
            throw new NullProductIdException("Cannot get product with null id");
        }
        Product retrieved=null;
        Optional<Product> product=repo.findById(productId);
        if(product.isPresent()){
            retrieved=product.get();
            return retrieved;
        }else{
            throw new InvaildProductIdException("Product with that id does not exist");
        }

    }

    @Override
    public Product addProduct(Product newProduct) {

        return repo.saveAndFlush(newProduct);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {

        return repo.saveAndFlush(updatedProduct);
    }

    @Override
    public boolean deleteProduct(Integer productId) throws NullProductIdException, InvaildProductIdException {
        if(productId==null){
            throw new NullProductIdException("Cannot delete product with null id");
        }
        Product retreived=repo.findById(productId).get();
        if(retreived!=null){
            repo.delete(retreived);
            return true;
        }else{
            throw new InvaildProductIdException("Product with that id does not exist");
        }
    }
}
