package com.tp.Nile.services;

import com.tp.Nile.exceptions.EmptyTypeNameException;
import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.NullTypeIdException;
import com.tp.Nile.exceptions.NullTypeNameException;
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
    public Type addType(Type newType) throws NullTypeNameException, EmptyTypeNameException {
        if (newType.getTypeName() == null) {
            throw new NullTypeNameException("Type name cannot be null");
        }
        if (newType.getTypeName() == "") {
            throw new EmptyTypeNameException("Type name cannot be empty");
        }
        return repo.saveAndFlush(newType);
    }

    @Override
    public Type updateType(Integer typeId, Type updatedType) throws NullTypeIdException, InvalidTypeIdException {
        Type toUpdate = getTypeById(typeId);
        updatedType.setTypeId(typeId);
        return repo.saveAndFlush(updatedType);
    }

    @Override
    public boolean deleteType(Integer typeId) throws NullTypeIdException {
        if (typeId == null) {
            throw new NullTypeIdException("Cannot get specification with null id");
        }
        Type retrieved = repo.findById(typeId).get();
        if (retrieved != null) {
            repo.delete(retrieved);
        }
        return retrieved != null;
    }
}
