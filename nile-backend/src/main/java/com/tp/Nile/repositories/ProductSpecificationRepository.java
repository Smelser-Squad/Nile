package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.ProductSpecification;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile({"test", "dev"})
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Integer> {

    @Query("select ps from ProductSpecification ps where ps.product.productId=:product")
    List<ProductSpecification> getProductSpecsByProduct(@Param("product") Integer product);

    @Query("select ps from ProductSpecification ps where ps.spec.specId=:spec")
    List<ProductSpecification> getProductSpecsBySpec(@Param("spec") Integer spec);

    @Query("select ps from ProductSpecification ps where ps.id.productId=:productId and ps.id.specId=:specId")
    Optional<ProductSpecification> findByIdProductIdAndIdSpecId(@Param("productId") Integer productId, @Param("specId") Integer specId);
}
