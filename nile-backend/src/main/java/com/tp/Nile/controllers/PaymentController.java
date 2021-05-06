package com.tp.Nile.controllers;

import com.tp.Nile.controllers.Helper.ChargeRequest;
import com.tp.Nile.controllers.Helper.ChargeRequestHelper;
import com.tp.Nile.exceptions.InvalidStripeException;
import com.tp.Nile.services.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create-charge")
    public ResponseEntity createCharge(@RequestBody ChargeRequestHelper request) {
        try {
            return ResponseEntity.ok(stripeService.createCharge(request));
        }
        catch (InvalidStripeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/charges")
    public ResponseEntity getAllTypes(){
        return ResponseEntity.ok(stripeService.getAllCharges());
    }

    @GetMapping("/charge/{chargeId}")
    public ResponseEntity getChargeById(@PathVariable Integer chargeId) {
        try {
            return ResponseEntity.ok(stripeService.getChargeById(chargeId));
        } catch (InvalidStripeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}