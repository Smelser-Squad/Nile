package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.NullTypeIdException;

import com.tp.Nile.models.Type;


import java.util.List;

public interface TypeService {
    List<Type> getAllTypes();

    Type getTypeById(Integer typeId) throws NullTypeIdException, InvalidTypeIdException;

    Type addType(Type newType);
}
