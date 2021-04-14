package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.exceptions.NullTypeIdException;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;

import javax.lang.model.type.NullType;
import java.util.List;

public interface TypeService {
    List<Type> getAllTypes();

    Type getTypeById(Integer typeId) throws NullTypeIdException, InvalidTypeIdException;
}
