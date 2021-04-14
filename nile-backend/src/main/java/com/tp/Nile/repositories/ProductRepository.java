package com.tp.Nile.repositories;

import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.category=:category")
    List<Product> getProductsByCategory(@Param("category") Category category);

    List<Product> findByCategory(Category category);

    List<Product> findByBrand(String brand);
    @Query("select p from Product p where p.vendor=:vendor")
    List<Product> getProductsByVendor(@Param("vendor") Vendor vendor);

    @Query("select p from Product p where p.type=:type")
    List<Product> getProductsByType(@Param("type") Type type);

}
