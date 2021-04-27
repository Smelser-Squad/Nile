package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.ProductSpecification;
import com.tp.Nile.models.ProductSpecificationId;

import javax.lang.model.util.Types;
import java.util.List;

public interface ProductSpecificationService {

    List<ProductSpecification> getAllProductSpecs();
    List<ProductSpecification> getProductSpecsByProduct(Integer productId);
    List<ProductSpecification> getProductSpecsBySpec(Integer specId);
    ProductSpecification getProductSpecById(Integer productId, Integer specId) throws NullProductIdException, NullSpecIdException, InvalidProductSpecIdException;
    ProductSpecification addProductSpec(ProductSpecification newProductSpec) throws NullProductIdException, InvalidProductIdException, NullSpecIdException, InvalidSpecIdException, NullProductSpecIdObjectException, NullSpecValueException, InvalidSpecValueException, TypesDoNotMatchException;
    ProductSpecification updateProductSpec(ProductSpecification updatedProductSpec) throws NullProductIdException, InvalidProductIdException, NullSpecIdException, InvalidSpecIdException, NullProductSpecIdObjectException, NullSpecValueException, InvalidSpecValueException, InvalidProductSpecIdException;
    boolean deleteProductSpec(Integer productId, Integer specId) throws NullProductIdException, NullSpecIdException, InvalidProductSpecIdException;

}
