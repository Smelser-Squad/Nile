package com.tp.Nile.repositories;

import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.ProductPhoto;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface ProductPhotoRepository extends JpaRepository<ProductPhoto,Integer> {

    @Query("select p from ProductPhoto p where p.product.productId=:productId")
    List<ProductPhoto> getPhotosByProduct(@Param("productId") Integer productId);

    @Query("select p from ProductPhoto p where p.product.productId=:productId and p.color=:color")
    List<ProductPhoto> getPhotosByProductColor(@Param("productId") Integer productId, @Param("color") String color);

<<<<<<< HEAD

=======
>>>>>>> 9f26d696c5174afe4b7bb322042b579ba6533fce
    @Query("select distinct color as c from ProductPhoto p where p.product.productId=:productId")
    List<String> getColorsOfProduct(@Param("productId") Integer productId);

}

