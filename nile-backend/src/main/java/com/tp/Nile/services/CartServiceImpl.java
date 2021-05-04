package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidCartIdException;
import com.tp.Nile.exceptions.InvalidUserIdException;
import com.tp.Nile.exceptions.NullCartIdException;
import com.tp.Nile.exceptions.NullUserIdException;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.User;
import com.tp.Nile.repositories.CartRepository;
import com.tp.Nile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Cart> getBoughtCartsByUserId(Integer userId) throws NullUserIdException, InvalidUserIdException {
        List<Cart> boughtCarts = new ArrayList<>();

        if(userId == null){
            throw new NullUserIdException("Cannot get carts by user id with null id");
        }
        Optional<User> user = uRepo.findById(Long.valueOf(userId));

        if(user.isPresent()){
            List<Cart> carts = repo.findByUser(user.get());
            for(Cart cart : carts){
                if(cart.getStatus().equals("Ordered")){
                    boughtCarts.add(cart);
                }
            }
            return boughtCarts;
        }else{
            throw new InvalidUserIdException("User with that id does not exist");
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
