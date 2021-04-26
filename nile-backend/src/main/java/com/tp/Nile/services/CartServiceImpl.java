package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidCartIdException;
import com.tp.Nile.exceptions.NullCartIdException;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.User;
import com.tp.Nile.repositories.CartRepository;
import com.tp.Nile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository repo;

    @Autowired
    UserRepository uRepo;


    @Override
    public List<Cart> getAllCarts() {
        return repo.findAll();
    }

    @Override
    public Cart getCartById(Integer cartId) throws NullCartIdException, InvalidCartIdException {
        if(cartId == null){
            throw new NullCartIdException("Cannot get cart with null id");
        }
        Cart retrieved = null;
        Optional<Cart> cart = repo.findById(cartId);
        if(cart.isPresent()){
            retrieved=cart.get();
            return retrieved;
        }else{
            throw new InvalidCartIdException("Cart with that id does not exist");
        }
    }

    @Override
    public Cart addCart(Cart newCart) {
        User user = newCart.getUser();
        if(user.getUserId() == null){
            user = uRepo.saveAndFlush(user);
            newCart.setUser(user);
        }
        Cart cart = repo.saveAndFlush(newCart);

        return cart;
    }

    @Override
    public Cart updateCart(Cart updatedCart) {
        return repo.saveAndFlush(updatedCart);
    }

    @Override
    public boolean deleteCart(Integer cartId) throws NullCartIdException, InvalidCartIdException {
        if(cartId == null){
            throw new NullCartIdException("Cannot delete cart with null id");
        }
        Cart retrieved = repo.findById(cartId).get();
        if(retrieved != null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidCartIdException("Cart with that id does not exist");
        }
    }
}
