package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.*;

import java.util.List;


public interface OrderService {

    List<Order> getAllOrders();
    Order getOrderById(Integer orderId) throws NullOrderIdException, InvalidOrderIdException;
    Order addOrder(Order newOrder);
    Order updateOrder(Order updatedOrder);
    boolean deleteOrder(Integer orderId) throws NullOrderIdException, InvalidOrderIdException;



}
