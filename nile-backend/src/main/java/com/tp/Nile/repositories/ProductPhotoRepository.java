package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.ProductPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPhotoRepository extends JpaRepository<ProductPhoto,Integer> {
    List<ProductPhoto> getPhotosByProduct(Product product);
}
