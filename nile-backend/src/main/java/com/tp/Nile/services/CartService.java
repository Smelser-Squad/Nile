package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.*;

import java.util.List;


public interface CartService {

    List<Cart> getAllCarts();
    Cart getCartById(Integer cartId) throws NullCartIdException, InvalidCartIdException;
    List<Cart> getBoughtCartsByUserId(Integer userId) throws NullUserIdException, InvalidUserIdException;
    List<Product> getProductsInACartByCartId(Integer cartId) throws NullCartIdException, InvalidCartIdException;
    Cart addCart(Cart newCart);
    Cart updateCart(Cart updatedCart);
    boolean deleteCart(Integer cartId) throws NullCartIdException, InvalidCartIdException;



}
