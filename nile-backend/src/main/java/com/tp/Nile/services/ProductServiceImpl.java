package com.tp.Nile.services;

import com.tp.Nile.controllers.requests.AddProductRequest;
import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.*;
import com.tp.Nile.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Product> getProductsByCategory(Category category) {
        return repo.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsByVendor(Vendor vendor) {
        return repo.getProductsByVendor(vendor);
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

    public Product addProduct(AddProductRequest product) throws NullProductObjectException, NullBrandException, NullNameException,NullDescriptionException,NullPriceException,InvalidPriceException{
        if(product==null){
            throw new NullProductObjectException("Cannot add null product");
        }
        if(product.getBrand()==null){
            throw new NullBrandException("Cannot add with null brand");
        }
        if(product.getName()==null){
            throw new NullNameException("Cannot add with null name");
        }
        if(product.getDescription()==null){
            throw new NullDescriptionException("Cannot add with null description");
        }
        if(product.getPrice()==null){
            throw new NullPriceException("Cannot add with null price");
        }
        if(product.getPrice()<0){
            throw new InvalidPriceException("Cannot add with price less than 0");
        }
        Product newProduct=new Product();
        Category category=categoryService.getCategoryById(product.getCategoryId());
        Type type=typeService.getTypeById(product.getTypeId());
        Vendor vendor=vendorService.getVendorById(product.getVendorId());

        Set<ProductFeature> features = new HashSet<>();
        for(Integer id:product.getFeatureId()){
            features.add(featureService.getFeatureById(id));
        }
        Set<Question>questions=new HashSet<>();
        for(Integer id:product.getQuestionId()){
            questions.add(questionService.getQuestionById(id));
        }
        List<ProductPhoto> photos=new ArrayList<>();
        for(Integer id:product.getPhotoId()){
            photos.add(photoService.getPhotoById(id));
        }

        newProduct.setPhotoList(photos);
        newProduct.setProductFeatures(features);
        newProduct.setQuestions(questions);
        newProduct.setCategory(category);
        newProduct.setVendor(vendor);
        newProduct.setType(type);

        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setBrand(product.getBrand());
        newProduct.setPrice(product.getPrice());



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
