package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidProductIdException;
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

    @Autowired
    TypeServiceImpl typeService;

    @Autowired
    VendorServiceImpl vendorService;

    @Autowired
    CategoryServiceImpl categoryService;

   @Autowired
   FeatureServiceImpl featureService;

   @Autowired
   PhotoServiceImpl photoService;

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

    public Product addProduct(Product product) {
        return repo.saveAndFlush(product);
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

    public void deleteProduct(Integer productId) throws InvalidProductIdException {
        try {
            Product retrieved = repo.findById(productId).get();
            if (retrieved != null) {
                repo.delete(retrieved);
            }

        } catch(Exception e) {
            throw new InvalidProductIdException("Product with that id does not exist");
        }
    }
}
