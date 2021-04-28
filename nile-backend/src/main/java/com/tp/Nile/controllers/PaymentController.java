package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidStripeException;
import com.tp.Nile.services.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class PaymentController {

    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }



    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }



//    @PostMapping("/create-charge")
//    public @ResponseBody
//    Response createCharge(String email, String token) {
//        //validate data
//        if (token == null) {
//            return new Response(false, "Stripe payment token is missing. Please, try again later.");
//        }
//
//        //create charge
//        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
//        if (chargeId == null) {
//            return new Response(false, "An error occurred while trying to create a charge.");
//        }
//
//        // You may want to store charge id along with order information
//
//        return new Response(true, "Success! Your charge id is " + chargeId);
//    }
//

    @PostMapping("/create-charge")
    public ResponseEntity createCharge(String email, String token, int amount) {
        try {
            return ResponseEntity.ok(stripeService.createCharge(email,token, amount));
        }
        catch (InvalidStripeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}