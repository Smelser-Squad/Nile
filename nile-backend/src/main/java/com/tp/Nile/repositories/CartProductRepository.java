package com.tp.Nile.repositories;

import com.tp.Nile.models.Cart;
import com.tp.Nile.models.CartProduct;
import com.tp.Nile.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile({ "dev", "test" })
public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {

}
