package com.tp.Nile.repositories;

import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(Category category);

    List<Product> findByBrand(String brand);

//    List<Product> findByVendorId(Integer vendorId);

    List<Product> findByType(String type);
}
