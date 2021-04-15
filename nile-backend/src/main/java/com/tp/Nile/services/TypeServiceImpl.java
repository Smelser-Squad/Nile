package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.NullTypeIdException;
import com.tp.Nile.models.Type;
import com.tp.Nile.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository repo;

    @Override
    public List<Type> getAllTypes() {
        return repo.findAll();
    }

    @Override
    public Type getTypeById(Integer typeId) throws NullTypeIdException, InvalidTypeIdException {
        if(typeId==null){
            throw new NullTypeIdException("Cannot get type with null id");
        }
        Type retrieved=null;

        Optional<Type> type=repo.findById(typeId);
        if(type.isPresent()){
            retrieved=type.get();
            return retrieved;
        }else{
            throw new InvalidTypeIdException("Type with that id does not exist");
        }

    }
    @Override
    public Type addType(Type newType) {
        return repo.saveAndFlush(newType);
    }
}
