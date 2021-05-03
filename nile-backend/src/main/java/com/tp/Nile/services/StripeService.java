package com.tp.Nile.services;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.tp.Nile.controllers.Helper.ChargeRequest;
import com.tp.Nile.exceptions.InvalidStripeException;
import com.tp.Nile.repositories.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StripeService {
    @Autowired
    ChargeRepository repo;

    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    public StripeService() {
    }

    public String createCharge(ChargeRequest request) throws InvalidStripeException{
        String id = null;
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