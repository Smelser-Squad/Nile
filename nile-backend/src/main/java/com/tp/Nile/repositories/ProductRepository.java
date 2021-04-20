package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.category.name=:category")
    List<Product> getProductsByCategory(@Param("category") String category);

    @Query("select p from Product p where p.brand=:brand")
    List<Product> findProductsByBrand(@Param("brand") String brand);

    @Query("select p from Product p where p.vendor.name=:vendor")
    List<Product> getProductsByVendor(@Param("vendor") String vendor);

    @Query("select p from Product p where p.type.typeName=:type")
    List<Product> getProductsByType(@Param("type") String type);

}
