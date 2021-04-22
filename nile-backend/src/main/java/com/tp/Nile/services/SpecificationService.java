package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Specification;
import com.tp.Nile.models.Type;

import java.util.List;

public interface SpecificationService {
    List<Specification> getAllSpecs();
    List<Specification> getSpecsByType(String type);
    Specification getSpecById(Integer specId) throws NullSpecIdException, InvalidSpecIdException;
    Specification addSpec(Specification newSpec) throws NullTypeIdException, InvalidTypeIdException, NullTypeNameException, EmptyTypeNameException;
    Specification updateSpec(Integer specId, Specification updatedSpec) throws NullSpecIdException, InvalidSpecIdException;
    boolean deleteSpec(Integer specId) throws NullSpecIdException;
}
