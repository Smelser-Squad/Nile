package com.tp.Nile.services;

import com.tp.Nile.exceptions.EmptyTypeNameException;
import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.NullTypeIdException;

import com.tp.Nile.exceptions.NullTypeNameException;
import com.tp.Nile.models.Type;


import java.util.List;

public interface TypeService {
    List<Type> getAllTypes();

    Type getTypeById(Integer typeId) throws NullTypeIdException, InvalidTypeIdException;

    Type addType(Type newType) throws NullTypeNameException, EmptyTypeNameException;

    Type updateType(Type updatedType) throws NullTypeIdException, InvalidTypeIdException;

    boolean deleteType(Integer typeId) throws NullTypeIdException;
}
