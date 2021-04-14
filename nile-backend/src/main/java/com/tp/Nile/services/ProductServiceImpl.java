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

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getProductByBrand(String brand) {
        return repo.findProductsByBrand(brand);
    }
    @Override
    public List<Product> getProductsByCategory(Category categoryId) {
        return repo.getProductsByCategory(categoryId);
    }

    @Override
    public List<Product> getProductsByVendor(Vendor vendorId) {
        return repo.getProductsByVendor(vendorId);
    }

    @Override
    public List<Product> getProductsByType(Type type) {
        return repo.getProductsByType(type);
    }

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

    public Product addProduct(Product newProduct) throws NullProductObjectException, NullBrandException, NullNameException,NullDescriptionException,NullPriceException,InvalidPriceException{
        if(newProduct==null){
            throw new NullProductObjectException("Cannot add null product");
        }
        if(newProduct.getBrand()==null){
            throw new NullBrandException("Cannot add with null brand");
        }
        if(newProduct.getName()==null){
            throw new NullNameException("Cannot add with null name");
        }
        if(newProduct.getDescription()==null){
            throw new NullDescriptionException("Cannot add with null description");
        }
        if(newProduct.getPrice()==null){
            throw new NullPriceException("Cannot add with null price");
        }
        if(newProduct.getPrice()<0){
            throw new InvalidPriceException("Cannot add with price less than 0");
        }
        return repo.saveAndFlush(newProduct);
    }

    public Product updateProduct(Product updatedProduct) {
        Product edited=repo.findById(updatedProduct.getProductId()).get();

        if(edited!=null){
            edited.setName(updatedProduct.getName());
            edited.setBrand(updatedProduct.getBrand());
            edited.setPrice(updatedProduct.getPrice());
            edited.setDescription(updatedProduct.getDescription());

        }
        return repo.saveAndFlush(updatedProduct);
    }

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
