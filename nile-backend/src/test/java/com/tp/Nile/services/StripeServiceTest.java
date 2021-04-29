//package com.tp.Nile.services;
//import com.stripe.Stripe;
//import com.tp.Nile.controllers.Helper.ChargeRequest;
//import com.tp.Nile.exceptions.InvalidStripeException;
//import com.tp.Nile.repositories.ChargeRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Value;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//
//public class StripeServiceTest {
//
//    @Mock
//    ChargeRepository repo;
//
//    @InjectMocks
//    StripeService service;
//
//    @Value("${stripe.keys.secret}")
//    private String API_SECRET_KEY = "sk_test_51IiMSjC3X35blG5opYFVTZQW7PVfmAMYf3d2svhTv5RBREzy66HL0pnAgqPKn1bjFch1I9B1Hgb4HR7n6KirJWiS008mIM2BV1";
//
//    @Test
//    public void createChargeTest(){
//        String id = null;
//
////        Stripe.apiKey = API_SECRET_KEY;
//
//        ChargeRequest request = new ChargeRequest(1, "token", 200,"yesrat@gmail.com");
//
//        when(repo.saveAndFlush(any(ChargeRequest.class))).thenReturn(request);
//       try{
//           Stripe.apiKey = API_SECRET_KEY;
//
//           id = service.createCharge(request);
//
//       }catch(InvalidStripeException ex){
//           fail();
//
//        }
//       assertThat(id).isNotNull();
//
//    }
//
//
//}
