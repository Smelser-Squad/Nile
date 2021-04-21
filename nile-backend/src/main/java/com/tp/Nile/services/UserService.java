package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Order;
import com.tp.Nile.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws
            NullUserException, NullUserIdException, InvalidUserIdException;

    User getUserById(Integer userId) throws
            NullUserException, NullUserIdException, InvalidUserIdException;

    User addUser(User user) throws
            NullUserException, NullUserIdException, InvalidUserIdException;

    User updateUser(User user) throws
            NullUserException, NullUserIdException, InvalidUserIdException;

    boolean deleteUser(Integer userId) throws
            NullUserException, NullUserIdException, InvalidUserIdException;

    List<Order> getOrdersByUserId(Integer userId) throws
            NullUserException, NullUserIdException, InvalidUserIdException,
            NullOrderIdException, NullOrderException, InvalidOrderIdException;

    List<Answer> getAnswersByUserId(Integer userId) throws
            NullUserException, NullUserIdException, InvalidUserIdException,
            NullAnswerException, NullAnswerIdException, InvalidAnswerIdException;

}
