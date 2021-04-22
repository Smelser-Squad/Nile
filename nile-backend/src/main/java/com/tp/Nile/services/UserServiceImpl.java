package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Cart;
import com.tp.Nile.models.Review;
import com.tp.Nile.models.User;
import com.tp.Nile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public List<User> getAllUsers() throws NullUserException, NullUserIdException, InvalidUserIdException {

        List<User> users = repo.findAll();

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getUserId() == null)
                throw new NullUserIdException("User id can not be null");
            else if(users.get(i) == null)
                throw new NullUserException("User can not be null");
        }

        return users;
    }

    @Override
    public User getUserById(Integer userId) throws NullUserException, NullUserIdException, InvalidUserIdException {
        if (userId == null)
        {
            throw new NullUserIdException("User id can not be null");
        }

        User retrieved = null;

        Optional<User> user = repo.findById(userId);

        if(user.isPresent()){
            retrieved=user.get();
            if (retrieved == null)
                throw new NullUserException("All non nullable attributes must have value");
            else
                return retrieved;
        }else{
            throw new InvalidUserIdException("User with that id does not exist");
        }

    }

    @Override
    public User addUser(User user) throws NullUserException, NullUserIdException, InvalidUserIdException {
        if (user.getUserId() == null)
        {
            throw new NullUserIdException("User id can not be null");
        }
        else if (user == null) {
            throw new NullUserException("User can not be null");
        }
        else
            return repo.saveAndFlush(user);

    }

    @Override
    public User updateUser(User user) throws NullUserException, NullUserIdException, InvalidUserIdException {

        if (user.getUserId() == null)
            throw new NullUserIdException("User id can not be null");

        User edited = repo.findById(user.getUserId()).get();

        if (edited != null) {
            edited.setUserId(user.getUserId());
            edited.setAnswers(user.getAnswers());
            edited.setCarts(user.getCarts());
        }
        else
            throw new NullUserException("User can not be null");

        return repo.saveAndFlush(user);

    }

    @Override
    public boolean deleteUser(Integer userId) throws NullUserException, NullUserIdException, InvalidUserIdException {
        if(userId==null){
            throw new NullUserIdException("Cannot delete user with null id");
        }
        User retreived=repo.findById(userId).get();

        if(retreived!=null){
            repo.delete(retreived);
            return true;
        }else{
            throw new InvalidUserIdException("User with that id does not exist");
        }
    }

    @Override
    public List<Cart> getCartByUserId(Integer userId) throws NullUserException, NullUserIdException, InvalidUserIdException, NullCartIdException, NullCartException, InvalidCartIdException {
        return repo.getCartByUserId(userId);
    }

    @Override
    public List<Answer> getAnswersByUserId(Integer userId) throws NullUserException, NullUserIdException, InvalidUserIdException, NullAnswerException, NullAnswerIdException, InvalidAnswerIdException {
        return repo.getAnswersByUserId(userId);
    }
}
