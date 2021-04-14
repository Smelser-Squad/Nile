package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidFeatureIdException;
import com.tp.Nile.exceptions.InvalidOrderIdException;
import com.tp.Nile.exceptions.NullFeatureIdException;
import com.tp.Nile.exceptions.NullOrderIdException;
import com.tp.Nile.models.Feature;
import com.tp.Nile.models.Order;
import com.tp.Nile.repositories.FeatureRepository;
import com.tp.Nile.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository repo;


    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    @Override
    public Order getOrderById(Integer orderId) throws NullOrderIdException, InvalidOrderIdException {
        if(orderId == null){
            throw new NullOrderIdException("Cannot get order with null id");
        }
        Order retrieved = null;
        Optional<Order> order = repo.findById(orderId);
        if(order.isPresent()){
            retrieved=order.get();
            return retrieved;
        }else{
            throw new InvalidOrderIdException("Order with that id does not exist");
        }
    }

    @Override
    public Order addOrder(Order newOrder) {
        return repo.saveAndFlush(newOrder);
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
        return repo.saveAndFlush(updatedOrder);
    }

    @Override
    public boolean deleteOrder(Integer orderId) throws NullOrderIdException, InvalidOrderIdException {
        if(orderId == null){
            throw new NullOrderIdException("Cannot delete order with null id");
        }
        Order retrieved = repo.findById(orderId).get();
        if(retrieved != null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidOrderIdException("Order with that id does not exist");
        }
    }
}
