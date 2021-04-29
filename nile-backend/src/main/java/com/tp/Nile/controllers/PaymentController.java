package com.tp.Nile.controllers;

import com.tp.Nile.controllers.Helper.ChargeRequest;
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

    @PostMapping("/create-charge")
    public ResponseEntity createCharge(@RequestBody ChargeRequest request) {
        try {
            return ResponseEntity.ok(stripeService.createCharge(request ));
        }
        catch (InvalidStripeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}