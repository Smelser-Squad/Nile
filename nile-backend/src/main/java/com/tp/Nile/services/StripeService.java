package com.tp.Nile.services;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.tp.Nile.controllers.Helper.ChargeRequest;
import com.tp.Nile.controllers.Helper.ChargeRequestHelper;
import com.tp.Nile.exceptions.InvalidStripeException;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.CartProduct;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.User;
import com.tp.Nile.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class StripeService {
    @Autowired
    ChargeRepository repo;

    @Autowired
    CartProductRepository cpRepo;

    @Autowired
    CartRepository cRepo;

    @Autowired
    ProductRepository pRepo;

    @Autowired
    UserRepository uRepo;

    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    public StripeService() {
    }

    public StripeService(ChargeRepository repo) {
        this.repo = repo;
    }

    public String createCharge(ChargeRequestHelper requestHelper) throws InvalidStripeException{
        String id = null;
        ChargeRequest request = new ChargeRequest();
        request.setToken(requestHelper.getToken());
        request.setEmail(requestHelper.getEmail());
        request.setAmount(requestHelper.getAmount());
        List<Object> objectProductId = requestHelper.getCartProductId();
        List<Integer> productId = new ArrayList<>();
        for(Object object: objectProductId){
            int i = (Integer) object;
            productId.add(i);
        }
        Cart cart = new Cart();
        cart.setCartProducts(new ArrayList<>());
        cart.setStatus("Ordered");
        cart.setPurchaseDate(LocalDate.now());

        Optional<User> user = uRepo.findById(1L);
        if(user.isPresent()){
            cart.setUser(user.get());
        }

        for(Integer i : productId){
            Optional<Product> product = pRepo.findById(i);
            if(product.isPresent()){
                CartProduct cartProduct = new CartProduct();
                cartProduct.setCart(cart);
                cartProduct.setProduct(product.get());
//                cartProduct = cpRepo.saveAndFlush(cartProduct);
                cart.getCartProducts().add(cartProduct);
            }
        }

        cart = cRepo.saveAndFlush(cart);
        for(CartProduct cartProduct : cart.getCartProducts()){
            cartProduct.setCart(cart);
            cartProduct.setQuantity(1);
            cpRepo.saveAndFlush(cartProduct);
        }

        try {
            Stripe.apiKey = API_SECRET_KEY;
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", request.getAmount());
            chargeParams.put("currency", "usd");
            chargeParams.put("description", "Charge for " + request.getEmail());
            chargeParams.put("source", "tok_visa"); // ^ obtained with Stripe.js
            //create a charge
            Charge charge = Charge.create(chargeParams);
            id = charge.getId();
            request.setCart(cart);
            repo.saveAndFlush(request);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }


    public List<ChargeRequest> getAllCharges() {
        return repo.findAll();
    }

    public ChargeRequest getChargeById(Integer chargeId) throws InvalidStripeException {
        if(chargeId==null){
            throw new InvalidStripeException("Cannot get charge with null id");
        }
        ChargeRequest retrieved=null;

        Optional<ChargeRequest> chargeRequest=repo.findById(chargeId);
        if(chargeRequest.isPresent()){
            retrieved=chargeRequest.get();
            return retrieved;
        }else{
            throw new InvalidStripeException("Charge with that id does not exist");
        }

    }

}