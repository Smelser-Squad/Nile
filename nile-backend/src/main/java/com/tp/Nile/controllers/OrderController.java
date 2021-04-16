package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidOrderIdException;
import com.tp.Nile.exceptions.NullOrderIdException;
import com.tp.Nile.models.Order;
import com.tp.Nile.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/orders")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    OrderServiceImpl service;


    @PostMapping()
    public ResponseEntity addOrder(@RequestBody Order order){
        return ResponseEntity.ok(service.addOrder(order));
    }
    @GetMapping()
    public ResponseEntity getOrders(){
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity getOrderById(@PathVariable Integer orderId) {
        try {
            return ResponseEntity.ok(service.getOrderById(orderId));
        } catch (NullOrderIdException | InvalidOrderIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateOrder(@RequestBody Order updatedOrder){
        return ResponseEntity.ok(service.updateOrder(updatedOrder));
    }
  
    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId){
        {
            String toReturn="";
            try {
                if (service.deleteOrder(orderId)) {
                    toReturn ="Order " + orderId + "deleted";
                }else{
                    toReturn="Order " + orderId + "not found";
                }
            }catch (InvalidOrderIdException | NullOrderIdException ex){
                ex.getMessage();
            }
            return  toReturn;
        }
    }
}
