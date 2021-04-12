package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.category=:category")
    List<Product> getProductsByCategory(@Param("category") String category);

    @Query("select p from Product p where p.brand=:brand")
    List<Product> getProductsByBrand(@Param("brand") String brand);

    @Query("select p from Product p where p.vendor_id=:vendorId")
    List<Product> getProductsByVendor(@Param("vendorId") Integer vendorId);

    @Query("select p from Product p where p.type_id=:type")
    List<Product> getProductsByType(@Param("type") Type Type);
}
