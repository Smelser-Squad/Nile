package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.ProductSpecification;
import com.tp.Nile.models.Specification;
import com.tp.Nile.repositories.ProductSpecificationRepository;
import com.tp.Nile.services.ProductSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

    @Autowired
    ProductSpecificationRepository repo;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    SpecificationServiceImpl specService;

    @Override
    public List<ProductSpecification> getAllProductSpecs() {
        return repo.findAll();
    }

    @Override
    public List<ProductSpecification> getProductSpecsByProduct(Integer productId) {
        return repo.getProductSpecsByProduct(productId);
    }

    @Override
    public List<ProductSpecification> getProductSpecsBySpec(Integer specId) {
        return repo.getProductSpecsBySpec(specId);
    }

    @Override
    public ProductSpecification getProductSpecById(Integer productId, Integer specId) throws NullProductIdException, NullSpecIdException, InvalidProductSpecIdException {
        if (productId == null) {
            throw new NullProductIdException("Product id cannot be null");
        }
        if (specId == null) {
            throw new NullSpecIdException("Spec id cannot be null");
        }
        ProductSpecification retrieved = null;
        Optional<ProductSpecification> productSpec = repo.findByIdProductIdAndIdSpecId(productId, specId);
        if (productSpec.isPresent()) {
            retrieved = productSpec.get();
            return retrieved;
        } else {
            throw new InvalidProductSpecIdException("Product spec with that id does not exist");
        }
    }

    @Override
    public ProductSpecification addProductSpec(ProductSpecification newProductSpec) throws NullProductIdException, InvalidProductIdException, NullSpecIdException, InvalidSpecIdException, NullProductSpecIdObjectException, NullSpecValueException, InvalidSpecValueException {
        if (newProductSpec.getId() == null) {
            throw new NullProductSpecIdObjectException("Id object cannot be null");
        }
        if (newProductSpec.getValue() == null) {
            throw new NullSpecValueException("Value cannot be null");
        }
        if (newProductSpec.getValue() == "") {
            throw new InvalidSpecValueException("Value cannot be empty");
        }
        Product product = productService.getProductById(newProductSpec.getId().getProductId());
        newProductSpec.setProduct(product);
        Specification spec = specService.getSpecById(newProductSpec.getId().getSpecId());
        newProductSpec.setSpec(spec);
        return repo.saveAndFlush(newProductSpec);
    }

    @Override
    public ProductSpecification updateProductSpec(ProductSpecification updatedProductSpec) throws NullProductIdException, InvalidProductIdException, NullSpecIdException, InvalidSpecIdException, NullProductSpecIdObjectException, NullSpecValueException, InvalidSpecValueException, InvalidProductSpecIdException {
        if (updatedProductSpec.getId() == null) {
            throw new NullProductSpecIdObjectException("Id object cannot be null");
        }
        if (updatedProductSpec.getValue() == null) {
            throw new NullSpecValueException("Value cannot be null");
        }
        if (updatedProductSpec.getValue() == "") {
            throw new InvalidSpecValueException("Value cannot be empty");
        }
        ProductSpecification retrieved = getProductSpecById(updatedProductSpec.getId().getProductId(), updatedProductSpec.getId().getSpecId());
        retrieved.setValue(updatedProductSpec.getValue());
        return repo.saveAndFlush(retrieved);
    }

    @Override
    public boolean deleteProductSpec(Integer productId, Integer specId) throws NullProductIdException, NullSpecIdException, InvalidProductSpecIdException {
        ProductSpecification retrieved = null;
        retrieved = getProductSpecById(productId, specId);
        repo.delete(retrieved);
        return true;
    }
}
