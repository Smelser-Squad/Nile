package com.tp.Nile.services;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.tp.Nile.controllers.Helper.ChargeRequest;
import com.tp.Nile.exceptions.InvalidStripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    public StripeService() {
    }

    public String createCharge(ChargeRequest request) throws InvalidStripeException{
        String id = null;
        try {
            Stripe.apiKey = API_SECRET_KEY;
            Map<String, Object> chargeParams = new HashMap<>();

//            chargeParams.put("amount", 200);
            chargeParams.put("amount", request.getAmount());

            chargeParams.put("currency", "usd");
//            chargeParams.put("description", "Charge for " + email);
            chargeParams.put("source", "tok_visa"); // ^ obtained with Stripe.js


//            chargeParams.put("source", "tok_visa");

            //create a charge
            Charge charge = Charge.create(chargeParams);
            id = charge.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

}